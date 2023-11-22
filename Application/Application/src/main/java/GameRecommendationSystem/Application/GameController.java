package GameRecommendationSystem.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

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
    public String searchGame(@RequestParam(name = "title") String title, Model model) {
        int perspective = -1;
        int genre = -1;
        int gameplay = -1;

        List<Game> gameList = gameRepository.findByTitleIgnoreCaseContainingRegex(title);

        Game matchedGame = gameList.get(0);

        for (int i = 0; i < matchedGame.getGenres().size(); i++) {
            if (matchedGame.getGenres().get(i).getGenreCategoryId() == 1 && genre == -1)
                genre = matchedGame.getGenres().get(i).getGenreId();
            else if (matchedGame.getGenres().get(i).getGenreCategoryId() == 2 && perspective == -1) {
                perspective = matchedGame.getGenres().get(i).getGenreId();
            } else if (matchedGame.getGenres().get(i).getGenreCategoryId() == 4 && gameplay == -1)
                gameplay = matchedGame.getGenres().get(i).getGenreId();
        }

        if (gameplay != -1 && genre != -1 && perspective != -1) {
            System.out.println("\ngameplay\n");
            gameList.addAll(gameRepository.findGamesByBothConditions(2, perspective, 1, genre, 4, gameplay));
        }

        // else if (gameplay != -1 && genre != -1 && perspective != -1)

        // Search game by title
        model.addAttribute("games", gameList);

        // Print out list of genre category under filter box Genre
        List<String> genreCategories = genreService.getAllDistinctGenreCategories();
        model.addAttribute("genreCategory", genreCategories);

        // Print out list of platforms under filter box Platform
        List<String> platformNames = platformService.getAllDistinctPlatformNames();
        model.addAttribute("platformNames", platformNames);
        return "searchResults";
    }

    @PostMapping("/filterPlatforms")
    public String platformFilter(@RequestParam(name = "platformNames", required = false) List<String> selectedPlatforms,
            Model model) {
        System.out.println("Selected Platforms: " + selectedPlatforms);

        List<Game> filteredPlatform = gameRepository.findByPlatforms(selectedPlatforms);

        model.addAttribute("filteredPlatform", filteredPlatform);
        return "searchResults";
    }

    @PostMapping("/filterGenres")
    public String genreFilter(@RequestParam(name = "genreCategory", required = false) List<String> selectedGenres,
            Model model) {
        System.out.println("Selected genre categories: " + selectedGenres);

        List<Game> filteredGenre = gameService.findGamesByGenreCategory(selectedGenres);

        model.addAttribute("filteredGenre", filteredGenre);
        return "searchResults";
    }

}
