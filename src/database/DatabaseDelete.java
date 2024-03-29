package database;

import command.Database;
import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public final class DatabaseDelete extends Database {
    private String deletedMovie;
    public DatabaseDelete(final ActionInput a) {
        super(a);
        deletedMovie = a.getDeletedMovie();
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setDeletedMovie(final String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }

    @Override
    public void accept(final Page page, final Application application) {
        page.visit(this, application);
    }
}
