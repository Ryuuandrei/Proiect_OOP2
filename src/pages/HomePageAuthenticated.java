package pages;

import changepage.ChPgLogout;
import changepage.ChPgMovies;
import changepage.ChPgUpgrades;
import command.Strategy.BannedStrategy;
import realistic.Application;

import java.util.ArrayList;

public final class HomePageAuthenticated extends Page {
    private static HomePageAuthenticated instance = null;
    private static final BannedStrategy BANNED;

    static {
        BANNED = new BannedStrategy();
    }

    private HomePageAuthenticated() {
    }

    /**
     *
     * @return the instance for HomePageAuthenticated
     */
    public static HomePageAuthenticated getInstance() {
        if (instance == null) {
            instance = new HomePageAuthenticated();
        }
        return instance;
    }

    @Override
    public void visit(final ChPgLogout actionInput, final Application application) {
        application.getPageStack().clear();
        application.setCurrentPage(HomePageUnauthenticated.getInstance());
        application.getEntity().setCurrentUser(null);
    }

    @Override
    public void visit(final ChPgMovies actionInput, final Application application) {
        application.getPageStack().push(actionInput);
        application.setCurrentPage(MoviesPage.getInstance());
        application.getEntity().setCurrentMoviesList(new ArrayList<>(application.getMoviesData()));
        BANNED.filter(application.getEntity(), application.getEntity().getCurrentUser()
                .getCredentials().getCountry());
    }

    @Override
    public void visit(final ChPgUpgrades actionInput, final Application application) {
        application.getPageStack().push(actionInput);
        application.setCurrentPage(Upgrades.getInstance());
    }

    @Override
    public void debug() {
        System.out.println("sunt in HomePageAuthenticated");
    }
}
