package GameRecommendationSystem.Application;

import java.util.*;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformService {
    @Autowired
    private PlatformRepository platformRepository;

    public List<String> getAllDistinctPlatformNames() {
        List<Platforms> allGenres = platformRepository.getAllPlatformNames();
        Set<String> distinctCategories = allGenres.stream()
            .map(Platforms::getPlatformName)
            .filter(Objects::nonNull) 
            .collect(Collectors.toSet());

        return new ArrayList<>(distinctCategories);
    }
}
