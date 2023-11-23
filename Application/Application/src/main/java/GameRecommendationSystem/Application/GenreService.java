package GameRecommendationSystem.Application;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<String> getAllDistinctGenreCategories() {
        List<Genre> allGenres = genreRepository.getAllGenreCategories();
        Set<String> distinctCategories = allGenres.stream()
            .map(Genre::getGenreCategory)
            .filter(Objects::nonNull) 
            .collect(Collectors.toSet());

        return new ArrayList<>(distinctCategories);
    }
    public Map<String, List<String>> getGenreNamesByCategory() {
        List<Genre> allGenres = genreRepository.findAll();

        return allGenres.stream()
                .collect(Collectors.groupingBy(
                        Genre::getGenreCategory,
                        Collectors.mapping(Genre::getGenreName, Collectors.toList())
                ));
    }
    
    
}
