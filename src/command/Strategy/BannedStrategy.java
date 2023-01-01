package command.Strategy;

import realistic.ActualMovie;
import realistic.ErrorEntity;

import java.util.ArrayList;

public final class BannedStrategy implements FilterStrategy {
    private final ArrayList<ActualMovie> movies;
    private final String country;

    public BannedStrategy(final ArrayList<ActualMovie> movies, final String country) {
        this.movies = movies;
        this.country = country;
    }

    @Override
    public void filter() {
        movies.removeIf(actualMovie -> actualMovie
                .getCountriesBanned().contains(country));
    }
}
