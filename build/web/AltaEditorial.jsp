<%-- 
    Document   : AltaEditorial
    Created on : 19 mar. 2026, 23:24:04
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="bd" class="bean.Usuarios" />

<%
    String nomE = request.getParameter("nombreE");
    
    if (nomE != null) {
        bd.setNombre(nomE); 
        bd.setOpcion("altaEditorial"); 
        out.print("<h3>" + bd.getRespuesta() + "</h3>");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Alta Editorial</title>
    </head>
    <body>
        <h2>Editorial</h2>
        
        <form action="AltaEditorial.jsp" method="post">
            <label>Nombre</label>
            <input type="text" name="nombreE" required>
            <br><br>
            <input type="submit" value="Guardar">
            <br>         
        </form>
    </body>
</html>
