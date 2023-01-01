package pages;

import changepage.ChPgLogout;
import changepage.ChPgMovies;
import changepage.ChPgSeeDetails;
import changepage.ChPgUpgrades;
import command.Strategy.BannedStrategy;
import command.Subscribe;
import features.*;
import realistic.Application;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class SeeDetails extends Page {
    private static SeeDetails instance = null;
//    private static final BannedStrategy BANNED;

    private static final int MAXRATING = 5;

//    static {
//        BANNED = new BannedStrategy();
//    }

    private SeeDetails() {
    }

    /**
     *
     * @return the instance for
     */
    public static SeeDetails getInstance() {
        if (instance == null) {
            return new SeeDetails();
        }
        return instance;
    }

    @Override
    public void visit(final ChPgMovies actionInput, final Application application) {
        application.getPageStack().push(actionInput);
        application.setCurrentPage(MoviesPage.getInstance());
        application.getEntity().setCurrentMoviesList(new ArrayList<>(application.getMoviesData()));
        application.getEntity()
                .filter(new BannedStrategy(application.getEntity().getCurrentMoviesList(),
                        application.getEntity()
                                .getCurrentUser()
                                .getCredentials()
                                .getCountry()));
    }

    @Override
    public void visit(final ChPgSeeDetails actionInput, final Application application) {
        application.getEntity().setCurrentMoviesList(new ArrayList<>(
                application.getEntity().getCurrentMoviesList()
                        .stream().filter(o1 -> o1.getName().equals(actionInput.getMovie()))
                        .collect(Collectors.toList())
        ));
        if (application.getEntity().getCurrentMoviesList().size() == 0) {
            application.setCurrentPage(MoviesPage.getInstance());
            application.getEntity().setError("Error");
        }
    }

    @Override
    public void visit(final ChPgUpgrades actionInput, final Application application) {
        application.setCurrentPage(Upgrades.getInstance());
    }

    @Override
    public void visit(final ChPgLogout actionInput, final Application application) {
        application.getEntity().setCurrentUser(null);
        application.getEntity().setCurrentMoviesList(new ArrayList<>());
        application.setCurrentPage(HomePageUnauthenticated.getInstance());
    }

    @Override
    public void visit(final Purchase actionInput, final Application application) {

        if (application.getEntity().getCurrentMoviesList().size() == 0
                || (application.getEntity().getCurrentUser().getNumFreePremiumMovies() <= 0
                && application.getEntity().getCurrentUser().getTokensCount() <= 1)) {
            super.visit(actionInput, application);
            return;
        }

        if (application.getEntity().getCurrentUser().getPurchasedMovies().stream()
                .anyMatch((actualMovie -> actualMovie.equals(application.getEntity().getCurrentMoviesList().get(0))))) {
            super.visit(actionInput, application);
            return;
        }

        if (application.getEntity().getCurrentUser().getNumFreePremiumMovies() > 0 && application.
                getEntity().getCurrentUser().getCredentials().getAccountType().equals("premium")) {

            application.getEntity().getCurrentUser().setNumFreePremiumMovies(application
                    .getEntity().getCurrentUser().getNumFreePremiumMovies() - 1);

        } else {

            application.getEntity().getCurrentUser().setTokensCount(application
                    .getEntity().getCurrentUser().getTokensCount() - 2);

        }

        application.getEntity().getCurrentUser().getPurchasedMovies()
                .add(application.getEntity().getCurrentMoviesList().get(0));
    }

    @Override
    public void visit(final Watch actionInput, final Application application) {

        if (application.getEntity().getCurrentMoviesList().size() == 0) {
            super.visit(actionInput, application);
            return;
        }

        if (application.getEntity().getCurrentUser().getWatchedMovies().stream()
                .anyMatch((actualMovie -> actualMovie.equals(application.getEntity().getCurrentMoviesList().get(0))))) {
            application.getEntity().setError("other");
            return;
        }

        if (application.getEntity().getCurrentUser().getPurchasedMovies().stream()
                .noneMatch(o -> o.getName().equals(application.getEntity()
                        .getCurrentMoviesList().get(0).getName()))) {
            super.visit(actionInput, application);
            return;
        }

        application.getEntity().getCurrentUser().getWatchedMovies()
                .add(application.getEntity().getCurrentMoviesList().get(0));
    }

    @Override
    public void visit(final Like actionInput, final Application application) {

        if (application.getEntity().getCurrentMoviesList().size() == 0) {
            application.getEntity().setError("Error");
            return;
        }

        if (application.getEntity().getCurrentUser().getLikedMovies().stream()
                .anyMatch((actualMovie -> actualMovie.equals(application.getEntity().getCurrentMoviesList().get(0))))) {
            application.getEntity().setError("pula");
            return;
        }

        if (application.getEntity().getCurrentUser().getWatchedMovies().stream()
                .noneMatch(o -> o.getName().equals(application.getEntity()
                        .getCurrentMoviesList().get(0).getName()))) {
            super.visit(actionInput, application);
            return;
        }
        application.getEntity().getCurrentMoviesList().get(0)
                .setNumLikes(application.getEntity().getCurrentMoviesList().get(0)
                        .getNumLikes() + 1);

        for (var genre : application.getEntity().getCurrentMoviesList().get(0).getGenres()) {

            if (!application.getEntity().getCurrentUser().getLikedGenres().containsKey(genre)) {
                application.getEntity().getCurrentUser().getLikedGenres().put(genre, 1);
            } else {
                application.getEntity().getCurrentUser().getLikedGenres().put(genre, application
                        .getEntity().getCurrentUser().getLikedGenres().get(genre) + 1);
            }

        }

        application.getEntity().getCurrentUser().getLikedMovies()
                .add(application.getEntity().getCurrentMoviesList().get(0));
    }

    @Override
    public void visit(final Rate actionInput, final Application application) {

        if (application.getEntity().getCurrentMoviesList().size() == 0
                || actionInput.getRate() > MAXRATING) {
            application.getEntity().setError("Error");
            return;
        }
//
//        if (application.getEntity().getCurrentUser().getRatedMovies().stream()
//                .anyMatch((actualMovie -> actualMovie.equals(application.getEntity().getCurrentMoviesList().get(0))))) {
//            super.visit(actionInput, application);
//            return;
//        }

        if (application.getEntity().getCurrentUser().getWatchedMovies().stream()
                .noneMatch(o -> o.getName().equals(application.getEntity()
                        .getCurrentMoviesList().get(0).getName()))) {
            super.visit(actionInput, application);
            return;
        }
        if (!application.getEntity().getCurrentMoviesList().get(0).getRatings()
                .containsKey(application.getEntity().getCurrentUser())) {

            application.getEntity().getCurrentUser().getRatedMovies()
                    .add(application.getEntity().getCurrentMoviesList().get(0));

            application.getEntity().getCurrentMoviesList().get(0).setNumRatings(application.
                    getEntity().getCurrentMoviesList().get(0).getNumRatings() + 1);
        }

        application.getEntity().getCurrentMoviesList().get(0).getRatings().put(application
                .getEntity().getCurrentUser(), actionInput.getRate());
        application.getEntity().getCurrentMoviesList().get(0).computeRating();
    }

    @Override
    public void visit(Subscribe actionInput, Application application) {
        if (application.getEntity().getCurrentUser().getSubscribedGenres().contains(actionInput
                .getSubscribedGenre())) {
            super.visit(actionInput, application);
        }
        application.getEntity().getCurrentUser().getSubscribedGenres().add(actionInput
                .getSubscribedGenre());
    }
}
