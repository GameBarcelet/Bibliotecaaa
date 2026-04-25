<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="bd" class="bean.Usuarios" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte Libros con Mayor Préstamo</title>
</head>
<body>
<h2>Reporte: Libros con Mayor Número de Préstamos</h2>
<%
    bd.reporteLibrosPrestamos();
%>
<p><%= bd.getRespuesta() %></p>
<br>
<a href='MenuAdmin.html'>Inicio</a>
</body>
</html>
