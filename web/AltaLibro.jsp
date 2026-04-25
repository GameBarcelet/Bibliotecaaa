<%-- 
    Document   : AltaLibro
    Created on : 20 mar. 2026, 09:46:43
    Author     : angel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %> 
<jsp:useBean id="bd" class="bean.Usuarios" />

<%
    // 1. Recibimos los datos si el usuario presionó Guardar
    String tit = request.getParameter("titulo");
    String aut = request.getParameter("autor");
    String edi = request.getParameter("editorial");
    String can = request.getParameter("cantidad");
    
    if (tit != null && aut != null && edi != null && can != null) {
        bd.setTitulo(tit);
        bd.setAutor(aut);
        bd.setEditorial(edi);
        bd.setCantidad(Integer.parseInt(can));
        bd.setOpcion("altaLibro");
        out.print("<h3>" + bd.getRespuesta() + "</h3><hr>");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Alta de Libros</title>
    </head>
    <body>
        <h2>Registro de Libros</h2>
        
        <form action="AltaLibro.jsp" method="post">
            <label>Titulo del Libro:</label><br>
            <input type="text" name="titulo" size="40" required><br><br>
            
            <label>Autor:</label><br>
            <% 
                bd.setOpcion("comboAutor");
                out.print(bd.getRespuesta());
            %>
            <br><br>
            
            <label>Editorial:</label><br>
            <% 
                bd.setOpcion("comboEditorial");
                out.print(bd.getRespuesta());
            %>
            <br><br>
            
            <label>Cantidad de Ejemplares:</label><br>
            <input type="number" name="cantidad" min="1" max="10" required><br><br>
            
            <input type="submit" value="Guardar">        
    </body>
</html>