package GameRecommendationSystem.Application;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

@Controller
public class GameRecommendationController {
	private List<String> titles = new ArrayList <>();
    private final RestTemplate restTemplate;
    private final String mobyGamesBaseUrl = "https://api.mobygames.com/v1/"; 
    public final static String apiKey = "moby_kZCBUgn5Hbs4CYbFhKpYTnvALrr"; 
    private ObjectMapper objectMapper;
    List<Game> gamesList = new ArrayList<>();
    
    @Autowired
    public GameRecommendationController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
  
    }
    
    @GetMapping("/gameTitle")
    public ResponseEntity<List<String>> getGame(@RequestParam(defaultValue = "0") int offset) {
        int batchSize = 100; 
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            String apiUrl = mobyGamesBaseUrl +"games?api_key=" + apiKey + "&offset=" + offset + "&limit=" + batchSize;

            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<JsonNode> gamesResponse = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    entity,
                    JsonNode.class
            );

           
            JsonNode gamesNode = gamesResponse.getBody();
            for (JsonNode game : gamesNode.get("games")) {
                String title = game.get("title").asText();
                titles.add(title);
            }

            return ResponseEntity.ok(titles);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonList("Error fetching games"));
        }
    }
    
    @GetMapping("/games")
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

            // does not go in here returns null i cant figure out why
            if (jsonNode.isArray()) {
                System.out.println("INSIDE");//never prints
                for (JsonNode gameNode : jsonNode) {
                    Game game = objectMapper.convertValue(gameNode, Game.class);
                    games.add(game);
                    System.out.println(game.getTitle());
                }
            }
            else
            {
                System.out.println("NULL");
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
    
   