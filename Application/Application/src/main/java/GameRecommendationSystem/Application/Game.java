package GameRecommendationSystem.Application;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "games") 
public class Game {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long game_id;

    private String title;
    @Column(columnDefinition = "TEXT") 
    private String description;

    private double moby_score;

    public Game() {}
    public Game(Long game_id, String title, String description, double moby_score) {
    	this.game_id = game_id; 
    	this.title = title;
    	this.description = description; 
    	this.moby_score = moby_score;
    	
    }

    public double getMobyScore()
    {
        return this.moby_score;
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
    public List<Platforms> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platforms> platforms) {
        this.platforms = platforms;
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_id")
    private Cover sample_cover;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private List<Screenshot> sample_screenshots;
    
    public Cover getSampleCover() {
        return sample_cover;
    }

    public void setSampleCover(Cover sample_cover) {
        this.sample_cover = sample_cover;
    }


    public List<Screenshot> getSampleScreenshots() {
        return sample_screenshots;
    }

    public void setSampleScreenshots(List<Screenshot> sample_screenshots) {
        this.sample_screenshots = sample_screenshots;
    }
    
    public Long getGameId() {
        return game_id;
    }

    public void setGameId(Long game_id){
        this.game_id = game_id;
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

    
    

    