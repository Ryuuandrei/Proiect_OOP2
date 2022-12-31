package database;

import command.Database;
import fileio.ActionInput;
import pages.Page;
import realistic.Application;

public class DatabaseDelete extends Database {
    private String deletedMovie;
    public DatabaseDelete(final ActionInput a) {
        super(a);
        deletedMovie = a.getDeletedMovie();
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setDeletedMovie(String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }

    @Override
    public void accept(Page page, Application application) {
        page.visit(this, application);
    }
}
