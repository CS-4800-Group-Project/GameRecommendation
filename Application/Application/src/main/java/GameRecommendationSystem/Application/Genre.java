package GameRecommendationSystem.Application;
import javax.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genre_id;

    private String genre_name;

    private String genre_category;

    private Long genre_category_id;

    public Long getGenreId() {
        return genre_id;
    }

    public void setGenreId(Long genre_id) {
        this.genre_id = genre_id;
    }

    public Long getGenreCategoryId() {
        return genre_category_id;
    }

    public void setGenreCategoryId(Long genre_category_id) {
        this.genre_category_id = genre_category_id;
    }

    public String getGenreName() {
        return genre_name;
    }

    public void setGenreName(String genre_name) {
        this.genre_name = genre_name;
    }

    public String getGenreCategory() {
        return genre_category;
    }

    public void setGenreCategory(String genre_category) {
        this.genre_category = genre_category;
    }

   

}
