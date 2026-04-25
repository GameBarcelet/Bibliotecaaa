<%-- 
    Document   : ModificaTipo
    Created on : 12 mar. 2026, 08:52:59
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="bd" class="bean.Usuarios" />

<%
    String u = request.getParameter("usuario");
    String t = request.getParameter("tipo");

    if (u != null && t != null) {
        bd.setUsuario(u);
        bd.setTipo(t);
        bd.setOpcion("modificaT");
        out.print(bd.getRespuesta() + "<br>");
    }
    
    bd.setOpcion("llenaS");
%>

<form action="ModificaTipo.jsp" method="post">
    
    Usuario: <%= bd.getRespuesta() %>
    
    <br>
    
    Tipo: 
    <select name="tipo">
        <option value="A"> Administrador </option>
        <option value="L"> Alumno </option>
        <option value="I"> Invitado </option>
        <option value="P"> Profesor </option>
    </select>
    
    <br>
    
    <input type="submit" value="Guardar">
    
</form>
    </body>
</html>
