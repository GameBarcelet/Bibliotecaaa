<%-- 
    Document   : Consulta
    Created on : 25 mar. 2026, 23:02:46
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta de Libros</title>
    </head>
    <body>
        <h2>Catálogo de Libros</h2>
        <p>Explora los títulos que tenemos disponibles en la biblioteca.</p>
        
        <%-- Aquí llamamos a Java para que imprima la tabla --%>
        <jsp:useBean id="bd" class="bean.Usuarios">
            <jsp:setProperty name="bd" property="opcion" value="consultaLibros" />
            <jsp:getProperty name="bd" property="respuesta" />
        </jsp:useBean>
        
    </body>
</html>