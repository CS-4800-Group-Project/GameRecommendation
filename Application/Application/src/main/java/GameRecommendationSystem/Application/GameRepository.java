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

        @Query("{ 'genres.genreCategoryId' : ?0, 'genres.genreId' : ?1 }")
        List<Game> findByGenreCategoryIdAndGenreId(@Param("genreCategoryId") int genreCategoryId,
                        @Param("genreId") int genreId);

        @Query("{ $and: [ { 'genres.genreCategoryId': ?0, 'genres.genreId': ?1 }, { 'genres.genreCategoryId': ?2, 'genres.genreId': ?3 }, { 'genres.genreCategoryId': ?4, 'genres.genreId': ?5 }, {'mobyScore': { 'exists' : true, '$gte': ?6}}, {'genres.genreName': {$in: ?7}}, {'platforms.platformName': {$in: ?8}}, {'platforms.firstReleaseDate': {$in: ?9}}] }")
        List<Game> findGamesByThreeConditions(int categoryId1, int genreId1, int categoryId2, int genreId2,
                        int categoryId3, int genreId3, Double mobyScore, List<String> selectedGenres, List<String> selectedPlatforms, List<String> selectedReleaseYear);

        @Query("{ $and: [ { 'genres.genreCategoryId': ?0, 'genres.genreId': ?1 }, { 'genres.genreCategoryId': ?2, 'genres.genreId': ?3 }, { 'genres.genreCategoryId': ?4, 'genres.genreId': ?5 }, { 'genres.genreCategoryId': ?6, 'genres.genreId': ?7 }, {'mobyScore': {'$gte': ?8}}, {'genres.genreName': {$in: ?9}}, {'platforms.platformName': {$in: ?10}}, {'platforms.firstReleaseDate': {$in: ?11}} ] }")
        List<Game> findGamesByFourConditions(int categoryId1, int genreId1, int categoryId2, int genreId2,
                        int categoryId3, int genreId3, int categoryId4, int genreId4,
                        Double mobyScore, List<String> selectedGenres, List<String> selectedPlatforms, List<String> selectedReleaseYear);

        @Query("{ $and: [ { 'genres.genreCategoryId': ?0, 'genres.genreId': ?1 }, { 'genres.genreCategoryId': ?2, 'genres.genreId': ?3 }, { 'genres.genreCategoryId': ?4, 'genres.genreId': ?5 }, { 'genres.genreCategoryId': ?6, 'genres.genreId': ?7 }, { 'genres.genreCategoryId': ?8, 'genres.genreId': ?9 }, {'mobyScore': {'$gte': ?10}}, {'genres.genreName': {$in: ?11}}, {'platforms.platformName': { $in : ?12}}, {'platforms.firstReleaseDate': {$in: ?13}} ] }")
        List<Game> findGamesByFiveConditions(int categoryId1, int genreId1, int categoryId2, int genreId2,
                        int categoryId3, int genreId3, int categoryId4, int genreId4, int categoryId5, int genreId5,
                        Double mobyScore, List<String> selectedGenres, List<String> selectedPlatforms, List<String> selectedReleaseYear);

}