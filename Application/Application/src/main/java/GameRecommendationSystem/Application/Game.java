package GameRecommendationSystem.Application;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;
@Document(collection = "GameList")
public class Game {
    @Id
    private String _id;

    @Indexed
	@Field("gameId")
    private int gameId;

    @Indexed
    @Field("title")
    private String title;

    @Field("description")
    private String description;
    
    @Field("genres")
    private List<Genre> genres;

    @Field("platforms")
    private List<Platforms> platforms;

    @Field("mobyScore")
    private double mobyScore;

    @Field("sampleCover")
    private Cover sampleCover;

    @Field("sampleScreenshots")
    private List<Screenshot> sampleScreenshots;

    public Game() {}
    public Game(int gameId, String title, String description, double mobyScore) {
    	this.gameId = gameId; 
    	this.title = title;
    	this.description = description; 
    	this.mobyScore = mobyScore;
    	
    }

    public double getMobyScore()
    {
        return mobyScore;
    }
    public void setMobyScore(double mobyScore)
    {
        this.mobyScore = mobyScore;
    }
    
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
    public List<Platforms> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platforms> platforms) {
        this.platforms = platforms;
    }
    
    
    
    public Cover getSampleCover() {
        return sampleCover;
    }

    public void setSampleCover(Cover sampleCover) {
        this.sampleCover = sampleCover;
    }


    public List<Screenshot> getSampleScreenshots() {
        return sampleScreenshots;
    }

    public void setSampleScreenshots(List<Screenshot> sampleScreenshots) {
        this.sampleScreenshots = sampleScreenshots;
    }
    
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId){
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
  
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}

    
    

    