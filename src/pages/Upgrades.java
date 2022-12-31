package pages;

import changepage.ChPgLogout;
import changepage.ChPgMovies;
import command.Strategy.BannedStrategy;
import features.BuyPremiumAccount;
import features.BuyTokens;
import realistic.Application;

import java.util.ArrayList;

public final class Upgrades extends Page {
    private static Upgrades instance = null;
    private static final BannedStrategy BANNED;
    private static final int COSTPREMIUM = 10;

    static {
        BANNED = new BannedStrategy();
    }

    private Upgrades() {
    }

    /**
     *
     * @return the instance for UpgradesPage
     */
    public static Upgrades getInstance() {
        if (instance == null) {
            instance = new Upgrades();
        }
        return instance;
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
    public void visit(final ChPgLogout actionInput, final Application application) {
        application.getPageStack().clear();
        application.setCurrentPage(HomePageUnauthenticated.getInstance());
        application.getEntity().setCurrentMoviesList(new ArrayList<>());
        application.getEntity().setCurrentUser(null);
    }

    @Override
    public void visit(final BuyTokens actionInput, final Application application) {
        if (Integer.parseInt(application.getEntity().getCurrentUser().getCredentials().getBalance())
                - Integer.parseInt(actionInput.getCount()) < 0) {
            super.visit(actionInput, application);
            return;
        }

        application.getEntity().getCurrentUser().setTokensCount(
                application.getEntity().getCurrentUser().getTokensCount()
                        + Integer.parseInt(actionInput.getCount()));
        int newBalance = Integer.parseInt(application.getEntity().getCurrentUser().getCredentials()
                .getBalance()) - Integer.parseInt(actionInput.getCount());
        application.getEntity().getCurrentUser().getCredentials()
                .setBalance(String.valueOf(newBalance));
    }

    @Override
    public void visit(final BuyPremiumAccount actionInput, final Application application) {
        if (application.getEntity().getCurrentUser().getTokensCount() >= COSTPREMIUM) {
            application.getEntity().getCurrentUser().getCredentials().setAccountType("premium");
            application.getEntity().getCurrentUser().setTokensCount(application.getEntity()
                    .getCurrentUser().getTokensCount() - COSTPREMIUM);
        } else {
            super.visit(actionInput, application);
        }
    }
}
