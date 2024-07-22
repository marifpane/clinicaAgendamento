<%@ page import="com.example.model.Service" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalhes do Serviço</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1, h2 {
            color: #343a40;
            font-weight: bold;
            margin-bottom: 20px;
        }
        p {
            color: #6c757d;
            line-height: 1.6;
            margin-bottom: 10px;
        }
        form {
            margin-bottom: 30px;
        }
        label {
            font-weight: bold;
            color: #495057;
            display: block;
            margin-bottom: 5px;
        }
        input[type="date"], input[type="time"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            width: 100%;
            margin-bottom: 10px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #ffffff;
            padding: 12px 20px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            border-radius: 4px;
            width: 100%;
        }
        a {
            color: #007bff;
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <%
            Service service = (Service) request.getAttribute("service");
        %>
        <h1><%= service.getName() %></h1>
        <p><%= service.getDescription() %></p>
        <p>Preço: <%= service.getPrice() %></p>
        <h2>Agendar Sessão</h2>
        <form action="addSession" method="post">
            <input type="hidden" name="serviceId" value="<%= service.getId() %>">
            <label>Data:</label>
            <input type="date" name="date" required>
            <label>Hora:</label>
            <input type="time" name="time" required>
            <input type="submit" value="Agendar">
        </form>
        <h2>Sessões Agendadas</h2>
        <a href="listSessions?serviceId=<%= service.getId() %>">Ver Sessões</a>
    </div>
</body>
</html>

