package command;

import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public class Database extends Action {
    private String feature;
    public Database(final ActionInput a) {
        super(a);
        feature = a.getFeature();
    }

    /**
     *
     * @return the feature that has to be performed ro the database
     */
    public String getFeature() {
        return feature;
    }

    /**
     *
     * @param feature sets the feature that has to be performed to the database
     */
    public void setFeature(final String feature) {
        this.feature = feature;
    }

    /**
     *
     * @param page - the visitor
     * @param application - the application
     */
    @Override
    public void accept(final Page page, final Application application) {
        super.accept(page, application);
    }
}
