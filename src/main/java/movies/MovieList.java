package movies;

import java.util.List;

import static input.InputUtils.*;

public class MovieList {
    private static final String DB_PATH = "jdbc:sqlite:movie_watchlist.sqlite";
    private static Database database;

    public static void main(String[] args) {
        String DB_PATH ="jdbc:sqlite:movie_watchlist.sqlite";
        database = new Database(DB_PATH);

        addNewMovies();
        checkIfWatchedAndRate();
        displayAllMovies();

    }

    public static void addNewMovies() {
        do {
            String movieName = stringInput("Enter the movie name ");
            boolean movieWatched = yesNoInput("Have you seen this movie yet?");
            int movieStars = 0;
            if (movieWatched) {
                movieStars = positiveIntInput("What is you rating, in stars out of 5?");
                // todo add validation, make sure number is between 0 and 5
            }
            Movie movie = new Movie(movieName, movieStars, movieWatched);
            database.addNewMovie(movie);
        } while (yesNoInput("Add a movie to the watchlist? "));

        /*
        -don't make SQL Strings that include Strings from variables
         */

    }

    public static void displayAllMovies() {
        List<Movie> movies = database.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("No movies");
        } else {
            for (Movie movie : movies) {
                System.out.println(movie);
            }

        }
    }

    public static void checkIfWatchedAndRate() {
        List<Movie> unwatchedMovies = database.getAllMoviesByWatched(false);

        for (Movie movie : unwatchedMovies) {
            boolean hasWatched = yesNoInput("Have you watched" + movie.getName() + "yet?");
            if (hasWatched) {
                int stars = positiveIntInput("What is your rating for " + movie.getName() + "out of 5?");
                movie.setWatched(true);
                movie.setStars(stars);
                database.updateMovie(movie);
            }
        }
    }
}





