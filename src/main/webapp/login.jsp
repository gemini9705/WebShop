<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="sv">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logga in</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #e0f7df; /* Ljusgrön bakgrund */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
            width: 360px; /* Öka bredden ytterligare */
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            font-size: 18px; /* Minska fontstorleken något */
            line-height: 1.3; /* Justera linjeavståndet */
            white-space: normal; /* Tillåt text att wrapa */
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 20px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .error-message {
            color: red;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Geminis & Moses Webbshop</h2>

    <form action="login" method="post">
        <label for="username">Användarnamn:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Lösenord:</label>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="Logga in">
    </form>

    <!-- Visa felmeddelande om fel användarnamn eller lösenord -->
    <%
        String error = request.getParameter("error");
        if (error != null) {
    %>
    <p class="error-message">Fel användarnamn eller lösenord. Försök igen!</p>
    <% } %>
</div>

</body>
</html>
