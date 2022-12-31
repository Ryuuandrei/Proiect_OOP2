package command.Strategy;

import fileio.Sort;
import realistic.ActualMovie;
import realistic.ErrorEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class SortStrategy extends FilterStrategy {
    @Override
    public void filter(final ErrorEntity e, final Sort sort) {

        if (sort.getRating() != null) {
            if (sort.getRating().equals("decreasing")) {
                e.setCurrentMoviesList(new ArrayList<>(e.getCurrentMoviesList().stream()
                        .sorted((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()))
                        .collect(Collectors.toList())));
            } else {
                e.setCurrentMoviesList(new ArrayList<>(e.getCurrentMoviesList().stream()
                        .sorted(Comparator.comparingDouble(ActualMovie::getRating))
                        .collect(Collectors.toList())));
            }
        }

        if (sort.getDuration() != null) {
            if (sort.getDuration().equals("decreasing")) {
                e.setCurrentMoviesList(new ArrayList<>(e.getCurrentMoviesList().stream()
                        .sorted((o1, o2) -> Integer.compare(o2.getDuration(), o1.getDuration()))
                        .collect(Collectors.toList())));
            } else {
                e.setCurrentMoviesList(new ArrayList<>(e.getCurrentMoviesList().stream()
                        .sorted(Comparator.comparingInt(ActualMovie::getDuration))
                        .collect(Collectors.toList())));
            }
        }
    }
}
