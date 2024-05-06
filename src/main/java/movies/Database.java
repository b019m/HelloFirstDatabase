package movies;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String databasePath;

    Database(String databasePath) { // constructor

        // create table, or make sure it is created

        this.databasePath = databasePath;

        try (Connection connection = DriverManager.getConnection(databasePath);
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS movies(name text, stars INTEGER, watched BOOLEAN)");

        } catch (SQLException e) {
            System.out.println("Error creating movie DB table because"
                    + e);
        }
    }

    public void addNewMovie(Movie movie) {

        String insertSQL = "INSERT INTO movies VALUES (?,?,?)";

        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            // INSERT INTO movies values (' ', 4, true);

            preparedStatement.setString(1, movie.getName());
            preparedStatement.setInt(2, movie.getStars());
            preparedStatement.setBoolean(3, movie.isWatched());

            preparedStatement.execute();
            // add movie
        } catch (SQLException e) {
            System.out.println("Error adding movie" + movie + "because"
                    + e);
        }
    }

    public List<Movie> getAllMovies() {

        try (Connection connection = DriverManager.getConnection(databasePath);
             Statement statement = connection.createStatement()) {

            // get all movies
            ResultSet movieResult = statement.executeQuery("SELECT * FROM movies ORDER BY name");

            List<Movie> movies = new ArrayList<>();

            while (movieResult.next()) {
                String name = movieResult.getString("name");
                int stars = movieResult.getInt("stars");
                boolean watched = movieResult.getBoolean("watched");
                Movie movie = new Movie(name, stars, watched);
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            System.out.println("Error fetching all movie because" + e);
            return null;
        }
    }
    public List<Movie> getAllMoviesByWatched(boolean watchedStatus) {
        try (Connection connection = DriverManager.getConnection(databasePath);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movies WHERE watched = ?")) {

            preparedStatement.setBoolean(1, watchedStatus);
            ResultSet movieResult = preparedStatement.executeQuery();

            List<Movie> movies = new ArrayList<>();

            while (movieResult.next()) {
            String name = movieResult.getString("name");
            int stars = movieResult.getInt("stars");
            boolean watched = movieResult.getBoolean("watched");
           Movie movie = new Movie(name, stars, watched);
           movies.add(movie);

        }
        return movies;
             // statement or prepared statement
             // select * from movies where watched = true
             // select * from movies where watched = false

        } catch (SQLException e) {
            System.out.println("Error getting movies because " + e);
            return null;
        }
    }

    public void updateMovie(Movie movie) {
        String sql = "UPDATE movies SET stars = ?, watched = ? WHERE name = ?";
        // update movies SET stars = 4, watched = true WHERE ma,e = 'UP'

        try (Connection connection = DriverManager.getConnection(databasePath);
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, movie.getStars());
            preparedStatement.setBoolean(2, movie.isWatched());
            preparedStatement.setString(3, movie.getName());

            preparedStatement.execute();
        } catch (SQLException e){
            System.out.println("Error updating movie" + movie + "because" + e);
        }


        }

    }




        /*
        - A database is separate application to your program
        - a database server application organizes, stores, and provides access to te data
        - your java program connects to the database to access the data
         */





