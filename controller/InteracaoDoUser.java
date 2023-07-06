package controller;

public class InteracaoDoUser{


     public void displayMainMenu() {
        System.out.println("\n\u001B[34m┌─────────────────────────────┐");
        System.out.println("│         Main Menu           │");
        System.out.println("\u001B[34m└─────────────────────────────┘");
        System.out.println("1. View Movie List");
        System.out.println("2. Save Favorite Movies");
        System.out.println("3. List Favorite Movies");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}