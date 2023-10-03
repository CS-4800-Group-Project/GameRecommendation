package GameRecommendationSystem.Application;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
@Controller
public class GameRecommendationController {

    private final RestTemplate restTemplate;
    private final String mobyGamesBaseUrl = "https://api.mobygames.com/v1"; 
    public final static String apiKey = "moby_kZCBUgn5Hbs4CYbFhKpYTnvALrr"; 

    public GameRecommendationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/genres")
    public ResponseEntity<String> getGenres() {
        String apiGenresUrl = mobyGamesBaseUrl + "/genres?api_key=" + apiKey;

        try {
            ResponseEntity<String> genresResponse = restTemplate.exchange(apiGenresUrl, HttpMethod.GET, null, String.class);

            return ResponseEntity.ok(genresResponse.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching genres");
        }
    }
    @GetMapping("/groups")
    public ResponseEntity<String> getGroups() {
        String apiGenresUrl = mobyGamesBaseUrl + "/groups?api_key=" + apiKey;

        try {
            ResponseEntity<String> groupsResponse = restTemplate.exchange(apiGenresUrl, HttpMethod.GET, null, String.class);

            return ResponseEntity.ok(groupsResponse.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching groups");
        }
    }
    @GetMapping("/games")
    public ResponseEntity<String> getGames() {
        String apiGenresUrl = mobyGamesBaseUrl + "/games?api_key=" + apiKey;

        try {
            ResponseEntity<String> gamesResponse = restTemplate.exchange(apiGenresUrl, HttpMethod.GET, null, String.class);

            return ResponseEntity.ok(gamesResponse.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching games");
        }
    }
    @GetMapping("/games/{id}")
    public ResponseEntity<String> getGamesWithID(@PathVariable String id) {
        String apiGenresUrl = mobyGamesBaseUrl + "/games?id=" + id + "&api_key=" + apiKey;

        try {
         
            ResponseEntity<String> gamesResponse = restTemplate.exchange(apiGenresUrl, HttpMethod.GET, null, String.class);

            
            return ResponseEntity.ok(gamesResponse.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching games");
        }
    }
    
}

	
	
	
