package fileio;

import java.util.ArrayList;

public class MovieInput {
    private String name;
    private String year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    public MovieInput() {
    }

    public MovieInput(final MovieInput other) {
        this.name = other.name;
        this.year = other.year;
        this.duration = other.duration;
        this.genres = other.genres;
        this.actors = other.actors;
        this.countriesBanned = other.countriesBanned;
    }

    /**
     *
     * @return movie name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name movie name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return movie year
     */
    public String getYear() {
        return year;
    }

    /**
     *
     * @param year movie year
     */
    public void setYear(final String year) {
        this.year = year;
    }

    /**
     *
     * @return movie duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     *
     * @param duration movie duration
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     *
     * @return movie genres
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     *
     * @param genres movie genres
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     *
     * @return movie actors
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     *
     * @param actors movie actors
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /**
     *
     * @return banned countries
     */
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    /**
     *
     * @param countriesBanned banned countries
     */
    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    /**
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     *
     * @param obj the object to be compared with the current instance
     * @return true if the objects have the same name, false otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        try {
            return name.equals(((MovieInput) obj).name);
        } catch (Exception e) {
            System.out.println("Object not of type movie");
            return false;
        }
    }
}
