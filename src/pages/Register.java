package pages;

import features.RegisterFeature;
import realistic.ActualUser;
import realistic.Application;

public final class Register extends Page {
    private static Register instance = null;

    private Register() {
    }

    /**
     *
     * @return the instance for RegisterPage
     */
    public static Register getInstance() {
        if (instance == null) {
            instance = new Register();
        }
        return instance;
    }

    @Override
    public void visit(final RegisterFeature actionInput, final Application application) {
        ActualUser newUser = new ActualUser(actionInput.getCredentials());
        application.getUsersData().add(newUser);
        application.setCurrentPage(HomePageAuthenticated.getInstance());
        application.getEntity().setCurrentUser(newUser);
    }

    @Override
    public void debug() {
        System.out.println("sunt in Register");
    }
}
