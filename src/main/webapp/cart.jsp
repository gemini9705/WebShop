<%@ page import="org.example.webshop.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="sv">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Din Kundvagn</title>
    <style>
        body {
            background-color: #e8f5e9;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            max-width: 800px;
            width: 90%;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #81c784;
            color: #fff;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            color: #4caf50;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            color: #388e3c;
        }

        p {
            color: green;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Din Kundvagn</h2>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Namn</th>
            <th>Beskrivning</th>
            <th>Pris</th>
            <th>Antal</th>
            <th>Åtgärd</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Product> cart = (List<Product>) request.getAttribute("cart");
            if (cart != null && !cart.isEmpty()) {
                for (Product product : cart) {
        %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td><%= product.getDescription() %></td>
            <td><%= product.getPrice() %> kr</td>
            <td><%= product.getStock() %></td>
            <td>
                <form action="remove-from-cart" method="post">
                    <input type="hidden" name="productId" value="<%= product.getId() %>">
                    <input type="submit" value="Ta bort">
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="6">Din kundvagn är tom.</td></tr>
        <%
            }
        %>
        </tbody>
    </table>

    <a href="products">Tillbaka till Produkter</a>
</div>

</body>
</html>
