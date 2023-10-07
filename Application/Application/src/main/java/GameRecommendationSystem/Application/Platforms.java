package GameRecommendationSystem.Application;
import javax.persistence.*;

@Entity
@Table(name = "platforms")
public class Platforms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long platform_id;

    private String platform_name;

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
}


