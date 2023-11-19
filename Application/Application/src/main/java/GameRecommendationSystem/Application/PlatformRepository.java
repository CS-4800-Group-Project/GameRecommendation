package GameRecommendationSystem.Application;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PlatformRepository extends MongoRepository<Platforms, String> {
    @Query(value = "{}", fields = "{'platformName': 1}")
    List<Platforms> getAllPlatformNames();
}
