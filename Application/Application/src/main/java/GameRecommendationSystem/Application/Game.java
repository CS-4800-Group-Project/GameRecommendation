package GameRecommendationSystem.Application;

import java.util.List;

public class Game {
    private List<String> alternateTitles;
    private String description;
    private int gameId;
    private List<Genre> genres;
    private double mobyScore;
    private String mobyUrl;
    private int numVotes;
    private String officialUrl;
    private List<Platforms> platforms;
    private Cover sampleCover;
    private List<Screenshot> sampleScreenshots;
    private String title;

    // Constructors, getters, and setters

    public List<String> getAlternateTitles() {
        return alternateTitles;
    }

    public void setAlternateTitles(List<String> alternateTitles) {
        this.alternateTitles = alternateTitles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public double getMobyScore() {
        return mobyScore;
    }

    public void setMobyScore(double mobyScore) {
        this.mobyScore = mobyScore;
    }

    public String getMobyUrl() {
        return mobyUrl;
    }

    public void setMobyUrl(String mobyUrl) {
        this.mobyUrl = mobyUrl;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }

    public String getOfficialUrl() {
        return officialUrl;
    }

    public void setOfficialUrl(String officialUrl) {
        this.officialUrl = officialUrl;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
