<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Service" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Serviços</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
      	<a href="index.html" class="btn btn-secondary mt-4">Home</a>
        <h1 class="text-center">Serviços Disponíveis</h1>
        <form action="addService" method="post" class="mb-4">
            <div class="form-group">
                <label for="name">Nome:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="description">Descrição:</label>
                <input type="text" class="form-control" id="description" name="description" required>
            </div>
            <div class="form-group">
                <label for="price">Preço:</label>
                <input type="number" class="form-control" id="price" step="0.01" name="price" required>
            </div>
            <button type="submit" class="btn btn-primary">Adicionar Serviço</button>
        </form>
        <hr>
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>Nome</th>
                    <th>Descrição</th>
                    <th>Preço</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Service> services = (List<Service>) request.getAttribute("services");
                    if (services != null) {
                        for (Service service : services) {
                %>
                <tr>
                    <td><%= service.getName() %></td>
                    <td><%= service.getDescription() %></td>
                    <td><%= service.getPrice() %></td>
                    <td>
                        <a href="viewService?id=<%= service.getId() %>" class="btn btn-info btn-sm">Ver Detalhes</a>
                        <a href="deleteService?id=<%= service.getId() %>" class="btn btn-danger btn-sm">Excluir</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" class="text-center">Nenhum serviço disponível</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js">
