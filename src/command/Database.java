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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Override
    public void accept(Page page, Application application) {
        super.accept(page, application);
    }
}
