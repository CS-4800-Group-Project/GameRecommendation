package GameRecommendationSystem.Application;
import javax.persistence.*;

@Entity
@Table(name = "platforms")
public class Platforms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long platformId;

<<<<<<< HEAD
    private String platformName;

    // Define getters and setters

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
=======
public class Platforms{
    private int platform_id;
    private String platform_name;

    public int getPlatformId() {
        return platform_id;
    }

    public void setPlatformId(int platform_id) {
        this.platform_id = platform_id;
>>>>>>> 9d6bf6eda15531e1790f43e04638faf137d771f3
    }

    public String getPlatformName() {
        return platform_name;
    }

    public void setPlatformName(String platform_name) {
        this.platform_name = platform_name;
    }
}


