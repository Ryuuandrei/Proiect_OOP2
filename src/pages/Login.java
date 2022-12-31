package pages;

import changepage.ChPgRegister;
import features.LoginFeature;
import realistic.Application;

public final class Login extends Page {
    private static Login instance = null;

    private Login() {
    }

    /**
     * @return the instance for LoginPage
     */
    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }

    @Override
    public void visit(final ChPgRegister actionInput, final Application application) {
        application.setCurrentPage(Register.getInstance());
    }

    @Override
    public void visit(final LoginFeature actionInput, final Application application) {
        for (var user : application.getUsersData()) {
            if (user.getCredentials().equals(actionInput.getCredentials())) {
                application.setCurrentPage(HomePageAuthenticated.getInstance());
                application.getEntity().setCurrentUser(user);
                return;
            }
        }

        application.getEntity().setError("Error");
        application.setCurrentPage(HomePageUnauthenticated.getInstance());
    }


    @Override
    public void debug() {
        System.out.println("sunt in Login");
    }
}
