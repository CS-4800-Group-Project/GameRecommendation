package GameRecommendationSystem.Application;

public class Genre {
    private String genreCategory;
    private int genreCategoryId;
    private int genreId;
    private String genreName;
    private String genreDescription; // New field

    // Constructors, getters, and setters

    public String getGenreDescription() {
        return genreDescription;
    }

    public void setGenreDescription(String genreDescription) {
        this.genreDescription = genreDescription;
    }
}

