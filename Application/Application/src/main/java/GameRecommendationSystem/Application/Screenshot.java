package GameRecommendationSystem.Application;
import javax.persistence.*;

@Entity
@Table(name = "screenshots")
public class Screenshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screenshot_id;

    private String image;
    private String thumbnail_image;
    private int width;
    private int height;
    private String caption;

    // Define getters and setters

    public Long getScreenshotId() {
        return screenshot_id;
    }

    public void setScreenshotId(Long screenshot_id) {
        this.screenshot_id = screenshot_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnailImage() {
        return thumbnail_image;
    }

    public void setThumbnailImage(String thumbnail_image) {
        this.thumbnail_image = thumbnail_image;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
