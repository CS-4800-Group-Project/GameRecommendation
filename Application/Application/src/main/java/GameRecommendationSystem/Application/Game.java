package GameRecommendationSystem.Application;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "games") // Make sure this matches your database table name
public class Game {
<<<<<<< HEAD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

=======
    private List<AlternateTitle> alternate_titles;
    private String description;
    private int game_id;
    private List<Genre> genres;
    private double moby_score;
    private String moby_url;
    private int num_votes;
    private String official_url;
    private List<Platforms> platforms;
    private Cover sample_cover;
    private List<Screenshot> sample_screenshots;
>>>>>>> 9d6bf6eda15531e1790f43e04638faf137d771f3
    private String title;
    @Column(columnDefinition = "TEXT") 
    private String description;

    

<<<<<<< HEAD
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
=======
    public List<AlternateTitle> getAlternateTitles() {
        return alternate_titles;
    }

    public void setAlternateTitles(List<AlternateTitle> alternate_titles) {
        this.alternate_titles = alternate_titles;
>>>>>>> 9d6bf6eda15531e1790f43e04638faf137d771f3
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

<<<<<<< HEAD
=======
    public int getGameId() {
        return game_id;
    }

    public void setGameId(int game_id) {
        this.game_id = game_id;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

>>>>>>> 9d6bf6eda15531e1790f43e04638faf137d771f3
    public double getMobyScore() {
        return moby_score;
    }

    public void setMobyScore(double moby_score) {
        this.moby_score = moby_score;
    }

<<<<<<< HEAD
    
=======
    public String getMobyUrl() {
        return moby_url;
    }

    public void setMobyUrl(String moby_url) {
        this.moby_url = moby_url;
    }

    public int getNumVotes() {
        return num_votes;
    }

    public void setNumVotes(int num_votes) {
        this.num_votes = num_votes;
    }

    public String getOfficialUrl() {
        return official_url;
    }

    public void setOfficialUrl(String official_url) {
        this.official_url = official_url;
    }

    public List<Platforms> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platforms> platforms) {
        this.platforms = platforms;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
>>>>>>> 9d6bf6eda15531e1790f43e04638faf137d771f3
}

    
    

    