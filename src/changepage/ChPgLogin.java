package changepage;

import command.ActionChangePage;
import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public final class ChPgLogin extends ActionChangePage {
    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
    }

    public ChPgLogin(final ActionInput a) {
        super(a);
    }
}
