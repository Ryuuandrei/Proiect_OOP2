package command.Strategy;

import fileio.Contain;
import realistic.ActualMovie;

import java.util.ArrayList;

public final class ContainsStrategy implements FilterStrategy {
    private final ArrayList<ActualMovie> movies;
    private final Contain contain;

    public ContainsStrategy(final ArrayList<ActualMovie> movies, final Contain contain) {
        this.movies = movies;
        this.contain = contain;
    }

    @Override
    public void filter() {

        if (contain.getActors() != null) {
            for (var actor : contain.getActors()) {
                movies.removeIf(actualMovie -> !(actualMovie
                        .getActors().contains(actor)));
            }
        }

        if (contain.getGenre() != null) {
            for (var genre : contain.getGenre()) {
                movies.removeIf(actualMovie -> !(actualMovie
                        .getGenres().contains(genre)));
            }
        }

    }
}
