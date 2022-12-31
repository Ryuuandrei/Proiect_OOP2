package features;

import command.ActionOnPage;
import fileio.ActionInput;
import fileio.Credentials;
import pages.Page;
import realistic.Application;

public final class RegisterFeature extends ActionOnPage {
    private Credentials credentials;

    public RegisterFeature(final ActionInput a) {
        super(a);
        credentials = a.getCredentials();
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
    }
}
