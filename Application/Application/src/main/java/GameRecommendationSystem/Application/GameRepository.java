package GameRecommendationSystem.Application;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends MongoRepository<Game, String> {
    List<Game> findByGameId(int gameId);

    @Query("{'title': {$regex: ?0, $options: 'i'}}")
    List<Game> findByTitleIgnoreCaseContainingRegex(String title);
   
    @Query("{ 'platforms.platformName' : { $in: ?0 } }")
    List<Game> findByPlatforms(@Param("platforms") List<String> platformNames);
    
    @Query("{ 'genres.genreCategory' : { $in: ?0 } }")
    List<Game> findByGenreCategory(@Param("genres") List<String> genreCategory);
}