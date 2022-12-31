package pages;

import changepage.ChPgLogin;
import changepage.ChPgRegister;
import realistic.Application;

public final class HomePageUnauthenticated extends Page {
    private static HomePageUnauthenticated instance = null;

    private HomePageUnauthenticated() {
    }

    /**
     * @return the instance for HomePageUnauthenticated
     */
    public static HomePageUnauthenticated getInstance() {
        if (instance == null) {
            instance = new HomePageUnauthenticated();
        }
        return instance;
    }

    @Override
    public void visit(final ChPgLogin actionInput, final Application application) {
        application.setCurrentPage(Login.getInstance());
    }

    @Override
    public void visit(final ChPgRegister actionInput, final Application application) {
        application.setCurrentPage(Register.getInstance());
    }

    @Override
    public void debug() {
        System.out.println("sunt in HomePageUnauthenticated");
    }
}
