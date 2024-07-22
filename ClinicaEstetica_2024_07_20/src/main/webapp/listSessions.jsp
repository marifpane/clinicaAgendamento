<%@ page import="java.util.List"%>
<%@ page import="com.example.model.Session"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista de Sessões</title>
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
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
	color: #343a40;
	font-weight: bold;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 12px;
	text-align: left;
	border: 1px solid #dee2e6;
}

th {
	background-color: #007bff;
	color: #ffffff;
}

tr:nth-child(even) {
	background-color: #f8f9fa;
}

tr:hover {
	background-color: #e9ecef;
}

a {
	color: #007bff;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Sessões Agendadas</h1>
		<table>
			<thead>
				<tr>
					<th>Data</th>
					<th>Hora</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Session> sessions = (List<Session>) request.getAttribute("sessions");
				for (Session sessionReq : sessions) {
				%>
				<tr>
					<td><%=sessionReq.getDate()%></td>
					<td><%=sessionReq.getTime()%></td>
					<td><a
						href="deleteSession?id=<%=session.getId()%>&serviceId=<%=sessionReq.getServiceId()%>">Excluir</a>
					</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>
