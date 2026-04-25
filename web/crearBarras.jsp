<%-- 
    Document   : Muestra
    Created on : 20 mar. 2026, 09:18:48
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Muestra Tabla</title>
    </head>
    <body>
        <jsp:useBean id="bd" class="bean.Usuarios">
            <jsp:setProperty name="bd" property="opcion" value="crearBarras" />
            <jsp:getProperty name="bd" property="respuesta" />
            <h1><br><%
                HttpSession sesion = request.getSession(true);
                sesion.setAttribute("titulos", bd.getTitulos());
                sesion.setAttribute("datos", bd.getDatos());
                    %>
                </jsp:useBean>
            <a href='Exportar.jsp'>Exportar </a><p> </p>     
            <a href='crearPdf.jsp'>Crear PDF </a><p> </p>
            <a href='crearBarras.jsp'>Crear Barras </a><p> </p>
            <a href='crearPie.jsp'>Crear Pie </a><p> </p>
            <a href='MenuAdmin.html'>Inicio </a></h1>
    </body>
</html>
