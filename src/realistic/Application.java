package realistic;

import command.ActionChangePage;
import fileio.MovieInput;
import fileio.UserInput;
import pages.HomePageUnauthenticated;
import pages.Page;

import java.util.ArrayList;
import java.util.Stack;

public final class Application {

    private ArrayList<ActualUser> usersData;
    private ArrayList<ActualMovie> moviesData;
    private ErrorEntity entity;
    private Page currentPage;
    private Stack<ActionChangePage> pageStack;

    public Stack<ActionChangePage> getPageStack() {
        return pageStack;
    }

    public void setPageStack(final Stack<ActionChangePage> pageStack) {
        this.pageStack = pageStack;
    }

    public ErrorEntity getEntity() {
        return entity;
    }

    public ArrayList<ActualUser> getUsersData() {
        return usersData;
    }

    public void setUsersData(final ArrayList<ActualUser> usersData) {
        this.usersData = usersData;
    }

    public void setEntity(final ErrorEntity entity) {
        this.entity = entity;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList<ActualMovie> getMoviesData() {
        return moviesData;
    }

    public void setMoviesData(final ArrayList<ActualMovie> moviesData) {
        this.moviesData = moviesData;
    }

    public Application(final ArrayList<UserInput> u, final ArrayList<MovieInput> m) {
        usersData = new ArrayList<>();
        for (UserInput user : u) {
            usersData.add(new ActualUser(user.getCredentials()));
        }
        moviesData = new ArrayList<>();
        for (MovieInput movie : m) {
            moviesData.add(new ActualMovie(movie));
        }
        entity = new ErrorEntity();
        currentPage = HomePageUnauthenticated.getInstance();
        pageStack = new Stack<>();
    }
}
