package GameRecommendationSystem.Application;

import java.util.*;
import java.util.regex.Pattern;
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

    public List<Game> filterGamesByGenresAndPlatformAndYear(List<String> selectedGenres, List<String> selectedPlatforms, List<String> selectedYears, Double targetScore ) {

        if ((selectedGenres == null || selectedGenres.isEmpty()) 
        && (selectedPlatforms == null || selectedPlatforms.isEmpty())
        && (selectedYears == null || selectedYears.isEmpty())
        && (targetScore == null)) {
            System.out.println("No Filters");
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
        if (targetScore != null){
            query.addCriteria(Criteria.where("mobyScore").gte(targetScore));
        }

        List<Game> filteredGames = mongoTemplate.find(query, Game.class);

        // Log information for debugging

        System.out.println("Selected Genres: " + selectedGenreNames);
        System.out.println("Selected Platforms: " + selectedPlatformNames);
        System.out.println("Filtered Games: " + filteredGames.size());
        return filteredGames;
    }

    public List<Game> findGamesByFiveConditions(int categoryId1, int genreId1, int categoryId2, int genreId2,
    int categoryId3, int genreId3, int categoryId4, int genreId4, int categoryId5, int genreId5,
    Double mobyScore, List<String> selectedGenres, List<String> selectedPlatforms, String selectedReleaseYear, String title){
        Query query = new Query();
        query.addCriteria(Criteria.where("genres.genreCategoryId").is(categoryId1).and("genres.genreId").is(genreId1));
        query.addCriteria(Criteria.where("genres.genreCategoryId").is(categoryId2).and("genres.genreId").is(genreId2));
        query.addCriteria(Criteria.where("genres.genreCategoryId").is(categoryId3).and("genres.genreId").is(genreId3));
        query.addCriteria(Criteria.where("genres.genreCategoryId").is(categoryId4).and("genres.genreId").is(genreId4));
        query.addCriteria(Criteria.where("genres.genreCategoryId").is(categoryId5).and("genres.genreId").is(genreId5));
        query.addCriteria(Criteria.where("mobyScore").gte(mobyScore));
        System.out.println(mobyScore);
        System.out.println(query.toString());
        if(selectedGenres != null && !selectedGenres.isEmpty()){
            query.addCriteria(Criteria.where("genres.genreName").in(selectedGenres));
        }
        if(selectedPlatforms != null && !selectedPlatforms.isEmpty()){
            query.addCriteria(Criteria.where("plaform.platformName").in(selectedPlatforms));
        }
        if(selectedReleaseYear != null && !selectedReleaseYear.isEmpty()){
            query.addCriteria(Criteria.where("plaform.firstReleaseDate").regex("^" + selectedReleaseYear + "(-\\d{2}-\\d{2})?$"));
        }
        query.addCriteria(Criteria.where("title").not().regex(title, "i"));

        List<Game> games = mongoTemplate.find(query, Game.class);
        return games;
    }

    List<Game> findGamesByFourConditions(int categoryId1, int genreId1, int categoryId2, int genreId2,
                        int categoryId3, int genreId3, int categoryId4, int genreId4,
                        Double mobyScore, List<String> selectedGenres, List<String> selectedPlatforms, String selectedReleaseYear, String title){
        Query query = new Query();
        Criteria criteria = new Criteria().andOperator(
            Criteria.where("genres.genreCategoryId").is(categoryId1).and("genres.genreId").is(genreId1),
            Criteria.where("genres.genreCategoryId").is(categoryId2).and("genres.genreId").is(genreId2),
            Criteria.where("genres.genreCategoryId").is(categoryId3).and("genres.genreId").is(genreId3),
            Criteria.where("genres.genreCategoryId").is(categoryId4).and("genres.genreId").is(genreId4)
        );
        query.addCriteria(criteria);
        System.out.println( "Moby Score" + mobyScore);
        query.addCriteria(Criteria.where("mobyScore").gte(mobyScore));
        if(selectedGenres != null && !selectedGenres.isEmpty()){
            query.addCriteria(Criteria.where("genres.genreName").in(selectedGenres));
        }
        if(selectedPlatforms != null && !selectedPlatforms.isEmpty()){
            query.addCriteria(Criteria.where("platforms.platformName").in(selectedPlatforms));
        }
        if (selectedReleaseYear != null && !selectedReleaseYear.isEmpty()) {
            query.addCriteria(Criteria.where("platforms.firstReleaseDate").regex(selectedReleaseYear, "i"));
        }
        query.addCriteria(Criteria.where("title").not().regex(title, "i"));


        List<Game> games = mongoTemplate.find(query, Game.class);
        return games;
    }
    List<Game> findGamesByThreeConditions(int categoryId1, int genreId1, int categoryId2, int genreId2,
                        int categoryId3, int genreId3,
                        Double mobyScore, List<String> selectedGenres, List<String> selectedPlatforms, String selectedReleaseYear, String title){
        Query query = new Query();
        Criteria criteria = new Criteria().andOperator(
            Criteria.where("genres.genreCategoryId").is(categoryId1).and("genres.genreId").is(genreId1),
            Criteria.where("genres.genreCategoryId").is(categoryId2).and("genres.genreId").is(genreId2),
            Criteria.where("genres.genreCategoryId").is(categoryId3).and("genres.genreId").is(genreId3)
        );
        query.addCriteria(criteria);
        System.out.println( "Moby Score" + mobyScore);
        query.addCriteria(Criteria.where("mobyScore").gte(mobyScore));
        if(selectedGenres != null && !selectedGenres.isEmpty()){
            query.addCriteria(Criteria.where("genres.genreName").in(selectedGenres));
        }
        if(selectedPlatforms != null && !selectedPlatforms.isEmpty()){
            query.addCriteria(Criteria.where("platforms.platformName").in(selectedPlatforms));
        }
        if (selectedReleaseYear != null && !selectedReleaseYear.isEmpty()) {
            query.addCriteria(Criteria.where("platforms.firstReleaseDate").regex(selectedReleaseYear, "i"));
        }
        query.addCriteria(Criteria.where("title").not().regex(title, "i"));


        List<Game> games = mongoTemplate.find(query, Game.class);
        return games;
    }
    


    public List<Game> findByTitleIgnoreCaseContainingRegex(String title){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(title, "i"));
        List<Game> games = mongoTemplate.find(query, Game.class);
        return games;
    }
    public List<Game> findByTitleIgnoreCaseContainingRegexAndFilters(String title, Double mobyScore, List<String> selectedGenres, List<String> selectedPlatforms, String selectedReleaseYear){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(title, "i"));
        query.addCriteria(Criteria.where("mobyScore").gte(mobyScore));
        if(selectedGenres != null && !selectedGenres.isEmpty()){
            query.addCriteria(Criteria.where("genres.genreName").in(selectedGenres));
        }
        if(selectedPlatforms != null && !selectedPlatforms.isEmpty()){
            query.addCriteria(Criteria.where("platforms.platformName").in(selectedPlatforms));
        }
        if (selectedReleaseYear != null && !selectedReleaseYear.isEmpty()) {
            query.addCriteria(Criteria.where("platforms.firstReleaseDate").regex(selectedReleaseYear, "i"));
        }
        
        List<Game> games = mongoTemplate.find(query, Game.class);
        return games;
    }
}
