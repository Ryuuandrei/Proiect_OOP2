package command.Strategy;

import fileio.Sort;
import realistic.ActualMovie;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class SortStrategy implements FilterStrategy {
    private final ArrayList<ActualMovie> movies;
    private final Sort sort;

    public SortStrategy(final ArrayList<ActualMovie> movies, final Sort sort) {
        this.movies = movies;
        this.sort = sort;
    }

    @Override
    public void filter() {

        if (sort.getRating() != null) {
            if (sort.getRating().equals("decreasing")) {
                movies.sort((o1, o2) -> Double.compare(o2.getRating(), o1.getRating()));
            } else {
                movies.sort(Comparator.comparingDouble(ActualMovie::getRating));
            }
        }

        if (sort.getDuration() != null) {
            if (sort.getDuration().equals("decreasing")) {
                movies.sort((o1, o2) -> Integer.compare(o2.getDuration(), o1.getDuration()));
            } else {
                movies.sort(Comparator.comparingInt(ActualMovie::getDuration));
            }
        }
    }
}
