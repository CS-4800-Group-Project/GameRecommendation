package GameRecommendationSystem.Application;

import java.util.List;

public class Game {
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
    private String title;


    public List<AlternateTitle> getAlternateTitles() {
        return alternate_titles;
    }

    public void setAlternateTitles(List<AlternateTitle> alternate_titles) {
        this.alternate_titles = alternate_titles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public double getMobyScore() {
        return moby_score;
    }

    public void setMobyScore(double moby_score) {
        this.moby_score = moby_score;
    }

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
}
