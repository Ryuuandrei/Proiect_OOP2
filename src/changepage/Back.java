package changepage;

import command.ActionChangePage;
import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public class Back extends ActionChangePage {
    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
//        if (!application.getPageStack().isEmpty()) {
//            application.getPageStack().pop();
//        }
    }
}