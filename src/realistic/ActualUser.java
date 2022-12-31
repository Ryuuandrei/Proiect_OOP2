package realistic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.Credentials;
import fileio.UserInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class ActualUser extends UserInput {
    private int tokensCount;
    private int numFreePremiumMovies;
    private static final int FREEPREMIUMMOVIES = 15;
    private ArrayList<ActualMovie> purchasedMovies = new ArrayList<>();
    private ArrayList<ActualMovie> watchedMovies = new ArrayList<>();
    private ArrayList<ActualMovie> likedMovies = new ArrayList<>();
    private ArrayList<ActualMovie> ratedMovies = new ArrayList<>();
    private ArrayList<Notification> notifications = new ArrayList<>();
    @JsonIgnore
    private HashMap<String, Integer> likedGenres = new HashMap<>();
    @JsonIgnore
    private ArrayList<String> subscribedGenres = new ArrayList<>();
    public ActualUser(final Credentials credentials) {
        super(credentials);
        numFreePremiumMovies = FREEPREMIUMMOVIES;
        purchasedMovies = new ArrayList<>();
        watchedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
    }

    public ActualUser(final ActualUser other) {
        this.numFreePremiumMovies = other.numFreePremiumMovies;
        this.tokensCount = other.tokensCount;
        for (var movie : other.getLikedMovies()) {
            this.likedMovies.add(new ActualMovie(movie));
        }

        for (var movie : other.getPurchasedMovies()) {
            this.purchasedMovies.add(new ActualMovie(movie));
        }

        for (var movie : other.getWatchedMovies()) {
            this.watchedMovies.add(new ActualMovie(movie));
        }

        for (var movie : other.getRatedMovies()) {
            this.ratedMovies.add(new ActualMovie(movie));
        }

        this.likedGenres.putAll(other.getLikedGenres());

        this.notifications.addAll(other.getNotifications());

        this.setCredentials(new Credentials(other.getCredentials()));
    }

    public void setLikedGenres(HashMap<String, Integer> likedGenres) {
        this.likedGenres = likedGenres;
    }

    public ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }

    public void setSubscribedGenres(ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    public HashMap<String, Integer> getLikedGenres() {
        return likedGenres;
    }

    public void setLikedGeneres(final HashMap<String, Integer> likedGenres) {
        this.likedGenres = likedGenres;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(final ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokens) {
        this.tokensCount = tokens;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<ActualMovie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<ActualMovie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<ActualMovie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<ActualMovie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<ActualMovie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<ActualMovie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<ActualMovie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<ActualMovie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
}
