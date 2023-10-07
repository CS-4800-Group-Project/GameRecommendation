package GameRecommendationSystem.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GameService {
    private final RestTemplate restTemplate;
    private final String mobyGamesBaseUrl = "https://api.mobygames.com/v1/";
    private final String apiKey = "moby_kZCBUgn5Hbs4CYbFhKpYTnvALrr";
    private final GameRepository gameRepository;

    @Autowired
    public GameService(RestTemplate restTemplate, GameRepository gameRepository) {
        this.restTemplate = restTemplate;
        this.gameRepository = gameRepository;
    }

    public void fetchAndSaveGameData() {
        String apiUrl = mobyGamesBaseUrl + "games?api_key=" + apiKey;

        // Make an HTTP GET request to the API
        Game[] gamesArray = restTemplate.getForObject(apiUrl, Game[].class);

        if (gamesArray != null) {
            for (Game game : gamesArray) {
                // Save the Game entity to your local database
                gameRepository.save(game);
            }
        }
    }
}

