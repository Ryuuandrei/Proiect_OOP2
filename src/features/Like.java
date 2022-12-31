package features;

import command.ActionOnPage;
import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public final class Like extends ActionOnPage {
    private String feature;

    public Like(final ActionInput a) {
        super(a);
        this.feature = a.getFeature();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
    }
}
