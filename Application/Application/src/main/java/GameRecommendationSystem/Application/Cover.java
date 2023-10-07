package GameRecommendationSystem.Application;
import javax.persistence.*;

@Entity
@Table(name = "covers")
public class Cover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cover_id;

    private String image;
    private String thumbnailImage;
    private int width;
    private int height;

    // Define getters and setters

    public Long getCoverId() {
        return cover_id;
    }

    public void setCoverId(Long cover_id) {
        this.cover_id = cover_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
