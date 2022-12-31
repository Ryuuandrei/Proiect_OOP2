package command;

import fileio.ActionInput;
import pages.Page;
import realistic.Application;

/**
 * general action for changing the current page
 */
public class ActionChangePage extends Action {
    private String page;

    /**
     *
     * @return - the page to change to
     */
    public String getPage() {
        return page;
    }

    /**
     *
     * @param page - set action page
     */
    public void setPage(final String page) {
        this.page = page;
    }

    public ActionChangePage() {
    }

    public ActionChangePage(final ActionInput a) {
        super(a);
        page = a.getPage();
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
