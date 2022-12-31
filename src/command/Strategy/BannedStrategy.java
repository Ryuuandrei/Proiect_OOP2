package command.Strategy;

import realistic.ErrorEntity;

public final class BannedStrategy extends FilterStrategy {
    @Override
    public void filter(final ErrorEntity e, final String country) {
        e.getCurrentMoviesList().removeIf(actualMovie -> actualMovie
                .getCountriesBanned().contains(country));
    }
}
