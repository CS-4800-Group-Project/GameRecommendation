package GameRecommendationSystem.Application;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import org.springframework.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class GameRecommendationController {
    private final RestTemplate restTemplate;

    private final String mobyGamesBaseUrl = "https://api.mobygames.com/v1/"; 
    public final static String apiKey = "moby_kZCBUgn5Hbs4CYbFhKpYTnvALrr"; 
    private ObjectMapper objectMapper;
    private GameRepository gameRepository;
    List<Game> gamesList = new ArrayList<>();
    

    @Autowired
    public GameRecommendationController(RestTemplate restTemplate,ObjectMapper objectMapper, GameRepository gameRepository) {
        this.restTemplate = restTemplate;
        this.gameRepository = gameRepository;
        this.objectMapper = objectMapper;
    }
/*
    @GetMapping("/fetch-and-store-games")
    public ResponseEntity<String> fetchAndStoreGames() {
        String apiUrl = "https://api.mobygames.com/v1/games?api_key=" + apiKey;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<JsonNode> gamesResponse = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                JsonNode.class
            );

            // Process the response and store data in the local database
            JsonNode gamesData = gamesResponse.getBody();
            for (JsonNode gameJson : gamesData.get("games")) {
                Game gameEntity = new Game();
                gameEntity.setTitle(gameJson.get("title").asText());
                gameEntity.setDescription(gameJson.get("description").asText());

                // Save the entity to the database
                gameRepository.save(gameEntity);
            }

            return ResponseEntity.ok("Games fetched and stored successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching and storing games: " + e.getMessage());
        }
    }
    */
    @GetMapping("/game")
    public ResponseEntity<JsonNode> getGames() {
        String apiUrl = "https://api.mobygames.com/v1/games?api_key=" + apiKey;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<JsonNode> gamesResponse = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    entity,
                    JsonNode.class
            );

            // added 
            gamesList = convertJsonToGameList(gamesResponse.getBody());

            return ResponseEntity.ok(gamesResponse.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(objectMapper.createObjectNode().put("error", "Error fetching games"));
        }

    }

    // added

    private List<Game> convertJsonToGameList(JsonNode jsonNode) {
        //return 
        List<Game> games = new ArrayList<>();
        PropertyNamingStrategy strategy = objectMapper.getPropertyNamingStrategy();

        try {
            // Set the naming strategy to SNAKE_CASE temporarily for this deserialization
            // double checked format and it is in snake_case.
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

            if (jsonNode.isObject() && jsonNode.has("games")) {
                JsonNode gamesNode = jsonNode.get("games"); // Get the "games" field
            
                // inside games there should be an array of results
                if (gamesNode.isArray()) {
                    System.out.println("INSIDE");//never prints
                    for (JsonNode gameNode : gamesNode) {
                        Game game = objectMapper.convertValue(gameNode, Game.class);
                        games.add(game);
                        System.out.println(game.getTitle() + " " + game.getMobyScore());
                    }
                }
                else
                {
                    System.out.println("ERROR SOMEWHERE");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Reset the original property naming strategy
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

        return "games"; // Return the name of the HTML template
    }
}
    

