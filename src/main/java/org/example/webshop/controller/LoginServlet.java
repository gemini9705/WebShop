package org.example.webshop.controller;

import org.example.webshop.model.User;
import org.example.webshop.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet to handle user login requests.
 * This servlet processes user login by verifying the user's credentials through the UserService.
 * If the login is successful, the user is redirected to the products page; otherwise, an error message is displayed on the login page.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    /**
     * Processes the HTTP POST request for user login.
     * Retrieves the username and password from the request, verifies the credentials using the UserService,
     * and stores the user in the session if login is successful. If login fails, the user is redirected back to the login page with an error message.
     *
     * @param request  the HttpServletRequest object that contains the request the client has made of the servlet
     * @param response the HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if an error occurs during the processing of the request
     * @throws IOException      if an input or output error occurs while the servlet is handling the request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService userService = new UserService();
        try {
            // Use UserService to verify the user's credentials
            User user = userService.verifyUser(username, password);

            if (user != null) {
                // Store user in session if verification is successful
                HttpSession session = request.getSession();
                session.setAttribute("user", user); // Store user in session
                session.setAttribute("message", "Lyckad inloggning!"); // Success message

                // Redirect to the products page
                response.sendRedirect("products");
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
