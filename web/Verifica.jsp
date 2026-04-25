<%-- 
    Document   : verifica
    Created on : 12 mar. 2026, 08:52:41
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
        <%

String u = request.getParameter("usuario");
String p = request.getParameter("pass");

%>

<jsp:useBean id="bd" class="bean.Usuarios"/>

<jsp:setProperty name="bd" property="usuario" value="<%=u%>"/>
<jsp:setProperty name="bd" property="pass" value="<%=p%>"/>

<jsp:setProperty name="bd" property="opcion" value="verifica"/>

<%
String r = bd.getRespuesta();

// Primero verificamos que 'r' NO sea nulo antes de preguntar en qué termina
if(r != null && (r.endsWith(".html") || r.contains(".jsp"))){
    response.sendRedirect(r);
} else {
    // Si r es nulo, imprimimos un mensaje personalizado en lugar de que el servidor explote
    if(r == null){
        out.print("Error de sistema: La respuesta llegó nula. ¿Llenaste el formulario?");
    } else {
        out.print(r);
    }
}
%>
    </body>
</html>
