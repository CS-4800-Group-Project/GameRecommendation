package GameRecommendationSystem.Application;
import javax.persistence.*;

@Entity
public class Platforms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long platform_id;
    private String platform_name;
    private String firstReleaseDate;
    
    public Long getPlatformId() {
        return platform_id;
    }

    public void setPlatformId(Long platform_id) {
        this.platform_id = platform_id;
    }

    public String getPlatformName() {
        return platform_name;
    }

    public void setPlatformName(String platform_name) {
        this.platform_name = platform_name;
    }
    public String getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(String firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }
}


