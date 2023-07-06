package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import modal.Movie;
import modal.MovieInfos;

public class MoviesDao {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/meubanco";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "leonardo";


    MovieInfos movieInfo = new MovieInfos(0, null, null, null, 0);


    public void saveMoviesToDatabase(String user, List<MovieInfos> movies) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO movies (user, title, overview, release_date, rating) VALUES (?, ?, ?, ?, ?)")) {
            for (MovieInfos movieInfo : movies) {
                statement.setString(1, user);
                statement.setString(2, movieInfo.getTitle());
                statement.setString(3, movieInfo.getOverview());
                statement.setString(4, movieInfo.getReleaseDate());
                statement.setDouble(5, movieInfo.getRating());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
