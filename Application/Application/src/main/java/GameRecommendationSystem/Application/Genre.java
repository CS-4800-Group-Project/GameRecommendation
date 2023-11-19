package GameRecommendationSystem.Application;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.bson.types.ObjectId;
@Document(collection = "GenreList")
public class Genre {
    @Id
    private ObjectId _id; 

    @Field("genreCategoryId")
    private int genreCategoryId;

    @Field("genreCategory")
    private String genreCategory;

    
    @Field("genreId")
    private int genreId;

    @Field("genreName")
    private String genreName;


    public Genre() {}
    public Genre(int genreCategoryId, String genreCategory, String genreName, int genreId) {
    	this.genreCategoryId = genreCategoryId; 
    	this.genreCategory = genreCategory;
    	this.genreName = genreName; 
    	this.genreId = genreId;
    	
    }
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getGenreCategoryId() {
        return genreCategoryId;
    }

    public void setGenreCategoryId(int genreCategoryId) {
        this.genreCategoryId = genreCategoryId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreCategory() {
        return genreCategory;
    }

    public void setGenreCategory(String genreCategory) {
        this.genreCategory = genreCategory;
    }


    @Override
    public String toString() {
        return "Genre{" +
                "id='" + _id + '\'' +
                ", genreCategory='" + genreCategory + '\'' +
                ", genreId=" + genreId +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}

