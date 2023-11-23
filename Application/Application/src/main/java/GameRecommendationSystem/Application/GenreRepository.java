package GameRecommendationSystem.Application;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface GenreRepository extends MongoRepository<Genre, String> {
    @Query(value = "{}", fields = "{'genreCategory': 1}")
    List<Genre> getAllGenreCategories();
    
    @Query("{ 'genreCategory': { $in: ?0 } }")
    List<Genre> findByGenreCategoryIn(List<String> genreCategories);

}