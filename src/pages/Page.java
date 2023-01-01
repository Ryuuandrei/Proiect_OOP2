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
import java.util.stream.Collectors;

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
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final LoginFeature actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final RegisterFeature actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final Search actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final FilterFeature actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final BuyTokens actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final BuyPremiumAccount actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final Purchase actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final Like actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final Rate actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final Watch actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final ChPgLogin actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final ChPgRegister actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final ChPgMovies actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final ChPgSeeDetails actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final ChPgUpgrades actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    /**
     * @param actionInput the action that has to be performed
     * @param application the application
     */
    public void visit(final ChPgLogout actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

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

    public void visit(final Subscribe actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

    public void visit(final Database actionInput, final Application application) {
        application.getEntity().setError("Error");
    }

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
                    user.getNotifications().add(new Notification(actionInput.getAddedMovie().getName(), "ADD"));
                    break;
                }
            }
        }
        application.getMoviesData().add(new ActualMovie(actionInput.getAddedMovie()));
        application.getEntity().setError("other");
    }

    public void visit(final DatabaseDelete actionInput, final Application application) {

        if (application.getMoviesData().stream().noneMatch(actualMovie -> actualMovie.getName().equals(actionInput.getDeletedMovie()))) {
            application.getEntity().setError("Error");
            return;
        }

        application.getMoviesData().removeIf(actualMovie -> actualMovie.getName().equals(actionInput.getDeletedMovie()));

    }

    /**
     * shows the current page
     */
    public void debug() {
    }
}
