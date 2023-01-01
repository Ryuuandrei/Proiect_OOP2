package pages;

import changepage.*;
import command.ActionChangePage;
import command.ActionOnPage;
import command.Database;
import command.Subscribe;
import database.DatabaseAdd;
import database.DatabaseDelete;
import features.*;
import realistic.ActualMovie;
import realistic.Application;
import realistic.Notification;

import java.util.ArrayList;

public class Page {
    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final ActionChangePage actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final ActionOnPage actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to log in the user
     * @param application the application
     */
    public void visit(final LoginFeature actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to register the user
     * @param application the application
     */
    public void visit(final RegisterFeature actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to search movies whose names start with the specified string
     * @param application the application
     */
    public void visit(final Search actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to perform the specified filtering options
     * @param application the application
     */
    public void visit(final FilterFeature actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to buy tokens
     * @param application the application
     */
    public void visit(final BuyTokens actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to buy a premium account
     * @param application the application
     */
    public void visit(final BuyPremiumAccount actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to purchase the movie in the currentMovieList
     * @param application the application
     */
    public void visit(final Purchase actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to like the movie in the currentMovieList
     * @param application the application
     */
    public void visit(final Like actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to rate the movie in the currentMovieList
     * @param application the application
     */
    public void visit(final Rate actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to watch the movie in the currentMovieList
     * @param application the application
     */
    public void visit(final Watch actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tris to change the page to the login page
     * @param application the application
     */
    public void visit(final ChPgLogin actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to change the page to the register page
     * @param application the application
     */
    public void visit(final ChPgRegister actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to change the page to the movies page
     * @param application the application
     */
    public void visit(final ChPgMovies actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to change the page to the see details page
     * @param application the application
     */
    public void visit(final ChPgSeeDetails actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to change the page to the upgardes page
     * @param application the application
     */
    public void visit(final ChPgUpgrades actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput tries to log the user out
     * @param application the application
     */
    public void visit(final ChPgLogout actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     *
     * @param actionInput tries to go to the previous page
     * @param application the application
     */
    public void visit(final Back actionInput, final Application application) {

        try {
            application.getPageStack().pop();
            if (!application.getPageStack().isEmpty()) {
                application.getPageStack().pop().accept(application.getCurrentPage(), application);
            } else {
                if (!application.getCurrentPage().equals(HomePageAuthenticated.getInstance())) {
                    application.setCurrentPage(HomePageAuthenticated.getInstance());
                    return;
                }
                application.getEntity().setError("Error");
            }
        } catch (Exception e) {
            application.getEntity().setError("Error");
        }

    }

    /**
     *
     * @param actionInput tries to subscribe the use to the specified genre
     * @param application the application
     */
    public void visit(final Subscribe actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     *
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final Database actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     *
     * @param actionInput tries to add a movie to the database and notifies all the users that have
     *                    subscribed to film's genres
     * @param application the application
     */
    public void visit(final DatabaseAdd actionInput, final Application application) {
        if (application.getMoviesData().stream().anyMatch((actualMovie -> actualMovie.getName()
                .equals(actionInput.getAddedMovie().getName())))) {
            application.getEntity().setError("Error");
            return;
        }

        var users = new ArrayList<>(application.getUsersData().stream()
                .filter(actualUser -> !actionInput.getAddedMovie().getCountriesBanned()
                        .contains(actualUser.getCredentials().getCountry())).toList());

        for (var user : users) {
            for (var genre : user.getSubscribedGenres()) {
                if (actionInput.getAddedMovie().getGenres().contains(genre)) {
                    user.getNotifications().add(new Notification(actionInput
                            .getAddedMovie().getName(), "ADD"));
                    break;
                }
            }
        }
        application.getMoviesData().add(new ActualMovie(actionInput.getAddedMovie()));
        application.getEntity().setError("other");
    }

    /**
     *
     * @param actionInput tries to delete the specified movie from the database, not sure if the
     *                    users should be notified, it works like this xd
     * @param application the application
     */
    public void visit(final DatabaseDelete actionInput, final Application application) {

        if (application.getMoviesData().stream().noneMatch(actualMovie -> actualMovie
                .getName().equals(actionInput.getDeletedMovie()))) {
            application.getEntity().setError("Error");
            return;
        }

        application.getMoviesData().removeIf(actualMovie -> actualMovie
                .getName().equals(actionInput.getDeletedMovie()));

    }

    /**
     * shows the current page
     */
    public void debug() {
    }
}
