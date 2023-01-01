package changepage;

import command.ActionChangePage;
import pages.Page;
import realistic.Application;

public class Back extends ActionChangePage {
    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
    }
}
