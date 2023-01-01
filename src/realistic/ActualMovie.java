package realistic;

import fileio.MovieInput;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;


public final class ActualMovie extends MovieInput implements Comparable<ActualMovie> {
    private int numLikes;
    private double rating;
    private int numRatings;
    @JsonIgnore
    private HashMap<ActualUser, Double> ratings = new HashMap<>();
    @JsonIgnore
    private HashMap<ActualUser, Boolean> usersThatLikedTheMovie = new HashMap<>();

    public HashMap<ActualUser, Boolean> getUsersThatLikedTheMovie() {
        return usersThatLikedTheMovie;
    }

    public void setUsersThatLikedTheMovie(final HashMap<ActualUser,
            Boolean> usersThatLikedTheMovie) {
        this.usersThatLikedTheMovie = usersThatLikedTheMovie;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    public ActualMovie(final MovieInput movieInput) {
        super(movieInput);
    }

    public HashMap<ActualUser, Double> getRatings() {
        return ratings;
    }

    public void setRatings(final HashMap<ActualUser, Double> ratings) {
        this.ratings = ratings;
    }

    public ActualMovie(final ActualMovie other) {
        super(other);
        this.rating = other.rating;
        this.numLikes = other.numLikes;
        this.numRatings = other.numRatings;
        this.ratings = other.ratings;
        this.usersThatLikedTheMovie = other.usersThatLikedTheMovie;
    }

    /**
     * updates the rating
     * @return the new rating
     */
    public double computeRating() {
        rating = 0;
        for (Map.Entry<ActualUser, Double> entry : ratings.entrySet()) {
            rating += entry.getValue();
        }
        rating /= numRatings;
        return rating;
    }

    @Override
    public int compareTo(final ActualMovie o) {
        return Integer.compare(o.numLikes, numLikes);
    }
}
