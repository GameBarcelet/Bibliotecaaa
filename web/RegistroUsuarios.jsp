<%-- 
    Document   : RegistroUsuarios
    Created on : 12 mar. 2026, 08:54:28
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id='bd' class='bean.Usuarios'>
            <jsp:useProperty name="bd" property="matricula" values="<%=request.getParameter("nombre")%>"/>
            <jsp:useProperty name="bd" property="paterno" values="<%=request.getParameter("paterno")%>"/>
            <jsp:useProperty name="bd"  property="materno" values="<%=request.getParameter("materno")%>"/>
            <jsp:useProperty name="bd"  property="mail" values="<%=request.getParameter("mail")%>"/>
            <jsp:useProperty name="bd"  property="tipo" values="<%=request.getParameter("tipo")%>"/>
            <jsp:useProperty name="bd"  property="domicilio" values="<%=request.getParameter("domicilio")%>"/>
            <jsp:useProperty name="bd"  property="telefono" values="<%=request.getParameter("telefono")%>"/>
            <jsp:setProperty name="bd" property="opcion" value="AltaUsuario"/>
            </jsp:useBean>
        <a href="MenuAdmin.html">Regresar</a>
    </body>
</html>
