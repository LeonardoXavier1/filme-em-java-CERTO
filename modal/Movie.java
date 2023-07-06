package modal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import dal.MoviesDao;
import request.MoviesAPI;
import request.RequestAPI;

public class Movie {
    
    
    Scanner scanner = new Scanner(System.in);
    RequestAPI request = new RequestAPI();
    MoviesAPI moviesAPI = new MoviesAPI();
    MovieInfos movieInfo = new MovieInfos(0, null, null, null, 0);
    MoviesDao moviesDao = new MoviesDao();

    public void displayMovieList() {
        List<MovieInfos> movies = new ArrayList<>();
        int currentPage = 1;
        int totalPages = request.getTotalPages();

        while (true) {
            System.out.println("\n\u001B[34m┌──────────────────────────┐");
            System.out.println("│        Movie List (Page " + currentPage + "/" + totalPages + ")        │");
            System.out.println("\u001B[34m└──────────────────────────┘");

            List<MovieInfos> currentMovies = moviesAPI.getMoviesFromAPI(currentPage);
            movies.addAll(currentMovies);

            for (MovieInfos movieInfo : currentMovies) {
                System.out.println("ID: " + movieInfo.getId());
                System.out.println("Title: " + movieInfo.getTitle());
                System.out.println("Overview: " + movieInfo.getOverview());
                System.out.println("Release Date: " + movieInfo.getReleaseDate());
                System.out.println("Vote Average: " + movieInfo.getRating());
                System.out.println();
            }

            System.out.println("1. Next Page");
            System.out.println("2. Previous Page");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1 && currentPage < totalPages) {
                currentPage++;
            } else if (choice == 2 && currentPage > 1) {
                currentPage--;
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public void saveFavoriteMovies(String user) {
        System.out.println("\n\u001B[34m┌─────────────────────────────┐");
        System.out.println("│    Save Favorite Movies    │");
        System.out.println("\u001B[34m└─────────────────────────────┘");
        System.out.println("Enter the IDs of the movies you want to save (separated by commas): ");
        String movieIdsString = scanner.nextLine();
        String[] movieIds = movieIdsString.split(",");

        List<MovieInfos> movies = new ArrayList<>();
        for (String movieId : movieIds) {
            int id = Integer.parseInt(movieId.trim());
            MovieInfos movie = moviesAPI.getMovieById(id);
            if (movie != null) {
                movies.add(movie);
            } else {
                System.out.println("Movie with ID " + id + " not found.");
            }
        }

        moviesDao.saveMoviesToDatabase(user, movies);
        System.out.println("Favorite movies saved successfully!");
    }
}
