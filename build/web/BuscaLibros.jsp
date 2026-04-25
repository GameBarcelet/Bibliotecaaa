<%-- 
    Document   : BuscaLibros
    Created on : 26 mar. 2026, 09:36:15
    Author     : angel
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String u = request.getParameter("usuario");
    String t = request.getParameter("titulo");
%>

<!DOCTYPE html>
<html>
<head><title>Confirmar Préstamo</title></head>
<body>

<h2>Confirmar Préstamo</h2>

<form action="GuardaPrestamo.jsp" method="post">

    Usuario <br>
    <input type="text" name="usuario" value="<%=u%>" readonly /><br><br>

    Libro <br>
    <input type="text" name="titulo" value="<%=t%>" readonly /><br><br>

    IdLibro <br>
    <jsp:useBean id="bd" class="bean.Libros">
        <jsp:setProperty name="bd" property="titulo" value="<%=t%>" />
        <jsp:setProperty name="bd" property="opcion" value="buscaId" />
        <jsp:getProperty name="bd" property="respuesta" />
    </jsp:useBean>
    <br><br>

    <input type="submit" value="Prestamo">

</form>
</body>
</html>