<%-- 
    Document   : Prestamos
    Created on : 19 mar. 2026, 23:19:40
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="BuscaLibros.jsp" method="post">

    Usuario <br>
    <jsp:useBean id="bd" class="bean.Usuarios">
        <jsp:setProperty name="bd" property="opcion" value="buscaU" />
        <jsp:getProperty name="bd" property="respuesta" />
    </jsp:useBean>

    <br><br>
    Libros <br>
    <jsp:useBean id="bd2" class="bean.Libros">
        <jsp:setProperty name="bd2" property="opcion" value="buscaL" />
        <jsp:getProperty name="bd2" property="respuesta" />
    </jsp:useBean>

    <br><br>
    <input type="submit" value="Busca">
    
</form>