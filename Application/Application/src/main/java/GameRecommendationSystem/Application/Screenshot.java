package GameRecommendationSystem.Application;

import org.springframework.data.mongodb.core.mapping.Field;

public class Screenshot {
    @Field("image")
    private String image;

    @Field("width")
    private int width;

    @Field("height")
    private int height;

    @Field("caption")
    private String caption;

    @Field("thumbnailImage")
    private String thumbnailImage;

    @Field("screenshotId")
    private String screenshotId;



    public String getScreenshotId() {
        return screenshotId;
    }

    public void setScreenshotId(String screenshotId) {
        this.screenshotId = screenshotId;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
