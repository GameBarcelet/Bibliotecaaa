<%-- 
    Document   : GuardaPrestamo
    Created on : 26 mar. 2026, 10:50:43
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Préstamo Registrado</title>
    </head>
    <body>
        <jsp:useBean id="bd" class="bean.Usuarios">
            <jsp:setProperty name="bd" property="*" />
            <jsp:setProperty name="bd" property="opcion" value="altaPrestamo" />
            
            <h2><jsp:getProperty name="bd" property="respuesta" /></h2>
        </jsp:useBean>
        
        <br><br>
        <a href="Prestamos.jsp">Hacer otro préstamo</a>
    </body>
</html>
