package fileio;

public final class ActionInput {
    private String type;
    private String page;
    private String movie;
    private String feature;
    private Credentials credentials;
    private String startsWith;
    private String count;
    private double rate;
    private Filter filters;
    private String subscribedGenre;
    private MovieInput addedMovie;
    private String deletedMovie;

    public MovieInput getAddedMovie() {
        return addedMovie;
    }

    public void setAddedMovie(final MovieInput addedMovie) {
        this.addedMovie = addedMovie;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setDeletedMovie(final String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(final String movie) {
        this.movie = movie;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    public String getCount() {
        return count;
    }

    public void setCount(final String count) {
        this.count = count;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(final double rate) {
        this.rate = rate;
    }

    public Filter getFilters() {
        return filters;
    }

    public void setFilters(final Filter filters) {
        this.filters = filters;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public void setSubscribedGenre(final String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }

    public ActionInput(final ActionInput a) {
        this.type = a.type;
        this.page = a.page;
        this.movie = a.movie;
        this.feature = a.feature;
        this.credentials = a.credentials;
        this.startsWith = a.startsWith;
        this.count = a.count;
        this.rate = a.rate;
        this.filters = a.filters;
        this.addedMovie = a.addedMovie;
        this.subscribedGenre = a.subscribedGenre;
    }

    public ActionInput() {
    }

}
