package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class UserDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/meubanco";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "leonardo";
    Scanner scanner = new Scanner(System.in);


    public boolean verifyUser(String user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE name = ?")) {
            statement.setString(1, user);

            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // THIS listFavoriteMovies FUNCTION IS HERE IN USERDAO BECAUSE IN MY LOGIC IT MAKES SENSE THE METHOD THAT SHOWS THE LIST OF
    // FAVORITES OF THE ->users<- HAVE A RELATION WITH THE USER, AND NOT WITH THE MOVIESDAO WHICH IS SPECIFIC FOR MOVIE AND DB
    public void listFavoriteMovies(String user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM movies WHERE user = ?")) {
            statement.setString(1, user);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("\n\u001B[34m┌─────────────────────────────┐");
                System.out.println("│     Favorite Movie List     │");
                System.out.println("\u001B[34m└─────────────────────────────┘");

                do {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String overview = resultSet.getString("overview");
                    String releaseDate = resultSet.getString("release_date");
                    double rating = resultSet.getDouble("rating");

                    System.out.println("ID: " + id);
                    System.out.println("Title: " + title);
                    System.out.println("Overview: " + overview);
                    System.out.println("Release Date: " + releaseDate);
                    System.out.println("Vote Average: " + rating);
                    System.out.println();
                } while (resultSet.next());

                System.out.println("1. Remove Movie");
                System.out.println("2. Remove All Favorites");
                System.out.println("3. Back to Main Menu");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    System.out.print("Enter the ID of the movie you want to remove: ");
                    int movieId = scanner.nextInt();
                    scanner.nextLine(); 
                    removeMovieFromDatabase(movieId);
                    System.out.println("Movie removed successfully!");
                } else if(choice == 2) {
                    removeAllFavorites(user);
                } else if (choice != 3) {
                    System.out.println("Invalid choice. Returning to Main Menu.");
                }
            } else {
                System.out.println("\u001B[1;31m┌───────────────────────────────┐");
                System.out.println("│ No favorite movies found. │");
                System.out.println("\u001B[1;31m└───────────────────────────────┘");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // THIS FUNCTION IS HERE FOR THE SAME REASON, BECAUSE I WILL REMOVE IT FROM THE ->users<- LIST
    private void removeMovieFromDatabase(int movieId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM movies WHERE id = ?")) {
            statement.setInt(1, movieId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // THIS FUNCTION IS HERE FOR THE SAME REASON, BECAUSE I WILL REMOVE IT FROM THE ->users<- LIST
    private void removeAllFavorites(String user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM movies WHERE user = ?")) {
            statement.setString(1, user);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Removed " + rowsAffected + " favorites.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
