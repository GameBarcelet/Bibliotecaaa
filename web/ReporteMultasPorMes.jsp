<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="bd" class="bean.Usuarios" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte Multas por Mes</title>
</head>
<body>
<h2>Reporte: Cantidad de Multas por Mes</h2>
<%
    bd.reporteMultasPorMes();
%>
<p><%= bd.getRespuesta() %></p>
<br>
<a href='MenuAdmin.html'>Inicio</a>
</body>
</html>
