package GameRecommendationSystem.Application;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
	List<Game> findByTitle(String title);
    List<Game> findByGenres(String genres);
}