<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="bd" class="bean.Usuarios" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte Usuarios con Préstamos</title>
</head>
<body>
<h2>Reporte: Usuarios con Total de Préstamos</h2>
<%
    bd.reporteUsuariosPrestamos();
%>
<p><%= bd.getRespuesta() %></p>
<br>
<a href='MenuAdmin.html'>Inicio</a>
</body>
</html>
