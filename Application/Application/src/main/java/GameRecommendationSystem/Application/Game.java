package GameRecommendationSystem.Application;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "games") // Make sure this matches your database table name
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    private String title;
    @Column(columnDefinition = "TEXT") 
    private String description;

    

    private double mobyScore;

    public Game() {}
    public Game(Long gameId, String title, String description, double mobyScore ) {
    	this.gameId = gameId; 
    	this.title = title;
    	this.description = description; 
    	this.mobyScore = mobyScore;
    	
    }
    
    @ManyToMany
    @JoinTable(
        name = "game_genre",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
        name = "game_platform",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private List<Platforms> platforms;
    
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
    public List<Platforms>  getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platforms> platforms) {
        this.platforms = platforms;
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_id")
    private Cover sampleCover;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private List<Screenshot> sampleScreenshots;
    
    public Cover getSampleCover() {
        return sampleCover;
    }

    public void setSampleCover(Cover sampleCover) {
        this.sampleCover = sampleCover;
    }

    // Getter and Setter for sampleScreenshots
    public List<Screenshot> getSampleScreenshots() {
        return sampleScreenshots;
    }

    public void setSampleScreenshots(List<Screenshot> sampleScreenshots) {
        this.sampleScreenshots = sampleScreenshots;
    }
    
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMobyScore() {
        return mobyScore;
    }

    public void setMobyScore(double mobyScore) {
        this.mobyScore = mobyScore;
    }

    
}

    
    

    