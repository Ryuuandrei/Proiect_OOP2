package features;

import command.ActionOnPage;
import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public final class Search extends ActionOnPage {
    private String startsWith;

    public Search(final ActionInput a) {
        super(a);
        startsWith = a.getStartsWith();
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
    }
}
