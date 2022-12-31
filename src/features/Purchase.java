package features;

import command.ActionOnPage;
import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public final class Purchase extends ActionOnPage {
    private String feature;
    private String movie;

    public Purchase(final ActionInput a) {
        super(a);
        this.feature = a.getFeature();
        this.movie = a.getMovie();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(final String movie) {
        this.movie = movie;
    }

    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
    }
}
