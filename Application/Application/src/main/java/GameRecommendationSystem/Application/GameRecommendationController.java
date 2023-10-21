package GameRecommendationSystem.Application;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;
import java.text.*;

@Controller
public class GameRecommendationController {
    private final RestTemplate restTemplate;
    private final String mobyGamesBaseUrl = "https://api.mobygames.com/v1/";
    public final static String apiKey = "moby_kZCBUgn5Hbs4CYbFhKpYTnvALrr";
    private ObjectMapper objectMapper;
    private List<Game> gamesList = new ArrayList<>();


    @Value("${game.data.file.path}")
    private String gameDataFilePath;
    
    @Autowired
    public GameRecommendationController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }


    private final int gamesPerPage = 100; // Number of games to fetch per request
    private final long requestDelayMillis = 1000; // Delay between requests in milliseconds (1 second)
    private final int maxGamesToFetch = 800;



    @PostConstruct
    public void loadGameList() {
        try {
           
            loadGameDataFromFile();
            if (gamesList.isEmpty()) {
                // If there are no games in game_data.json, fetch games from the API
                int offset = 0; // Offset for pagination
                int totalGamesFetched = 0; // Keep track of the total games fetched
                System.out.println(offset);
                while (totalGamesFetched < maxGamesToFetch) {
                    String apiUrl = "https://api.mobygames.com/v1/games?api_key=" + apiKey + "&offset=" + offset;
   
                    HttpHeaders headers = new HttpHeaders();
                    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
   
                    HttpEntity<?> entity = new HttpEntity<>(headers);
   
                    ResponseEntity<JsonNode> gamesResponse = restTemplate.exchange(
                            apiUrl,
                            HttpMethod.GET,
                            entity,
                            JsonNode.class
                    );
   
                    if (gamesResponse.getStatusCode().is4xxClientError() || gamesResponse.getStatusCode().is5xxServerError()) {
                        // Handle rate limiting or other errors
                        System.out.println("Error: " + gamesResponse.getStatusCode());
                        break;
                    }
   
                    // Get the games from the response and add them to the gamesList
                    List<Game> games = convertJsonToGameList(gamesResponse.getBody());
                    gamesList.addAll(games);
                   
                    // Update the total games fetched
                    totalGamesFetched += games.size();
   
                    // If the response doesn't contain the expected number of games or the limit is reached, break the loop
                    if (games.size() < gamesPerPage || totalGamesFetched >= maxGamesToFetch) {
                        break;
                    }
   
                    // Increment the offset for the next page
                    offset += gamesPerPage;
   
                    // Introduce a 1-second delay to avoid rate limiting
                    Thread.sleep(requestDelayMillis);
                   
                }
            saveGameDataToFile();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private void saveGameDataToFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // For pretty-printing

            // Specify the path to your JSON file
            File file = new File(gameDataFilePath);
            
            if (!file.exists()) {
                file.createNewFile();
            }

            objectMapper.writeValue(file, gamesList);
            System.out.println("gamesList has been successfully saved to game_data.json.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save game data to game_data.json: " + e.getMessage());
        }
    }

    private void loadGameDataFromFile() {
        try {
            File file = new File(gameDataFilePath);

            if (file.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                gamesList = objectMapper.readValue(file, new TypeReference<List<Game>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load game data from game_data.json: " + e.getMessage());
        }
    }
   
    private List<Game> convertJsonToGameList(JsonNode jsonNode) {
        List<Game> games = new ArrayList<>();
        PropertyNamingStrategy strategy = objectMapper.getPropertyNamingStrategy();


        try {
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);


            if (jsonNode.isObject() && jsonNode.has("games")) {
                JsonNode gamesNode = jsonNode.get("games");

                if (gamesNode.isArray()) {
                    for (JsonNode gameNode : gamesNode) {
                        Game game = objectMapper.convertValue(gameNode, Game.class);
                        games.add(game);
                        System.out.println(game.getTitle() + " " + game.getMobyScore());
                    }
                } else {
                    System.out.println("ERROR SOMEWHERE");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            objectMapper.setPropertyNamingStrategy(strategy);
        }
        return games;
    }


    // added
    //
    @GetMapping("/gamesresults")
    public String getGames(Model model) {
        // check to see if any games are in list
        System.out.println("Number of games retrieved: " + gamesList.size());
       


        model.addAttribute("games", gamesList); // Add the list of games to the model

        return "searchResults"; // Return the name of the HTML template
    }
   
    @GetMapping("/search")
    public String searchGame(@RequestParam(name = "title") String title, Model model) {
        // Create a list to store matching games
        List<Game> matchingGames = new ArrayList<>();

        // Normalize the search title for comparison
        String normalizedTitle = normalizeTitle(title);

        // Create a Collator instance for accent-insensitive comparison
        Collator collator = Collator.getInstance(new Locale("en", "US"));

        for (Game game : gamesList) {
            String gameTitle = normalizeTitle(game.getTitle());

            // Use the Collator instance to perform accent-insensitive comparison
            if (collator.compare(normalizedTitle, gameTitle) == 0|| gameTitle.contains(normalizedTitle)) {
                matchingGames.add(game);
            }
        }


        // Add the matching games to the model
        model.addAttribute("games", matchingGames);


        // Return the results template
        return "searchResults";
    }

    private String normalizeTitle(String title) {
        // Normalize the title to lowercase and remove diacritical marks (accents)
        return Normalizer.normalize(title, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }


}





