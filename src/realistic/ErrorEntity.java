package realistic;

import command.Strategy.FilterStrategy;
import fileio.ActionInput;
import pages.HomePageAuthenticated;
import pages.Upgrades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public final class ErrorEntity {
    private String error = null;
    private ArrayList<ActualMovie> currentMoviesList = new ArrayList<>();
    private ActualUser currentUser;

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public ArrayList<ActualMovie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(final ArrayList<ActualMovie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    public ActualUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final ActualUser currentUser) {
        this.currentUser = currentUser;
    }

    public ErrorEntity() {
    }

    public ErrorEntity(final ErrorEntity other) {
        error = other.error;
        currentUser = new ActualUser(other.currentUser);
        currentMoviesList = new ArrayList<>();
        for (var movie : other.getCurrentMoviesList()) {
            this.currentMoviesList.add(new ActualMovie(movie));
        }
    }

    /**
     * @param application the application
     * @param actionInput the current action that is performed on the page
     * @return the output of the action, if it is any
     */
    public static ErrorEntity update(final Application application, final ActionInput actionInput) {

        ErrorEntity deepCopy;

        if (application.getEntity().getError() != null) {
            if (application.getEntity().getError().equals("Error")) {
                deepCopy = new ErrorEntity();
                deepCopy.setError("Error");
                return deepCopy;
            }
            if (application.getEntity().getError().equals("other")) {
                return null;
            }
        }
        if (actionInput.getType().equals("on page")
                && (actionInput.getFeature().equals("filter")
                || actionInput.getFeature().equals("search")
                || actionInput.getFeature().equals("login")
                || actionInput.getFeature().equals("register")
                || actionInput.getFeature().equals("purchase")
                || actionInput.getFeature().equals("watch")
                || actionInput.getFeature().equals("like")
                || actionInput.getFeature().equals("rate"))) {
            deepCopy = new ErrorEntity(application.getEntity());
            return deepCopy;
        }

        if (actionInput.getType().equals("change page")) {
            if (actionInput.getPage().equals("movies")
                    || actionInput.getPage().equals("see details")) {
                deepCopy = new ErrorEntity(application.getEntity());
                return deepCopy;
            }
        }

        if (actionInput.getType().equals("back")) {
            if (application.getCurrentPage().equals(HomePageAuthenticated.getInstance())
                || application.getCurrentPage().equals(Upgrades.getInstance())) {
                return null;
            }
            deepCopy = new ErrorEntity(application.getEntity());
            return deepCopy;
        }
        return null;
    }

    /**
     *
     * @param application the application
     * @return the recommended movie for the current premium user
     */
    public ActualMovie recommend(final Application application) {

        ActualMovie movie = null;
        if (!currentUser.getLikedGenres().isEmpty()) {

            ArrayList<ActualMovie> availableMovies =
                    new ArrayList<>(application.getMoviesData());

            availableMovies.removeIf(actualMovie -> actualMovie.getCountriesBanned()
                    .contains(application.getEntity()
                            .getCurrentUser()
                            .getCredentials()
                            .getCountry()));
            Collections.sort(availableMovies);
            ArrayList<String> sortedGenres = new ArrayList<>();
            currentUser.getLikedGenres().entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> sortedGenres.add(entry.getKey()));

            for (String genre : sortedGenres) {
                for (ActualMovie recommendedMovie : availableMovies) {
                    if (recommendedMovie.getGenres().contains(genre)
                            && !currentUser
                            .getLikedMovies()
                            .contains(recommendedMovie)) {
                        movie = recommendedMovie;
                        break;
                    }
                }
            }

        }
        return movie;

    }
    /**
     *
     * @param filterStrategy the filtering option
     */
    public void filter(final FilterStrategy filterStrategy) {
        filterStrategy.filter();
    }

}
