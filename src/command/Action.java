package command;

import fileio.ActionInput;
import pages.Page;
import realistic.Application;

/**
 * general action, only contains a type
 */
public class Action {
    private String type;

    public Action(final ActionInput a) {
        type = a.getType();
    }

    public Action() {
    }

    /**
     *
     * @param page - the visitor
     * @param application - the application
     */
    public void accept(final Page page, final Application application) {
    }

    /**
     *
     * @return - action type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type - set action type
     */
    public void setType(final String type) {
        this.type = type;
    }
}
