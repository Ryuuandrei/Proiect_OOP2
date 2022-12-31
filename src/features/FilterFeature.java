package features;

import command.ActionOnPage;
import fileio.ActionInput;
import fileio.Filter;
import pages.Page;
import realistic.Application;

public final class FilterFeature extends ActionOnPage {
    private Filter filter;

    public FilterFeature(final ActionInput a) {
        super(a);
        filter = a.getFilters();
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(final Filter filter) {
        this.filter = filter;
    }

    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
    }
}
