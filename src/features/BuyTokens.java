package features;

import command.ActionOnPage;
import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public final class BuyTokens extends ActionOnPage {
    private String count;

    public BuyTokens(final ActionInput a) {
        super(a);
        count = a.getCount();
    }

    public String getCount() {
        return count;
    }

    public void setCount(final String count) {
        this.count = count;
    }

    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
    }
}
