package pages;

import changepage.ChPgLogout;
import changepage.ChPgMovies;
import changepage.ChPgSeeDetails;
import changepage.ChPgUpgrades;
import command.Strategy.BannedStrategy;
import command.Strategy.ContainsStrategy;
import command.Strategy.SortStrategy;
import features.FilterFeature;
import features.Search;
import realistic.Application;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class MoviesPage extends Page {
    private static MoviesPage instance = null;

    private MoviesPage() {
    }

    /**
     * @return the instance for MoviesPage
     */
    public static MoviesPage getInstance() {
        if (instance == null) {
            instance = new MoviesPage();
        }
        return instance;
    }

    @Override
    public void visit(final ChPgMovies actionInput, final Application application) {
    }

    @Override
    public void visit(final ChPgSeeDetails actionInput, final Application application) {
        application.getPageStack().push(actionInput);
        application.setCurrentPage(SeeDetails.getInstance());
        application.getEntity().setCurrentMoviesList(new ArrayList<>(
                application.getEntity().getCurrentMoviesList()
                        .stream().filter(o1 -> o1.getName()
                                .equals(actionInput.getMovie())).collect(Collectors.toList())
        ));
        if (application.getEntity().getCurrentMoviesList().size() == 0) {
            application.getPageStack().pop();
            application.getEntity().setError("Error");
            application.setCurrentPage(MoviesPage.getInstance());
            application.getEntity().setCurrentMoviesList(new ArrayList<>(application
                    .getMoviesData()));
            application.getEntity()
                    .filter(new BannedStrategy(application.getEntity().getCurrentMoviesList(),
                            application.getEntity()
                                    .getCurrentUser()
                                    .getCredentials()
                                    .getCountry()));
        }
    }

    @Override
    public void visit(final ChPgLogout actionInput, final Application application) {
        application.getPageStack().clear();
        application.getEntity().setCurrentUser(null);
        application.getEntity().setCurrentMoviesList(new ArrayList<>());
        application.setCurrentPage(HomePageUnauthenticated.getInstance());
    }

    @Override
    public void visit(final ChPgUpgrades actionInput, final Application application) {
        application.getPageStack().push(actionInput);
        application.setCurrentPage(Upgrades.getInstance());
    }

    @Override
    public void visit(final Search actionInput, final Application application) {

        application.getEntity().setCurrentMoviesList(new ArrayList<>(application.getMoviesData()));
        application.getEntity()
                .filter(new BannedStrategy(application.getEntity().getCurrentMoviesList(),
                        application.getEntity()
                                .getCurrentUser()
                                .getCredentials()
                                .getCountry()));

        application.getEntity().getCurrentMoviesList().removeIf(actualMovie -> !(actualMovie
                .getName().startsWith(actionInput.getStartsWith())));
    }

    @Override
    public void visit(final FilterFeature actionInput, final Application application) {

        application.getEntity().setCurrentMoviesList(new ArrayList<>(application.getMoviesData()));
        application.getEntity()
                .filter(new BannedStrategy(application.getEntity().getCurrentMoviesList(),
                        application.getEntity()
                                .getCurrentUser()
                                .getCredentials()
                                .getCountry()));

        if (actionInput.getFilter().getSort() != null) {
            application.getEntity()
                    .filter(new SortStrategy(application.getEntity().getCurrentMoviesList(),
                            actionInput.getFilter().getSort()));
        }

        if (actionInput.getFilter().getContains() != null) {
            application.getEntity()
                    .filter(new ContainsStrategy(application.getEntity().getCurrentMoviesList(),
                            actionInput.getFilter().getContains()));
        }

    }

    @Override
    public void debug() {
        System.out.println("sunt in MoviesPage");
    }
}
