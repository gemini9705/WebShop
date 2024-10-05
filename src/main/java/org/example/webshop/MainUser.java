package org.example.webshop;

import org.example.webshop.dao.UserDAO;
import org.example.webshop.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class MainUser {
    public static void main(String[] args) {
        // Skapa ett scanner-objekt för att läsa input från användaren
        Scanner scanner = new Scanner(System.in);

        System.out.println("Välkommen! Vänligen logga in.");

        // Be användaren att mata in sitt användarnamn och lösenord
        System.out.print("Användarnamn: ");
        String username = scanner.nextLine();

        System.out.print("Lösenord: ");
        String password = scanner.nextLine();

        // Skapa ett UserDAO-objekt för att interagera med databasen
        UserDAO userDAO = new UserDAO();

        try {
            // Hämta användare från databasen baserat på användarnamn
            User user = userDAO.getUserByUsername(username);

            // Kontrollera om användaren hittades och om lösenordet stämmer
            if (user != null && user.getPassword().equals(password)) {
                System.out.println("Lyckad inloggning! Välkommen " + user.getUsername() + "!");
            } else {
                System.out.println("Misslyckad inloggning! Fel användarnamn eller lösenord.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ett fel uppstod vid databaskontakten.");
        }

        scanner.close();
    }
}
