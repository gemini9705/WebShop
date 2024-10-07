package org.example.webshop.controller;

import org.example.webshop.model.User;
import org.example.webshop.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;


public class LoginServlet extends HttpServlet {

    /**
     * Processes the HTTP POST request for user login.
     *
     * @param request  the HttpServletRequest object that contains the request data
     * @param response the HttpServletResponse object that will contain the response data
     * @throws ServletException if an error occurs during the processing of the request
     * @throws IOException      if an input or output error occurs during the processing
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService userService = new UserService();
        try {
            // Använd UserService för att verifiera användaren
            User user = userService.verifyUser(username, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user); // Store user in session
                session.setAttribute("message", "Lyckad inloggning!"); // Success message

                response.sendRedirect("products"); // Redirect to products page
            } else {
                // Forward to login page with error message if credentials are invalid
                request.setAttribute("error", "Fel användarnamn eller lösenord. Försök igen!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Forward to login page with error message if an SQL error occurs
            request.setAttribute("error", "Ett fel uppstod. Försök igen senare!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
