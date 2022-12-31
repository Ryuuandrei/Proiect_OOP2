package changepage;

import command.ActionChangePage;
import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public final class ChPgSeeDetails extends ActionChangePage {
    private String movie;

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

    public ChPgSeeDetails(final ActionInput a) {
        super(a);
        this.movie = a.getMovie();
    }
}
