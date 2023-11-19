package GameRecommendationSystem.Application;

import org.springframework.data.mongodb.core.mapping.Field;

public class Cover {
    @Field("image")
    private String image;

    @Field("thumbnailImage")
    private String thumbnailImage;

    @Field("width")
    private int width;

    @Field("height")
    private int height;

    @Field("coverId")
    private String coverId;

  

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
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
