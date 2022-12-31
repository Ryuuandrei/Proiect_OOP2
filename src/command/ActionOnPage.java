package command;

import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public class ActionOnPage extends Action {
    public ActionOnPage(final ActionInput a) {
        super(a);
    }

    public ActionOnPage() {
    }

    /**
     *
     * @param page - the visitor
     * @param application - the application
     */
    @Override
    public void accept(final Page page, final Application application) {
    }
}
