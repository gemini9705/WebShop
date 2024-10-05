package org.example.webshop.controller;

import org.example.webshop.model.User;
import org.example.webshop.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService userService = new UserService();
        try {
            // Hämta användare från databasen via service-lagret
            User user = userService.getUserByUsername(username);

            // Kontrollera om användare finns och lösenordet stämmer
            if (user != null && user.getPassword().equals(password)) {
                // Skapa session för inloggad användare
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // Sätt ett meddelande om att inloggningen lyckades
                session.setAttribute("message", "Lyckad inloggning!");

                // Omdirigera till produktsidan efter lyckad inloggning
                response.sendRedirect("products");
            } else {
                // Felaktigt användarnamn eller lösenord
                request.setAttribute("error", "Fel användarnamn eller lösenord. Försök igen!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Om något går fel med databasen
            request.setAttribute("error", "Ett fel uppstod. Försök igen senare!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
