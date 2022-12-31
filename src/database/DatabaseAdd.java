package database;

import command.Database;
import fileio.ActionInput;
import fileio.MovieInput;
import pages.Page;
import realistic.Application;

public class DatabaseAdd extends Database {
    private MovieInput addedMovie;
    public DatabaseAdd(final ActionInput a) {
        super(a);
        addedMovie = a.getAddedMovie();
    }
    public MovieInput getAddedMovie() {
        return addedMovie;
    }

    public void setAddedMovie(MovieInput addedMovie) {
        this.addedMovie = addedMovie;
    }

    @Override
    public void accept(Page page, Application application) {
        page.visit(this, application);
    }
}
