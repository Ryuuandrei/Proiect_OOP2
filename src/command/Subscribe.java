package command;

import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public final class Subscribe extends Action {
    private String subscribedGenre;

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public void setSubscribedGenre(final String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }

    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
    }

    public Subscribe(final ActionInput a) {
        super(a);
        subscribedGenre = a.getSubscribedGenre();
    }
}
