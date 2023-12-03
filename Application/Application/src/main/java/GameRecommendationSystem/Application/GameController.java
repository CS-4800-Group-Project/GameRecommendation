package GameRecommendationSystem.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.ui.Model;

@Controller
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @Autowired
    private GenreService genreService;
    

    @Autowired
    private PlatformService platformService;

    @GetMapping("/description")
    public String gameDescriptionPage(@RequestParam(name = "gameId") Long gameId, Model model) {
        // Use the GameRepository to find the game by gameId
        List<Game> matchingGames = gameRepository.findByGameId(gameId.intValue());

        // Add the matching games to the model
        model.addAttribute("games", matchingGames);

        return "description";
    }

    @GetMapping("/search")
    public String searchGame(@RequestParam(name = "title") String title, 
    @RequestParam(name = "selectedGenres", required = false) List<String> selectedGenres,
    @RequestParam(name = "platformNames", required = false) List<String> selectedPlatforms,
    @RequestParam(name = "yearPublished", required = false) String selectedYears,
    @RequestParam(name = "rating", required = false) Double targetScore,
    Model model) {
        int perspective = -1;
        int genre1 = -1;
        int genre2 = -1;
        int gameplay = -1;
        int setting = -1;

        if(targetScore == null){
            targetScore = 0.0;
        }

        // Search game by title
        List<Game> gameList = gameService.findByTitleIgnoreCaseContainingRegexAndFilters(title, targetScore, selectedGenres, selectedPlatforms, selectedYears);
        if (!gameList.isEmpty()) {
            Game matchedGame = gameList.get(0);
                            System.out.println(matchedGame.getTitle());

            int count = 0;
            for (int i = 0; i < matchedGame.getGenres().size(); i++) {
                if (matchedGame.getGenres().get(i).getGenreCategoryId() == 1 && genre1 == -1) {
                    genre1 = matchedGame.getGenres().get(i).getGenreId();
                    count++;
                } else if (matchedGame.getGenres().get(i).getGenreCategoryId() == 1 && genre2 == -1) {
                    genre2 = matchedGame.getGenres().get(i).getGenreId();
                    count++;
                } else if (matchedGame.getGenres().get(i).getGenreCategoryId() == 2 && perspective == -1) {
                    perspective = matchedGame.getGenres().get(i).getGenreId();
                    count++;
                } else if (matchedGame.getGenres().get(i).getGenreCategoryId() == 4 && gameplay == -1) {
                    gameplay = matchedGame.getGenres().get(i).getGenreId();
                    count++;
                } else if (matchedGame.getGenres().get(i).getGenreCategoryId() == 10 && setting == -1) {
                    setting = matchedGame.getGenres().get(i).getGenreId();
                    count++;
                }

                if (count == 5){
                    break;
                }
            }
            System.out.println(gameplay != -1);
            System.out.println(genre1 != -1);
            System.out.println(perspective != -1);
            System.out.println(setting != -1);
            System.out.println(genre2 != -1);
            if (gameplay != -1 && genre2 == -1 && genre1 != -1 && perspective != -1 && setting != -1) {
                System.out.println("\n\n4\n\n");
                gameList.addAll( gameService.findGamesByFourConditions(2, perspective, 1, genre1, 4, gameplay, 10, setting,
                        targetScore, selectedGenres, selectedPlatforms, selectedYears, title)
                );
            } 
            else if(gameplay != -1 && genre2 != -1 && genre1 != -1 && perspective != -1 && setting != -1) {
                System.out.println("\n\n5\n\n");
                gameList.addAll(gameService.findGamesByFiveConditions(2, perspective, 1, genre1, 4, gameplay, 1, genre2, 10,
                                setting, targetScore, selectedGenres, selectedPlatforms, selectedYears, title));
            }
            else if(gameplay != -1 && genre2 != -1 && genre1 != -1 && perspective != -1 && setting == -1) {
                System.out.println("\n\n4\n\n");
                gameList.addAll(gameService.findGamesByFourConditions(2, perspective, 1, genre1, 4, gameplay, 1, genre2,
                        targetScore, selectedGenres, selectedPlatforms, selectedYears, title));
            }
            else if(gameplay != -1 && genre1 != -1 && perspective != -1 && setting == -1) {
                System.out.println("\n\n3\n\n");
                gameList.addAll(gameService.findGamesByThreeConditions(2, perspective, 1, genre1, 4, gameplay,
                        targetScore, selectedGenres, selectedPlatforms, selectedYears, title));
            }
        }

        // else if (gameplay != -1 && genre != -1 && perspective != -1)
        System.out.println(gameList.size());
        List<Game> filteredGames = new ArrayList<>();

    
        // Print out list of genre category under filter box Genre
        List<String> genreCategories = genreService.getAllDistinctGenreCategories();
        
        // Print out list of platforms under filter box Platform
        List<String> platformNames = platformService.getAllDistinctPlatformNames();
        List<String> platformNameskeep = List.of("Windows", "Macintosh", "iPhone","iPad", "Android", "Wii", "Nintendo Switch",  "PlayStation 3", "PlayStation 4", "PlayStation 5", "Linux", "DOS", "Xbox One", "Browser", "Arcade");
        platformNames.retainAll(platformNameskeep);
        
        List<String> yearPublished = IntStream.rangeClosed(1984, 2023)
                                             .mapToObj(Integer::toString)
                                             .collect(Collectors.toList());
        
        Map<String, List<String>> genreNamesByCategory = genreService.getGenreNamesByCategory();
    
        List<String> allGenreCategories = genreService.getAllDistinctGenreCategories();

        model.addAttribute("games", gameList);
        model.addAttribute("genreCategories", genreCategories);
        model.addAttribute("platformNames", platformNames);
        model.addAttribute("genreCategories", allGenreCategories);
        model.addAttribute("filteredGames", filteredGames);
        model.addAttribute("genreNamesByCategory", genreNamesByCategory);
        model.addAttribute("yearPublished", yearPublished);
        return "searchResults";
    }

}
