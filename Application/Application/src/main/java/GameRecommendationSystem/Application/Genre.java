package GameRecommendationSystem.Application;
import javax.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genre_id;

    private String genre_name;

    public Long getGenreId() {
        return genre_id;
    }

    public void setGenreId(Long genre_id) {
        this.genre_id = genre_id;
    }

    public String getGenreName() {
        return genre_name;
    }

    public void setGenreName(String genre_name) {
        this.genre_name = genre_name;
    }

   

}
