package GameRecommendationSystem.Application;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.bson.types.ObjectId;
@Document(collection = "PlatformList")
public class Platforms {
    @Id
    private ObjectId _id;

    @Field("firstReleaseDate")
    private String firstReleaseDate;

    @Field("platformName")
    private String platformName;

    @Field("platformId")
    private int platformId;

    public Platforms() {}
    public Platforms(String firstReleaseDate, String platformName, int platformId) {
    	this.firstReleaseDate = firstReleaseDate; 
    	this.platformName = platformName;
    	this.platformId = platformId; 
    	
    }
    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
    public String getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(String firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }
    @Override
    public String toString() {
        return "Platforms{" +
                "id='" + _id + '\'' +
                ", firstReleaseDate='" + firstReleaseDate + '\'' +
                ", platformName='" + platformName + '\'' +
                ", platformId=" + platformId +
                '}';
    }
}