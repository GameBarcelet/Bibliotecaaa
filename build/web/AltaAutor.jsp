<%-- 
    Document   : AltaAutor
    Created on : 19 mar. 2026, 23:25:08
    Author     : angel
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="bd" class="bean.Usuarios" />

<%
    String nom = request.getParameter("nombreA");
    String pat = request.getParameter("paternoA");
    String mat = request.getParameter("maternoA");
    
    if (nom != null && pat != null && mat != null) {
        bd.setNombre(nom);
        bd.setPaterno(pat);
        bd.setMaterno(mat);
        bd.setOpcion("altaAutor");
        out.print("<h3>" + bd.getRespuesta() + "</h3>");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Alta Autores</title>
    </head>
    <body>
        <h2>Alta Autores</h2>
        
        <form action="AltaAutor.jsp" method="post">
            <input type="text" name="nombreA" placeholder="Nombre(s)" required>
            <input type="text" name="paternoA" placeholder="Apellido Paterno" required>
            <input type="text" name="maternoA" placeholder="Apellido Materno" required>
            <br><br>
            <input type="submit" value="Guardar">
            <br> 
        </form>
    </body>
</html>