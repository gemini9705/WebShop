<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="sv">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Produkter</title>
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

        td form {
            display: flex;
            align-items: center;
            gap: 10px;
            justify-content: center;
        }

        input[type="number"] {
            width: 60px;
            padding: 5px;
            margin-right: 8px;
            text-align: center;
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

        input[type="number"] {
            width: 50px;
            padding: 5px;
            margin-right: 10px;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: white;
            border: none;
            padding: 8px 16px;
            text-decoration: none;
            cursor: pointer;
            font-size: 14px;
            border-radius: 4px;
        }

        input[type="submit"]:hover {
            background-color: #388e3c;
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
    <h2>Produkter</h2>


    <p>${message != null ? message : ""}</p>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Namn</th>
            <th>Beskrivning</th>
            <th>Pris</th>
            <th>Lager</th>
            <th>Lägg till i kundvagnen</th>
        </tr>
        </thead>
        <tbody>
        ${productTableHtml}
        </tbody>
    </table>

    <a href="view-cart">Visa kundvagn</a>
</div>

</body>
</html>
