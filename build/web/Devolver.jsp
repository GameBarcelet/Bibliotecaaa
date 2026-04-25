<%-- 
    Document   : Devolver
    Created on : 10 abr 2026, 19:40:24
    Author     : luisa
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head><title>Resultado Devolución</title></head>
<body>

<jsp:useBean id="bdD" class="bean.Libros" />
<jsp:setProperty name="bdD" property="usuario" param="usuario" />
<jsp:setProperty name="bdD" property="idLibro" param="idLibro" />
<jsp:setProperty name="bdD" property="opcion"  value="altaD" />

<h2>Resultado</h2>
<p><strong><jsp:getProperty name="bdD" property="respuesta" /></strong></p>

<a href="Devoluciones.jsp">Realizar otra devolución</a>
</body>
</html>