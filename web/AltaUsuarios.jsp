<%-- 
    Document   : AltaUsuario
    Created on : 19 mar. 2026, 23:25:36
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="bd" class="bean.Usuarios" />

<%
    String mat = request.getParameter("matricula");
    String nom = request.getParameter("nombre");
    String pat = request.getParameter("paterno");
    String mater = request.getParameter("materno");
    String mail = request.getParameter("mail");
    String dom = request.getParameter("domicilio");
    String tel = request.getParameter("telefono");
    
    if (mat != null && nom != null) {
        bd.setUsuario(mat); 
        bd.setNombre(nom);
        bd.setPaterno(pat);
        bd.setMaterno(mater);
        bd.setMail(mail);
        // bd.setDomicilio(dom); 
        // bd.setTelefono(tel);
        bd.setOpcion("altaUsuario");
        out.print("<h3>" + bd.getRespuesta() + "</h3>");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Alta Usuarios</title>
    </head>
    <body>
        <h2>Alta Usuarios</h2>
        
        <form action="AltaUsuarios.jsp" method="post">
            <label>Matricula</label> 
            <input type="text" name="matricula" required><br><br>
            
            <label>Nombre(s)</label>
            <input type="text" name="nombre" required>
            
            <label>Apellido Paterno</label>
            <input type="text" name="paterno" required>
            
            <label>Apellido Materno</label>
            <input type="text" name="materno" required><br><br>
            
            <label>mail</label> 
            <input type="email" name="mail"><br><br>
            
            <label>domicilio</label> 
            <input type="text" name="domicilio" size="50"><br><br>
            
            <label>telefono</label> 
            <input type="text" name="telefono"><br><br>
            
            <input type="submit" value="Guardar">
        </form>
    </body>
</html>