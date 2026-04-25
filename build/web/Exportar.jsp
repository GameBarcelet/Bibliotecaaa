<%-- 
    Document   : Exportar
    Created on : 20 mar. 2026, 09:18:15
    Author     : angel
--%><%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="bd" class="bean.Usuarios" />
<!DOCTYPE html>
<html>
    <head>
        <title>Exportar</title>
    </head>
    <body>
        <% 
            // Primero cargamos los datos para que no exporte un archivo vacio
            bd.setOpcion("muestraTabla"); 
            
            // Crea el archivo en C:\dell\datos.xls
            bd.setOpcion("exportar"); 
        %>

        <h2>Resultado:</h2>
        <p><%= bd.getRespuesta() %></p>

        <br>
        <a href="Muestra.jsp">Regresar a la tabla</a><br>
        <a href="MenuAdmin.html">Inicio</a>
    </body>
</html>