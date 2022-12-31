package features;

import command.ActionOnPage;
import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public final class Rate extends ActionOnPage {
    private double rate;
    private String feature;

    public Rate(final ActionInput a) {
        super(a);
        this.rate = a.getRate();
        this.feature = a.getFeature();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(final double rate) {
        this.rate = rate;
    }

    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
    }
}
