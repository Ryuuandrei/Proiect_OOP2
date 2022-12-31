package pages;

public final class Logout extends Page {

    private static Logout instance = null;

    private Logout() {
    }

    /**
     *
     * @return the instance for LogoutPage
     */
    private static Logout getInstance() {
        if (instance == null) {
            instance = new Logout();
        }
        return instance;
    }

    @Override
    public void debug() {
        System.out.println("sunt in logout");
    }
}
