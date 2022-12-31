package command.Strategy;

import fileio.Contain;
import realistic.ErrorEntity;

public final class ContainsStrategy extends FilterStrategy {
    @Override
    public void filter(final ErrorEntity e, final Contain contain) {

        if (contain.getActors() != null) {
            for (var actor : contain.getActors()) {
                e.getCurrentMoviesList().removeIf(actualMovie -> !(actualMovie
                        .getActors().contains(actor)));
            }
        }

        if (contain.getGenre() != null) {
            for (var genre : contain.getGenre()) {
                e.getCurrentMoviesList().removeIf(actualMovie -> !(actualMovie
                        .getGenres().contains(genre)));
            }
        }

    }
}
