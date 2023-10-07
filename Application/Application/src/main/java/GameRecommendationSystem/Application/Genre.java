package GameRecommendationSystem.Application;
import javax.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
<<<<<<< HEAD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;

    private String genreName;

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
=======
    private String genre_category;
    private int genre_category_id;
    private int genre_id;
    private String genre_name;
    private String genre_description; 

    public String getGenreCategory() {
        return genre_category;
    }

    // Setter method for genreCategory
    public void setGenreCategory(String genre_category) {
        this.genre_category = genre_category;
    }

    // Getter method for genreCategoryId
    public int genre_category_id() {
        return genre_category_id;
    }

    // Setter method for genreCategoryId
    public void setGenreCategoryId(int genre_category_id) {
        this.genre_category_id = genre_category_id;
    }

    // Getter method for genreId
    public int getGenreId() {
        return genre_id;
    }

    // Setter method for genreId
    public void setGenreId(int genre_id) {
        this.genre_id = genre_id;
    }

    // Getter method for genreName
    public String genre_name() {
        return genre_name;
    }

    // Setter method for genreName
    public void setGenreName(String genre_name) {
        this.genre_name = genre_name;
    }

    // Getter method for genreDescription
    public String getGenreDescription() {
        return genre_description;
    }

    // Setter method for genreDescription
    public void setGenreDescription(String genre_description) {
        this.genre_description = genre_description;
    }
    

   
    
>>>>>>> 9d6bf6eda15531e1790f43e04638faf137d771f3
}
