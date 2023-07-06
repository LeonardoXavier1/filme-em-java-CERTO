package view;


import java.net.URISyntaxException;
import java.util.Scanner;

import controller.InteracaoDoUser;
import dal.MoviesDao;
import dal.UserDao;
import modal.Movie;


public class Main{

    Scanner scanner = new Scanner(System.in);
    UserDao daoUser = new UserDao();
    InteracaoDoUser interactUser = new InteracaoDoUser();
    Movie movie = new Movie();
    MoviesDao moviesDao = new MoviesDao();


  public static void main(String[] args) throws URISyntaxException {
        Main main = new Main();
    }

     public Main() throws URISyntaxException {



        System.out.println("\u001B[34m┌───────────────────────────┐");
        System.out.println("\u001B[34m│           Login           │");
        System.out.println("\u001B[34m└───────────────────────────┘");

        System.out.print("user: ");
        String user = scanner.nextLine();

        if (daoUser.verifyUser(user)) {
            System.out.println("\u001B[32m┌─────────────────────────────┐");
            System.out.println("       Welcome, " + user);
            System.out.println("\u001B[32m└─────────────────────────────┘\n");

            int choice = 0;
            while (choice != 4) {
                interactUser.displayMainMenu();
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        movie.displayMovieList();
                        break;
                    case 2:
                        movie.saveFavoriteMovies(user);
                        break;
                    case 3:
                        daoUser.listFavoriteMovies(user);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("\u001B[1;31m┌─────────────────────────────────────────┐");
            System.out.println("│\u001B[1;31mUser not found in the Database.│");
            System.out.println("\u001B[1;31m└─────────────────────────────────────────┘");
        }       
     }
}