package GameRecommendationSystem.Application;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class GameRecommendationController {
    private final RestTemplate restTemplate;
    private final String mobyGamesBaseUrl = "https://api.mobygames.com/v1/";
    public final static String apiKey = "moby_kZCBUgn5Hbs4CYbFhKpYTnvALrr";
    private final GameRepository gameRepository;
    private ObjectMapper objectMapper; 

    @Autowired
    public GameRecommendationController(RestTemplate restTemplate,ObjectMapper objectMapper, GameRepository gameRepository) {
        this.restTemplate = restTemplate;
        this.gameRepository = gameRepository;
        this.objectMapper = objectMapper;
    }

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

            return ResponseEntity.ok(gamesResponse.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(objectMapper.createObjectNode().put("error", "Error fetching games"));
        }
    }
}

