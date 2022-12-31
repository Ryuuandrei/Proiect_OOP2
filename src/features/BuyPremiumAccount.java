package features;

import command.ActionOnPage;
import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public final class BuyPremiumAccount extends ActionOnPage {
    public BuyPremiumAccount(final ActionInput a) {
        super(a);
    }

    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
    }
}
