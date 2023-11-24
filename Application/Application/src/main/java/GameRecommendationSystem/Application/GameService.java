package GameRecommendationSystem.Application;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;

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
        
        System.out.println("Received genre categories: " + genreCategory);
        System.out.println("Number of games found: " + games.size());
        
        return games;
    }
    public List<Game> filterGamesByGenresAndPlatform(List<String> selectedGenres, List<String> selectedPlatforms, List<String> selectedYears) {
        
        if ((selectedGenres == null || selectedGenres.isEmpty()) 
        && (selectedPlatforms == null || selectedPlatforms.isEmpty())
        && (selectedYears == null || selectedYears.isEmpty())) {
            return gameRepository.findAll();
        }
    
        
        List<String> selectedGenreNames = (selectedGenres != null)
                ? selectedGenres.stream().map(genre -> genre.split(":")[0]).collect(Collectors.toList())
                : Collections.emptyList();
    
        
        List<String> selectedPlatformNames = (selectedPlatforms != null)
                ? selectedPlatforms.stream().map(platform -> platform.split(":")[0]).collect(Collectors.toList())
                : Collections.emptyList();
        
        List<String> selectedYearsList = (selectedYears != null)
                ? selectedYears
                : Collections.emptyList();
        
        
        Query query = new Query();
    
        if (!selectedGenreNames.isEmpty()) {
            query.addCriteria(Criteria.where("genres.genreName").in(selectedGenreNames));
        }
    
        if (!selectedPlatformNames.isEmpty()) {
            query.addCriteria(Criteria.where("platforms.platformName").in(selectedPlatformNames));
        }
        if (!selectedYearsList.isEmpty()) {
            query.addCriteria(Criteria.where("platforms.firstReleaseDate").in(selectedYears));
        }
    
        List<Game> filteredGames = mongoTemplate.find(query, Game.class);
    
        
        System.out.println("Selected Genres: " + selectedGenreNames);
        System.out.println("Selected Platforms: " + selectedPlatformNames);
        System.out.println("Filtered Games: " + filteredGames.size());
    
        return filteredGames;
    }
    
    
    

}
    
    
    
    
    
    
    
    
    
    
    
    
    
