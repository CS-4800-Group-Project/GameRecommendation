package GameRecommendationSystem.Application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findGamesByGenreCategory(List<String> genreCategory) {
        List<Game> games = gameRepository.findByGenreCategory(genreCategory);
        
        // Log the results and parameters
        // System.out.println("Received genre categories: " + genreCategory);
        // System.out.println("Number of games found: " + games.size());
        
        return games;
    }
}
