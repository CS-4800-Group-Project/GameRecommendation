package GameRecommendationSystem.Application;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.*;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final MongoTemplate mongoTemplate;
    @Autowired
    public GameService(GameRepository gameRepository, MongoTemplate mongoTemplate) {
        this.gameRepository = gameRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Game> findGamesByGenreCategory(List<String> genreCategory) {
        List<Game> games = gameRepository.findByGenreCategory(genreCategory);
        
        return games;
    }

    public List<Game> filterGamesByGenresAndPlatformAndYear(List<String> selectedGenres, List<String> selectedPlatforms, List<String> selectedYears) {

        if ((selectedGenres == null || selectedGenres.isEmpty()) 
        && (selectedPlatforms == null || selectedPlatforms.isEmpty())
        && (selectedYears == null || selectedYears.isEmpty())) {
            return gameRepository.findAll();
        }

        // Extract genre names without categories from selectedGenres

        List<String> selectedGenreNames = (selectedGenres != null)
                ? selectedGenres.stream().map(genre -> genre.split(":")[0]).collect(Collectors.toList())
                : Collections.emptyList();

        // Extract platform names from selectedPlatforms

        List<String> selectedPlatformNames = (selectedPlatforms != null)
                ? selectedPlatforms.stream().map(platform -> platform.split(":")[0]).collect(Collectors.toList())
                : Collections.emptyList();

        // Create a query to filter by genres if selectedGenres is not empty

        List<String> selectedYearsList = (selectedYears != null)
                ? selectedYears
                : Collections.emptyList();


        Query query = new Query();

        if (!selectedGenreNames.isEmpty()) {
            query.addCriteria(Criteria.where("genres.genreName").in(selectedGenreNames));
        }

        // If selectedPlatforms is not empty, add criteria to filter by platforms
        if (!selectedPlatformNames.isEmpty()) {
            query.addCriteria(Criteria.where("platforms.platformName").in(selectedPlatformNames));
        }
        if (!selectedYearsList.isEmpty()) {
            query.addCriteria(Criteria.where("platforms.firstReleaseDate").in(selectedYears));
        }

        List<Game> filteredGames = mongoTemplate.find(query, Game.class);

        // Log information for debugging

        System.out.println("Selected Genres: " + selectedGenreNames);
        System.out.println("Selected Platforms: " + selectedPlatformNames);
        System.out.println("Filtered Games: " + filteredGames.size());
        return filteredGames;
    }
}
