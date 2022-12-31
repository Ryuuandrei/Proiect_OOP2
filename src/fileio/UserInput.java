package fileio;

public class UserInput {

    private Credentials credentials;

    /**
     *
     * @return user credentials
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     *
     * @param credentials user credentials
     */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public UserInput(final Credentials credentials) {
        this.credentials = credentials;
    }

    public UserInput() {
    }
}
