<%-- 
    Document   : Devoluciones
    Created on : 10 abr 2026, 19:36:16
    Author     : luisa
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*, bean.Conexion" %>
<%
    String u = request.getParameter("usuario");
    String titulo = request.getParameter("titulo");
    String idLibro = request.getParameter("idLibro");
    if (u == null) u = "";
    if (titulo == null) titulo = "";
    if (idLibro == null) idLibro = "";
%>
<html>
<head><title>Devoluciones</title></head>
<body>
<h2>Devoluciones</h2>

<form action="Devoluciones.jsp" method="post">

    Usuario:
    <p>
    <%
        try {
            Connection c1 = Conexion.conectar();
            PreparedStatement ps1 = c1.prepareStatement(
                "SELECT DISTINCT Matricula, NombreCompleto FROM UsuariosPrestamos ORDER BY NombreCompleto");
            ResultSet rs1 = ps1.executeQuery();
            out.print("<select name='usuario' onchange='this.form.submit()'>");
            out.print("<option value=''>-- Selecciona --</option>");
            while (rs1.next()) {
                String n = rs1.getString("NombreCompleto");
                String sel = n.equals(u) ? " selected" : "";
                out.print("<option value='" + n + "'" + sel + ">" + n + "</option>");
            }
            out.print("</select>");
            rs1.close(); ps1.close(); c1.close();
        } catch (Exception e) {
            out.print("Error usuarios: " + e);
        }
    %>
    </p>

    <% if (!u.isEmpty()) { %>
    Libro:
    <p>
    <%
        try {
            Connection c2 = Conexion.conectar();
            PreparedStatement ps2 = c2.prepareStatement(
                "SELECT DISTINCT Titulo FROM LibrosPrestamos WHERE NombreCompleto = ?");
            ps2.setString(1, u);
            ResultSet rs2 = ps2.executeQuery();
            out.print("<select name='titulo' onchange='this.form.submit()'>");
            out.print("<option value=''>-- Selecciona --</option>");
            while (rs2.next()) {
                String tit = rs2.getString("Titulo");
                String sel = tit.equals(titulo) ? " selected" : "";
                out.print("<option value='" + tit + "'" + sel + ">" + tit + "</option>");
            }
            out.print("</select>");
            rs2.close(); ps2.close(); c2.close();
        } catch (Exception e) {
            out.print("Error libros: " + e);
        }
    %>
    </p>
    <% } %>

    <% if (!u.isEmpty() && !titulo.isEmpty()) { %>
    Id Libro:
    <p>
    <%
        try {
            Connection c3 = Conexion.conectar();
            PreparedStatement ps3 = c3.prepareStatement(
                "SELECT IdLibro FROM LibrosPrestamos WHERE NombreCompleto = ? AND Titulo = ?");
            ps3.setString(1, u);
            ps3.setString(2, titulo);
            ResultSet rs3 = ps3.executeQuery();
            out.print("<select name='idLibro'>");
            out.print("<option value=''>-- Selecciona --</option>");
            while (rs3.next()) {
                String idL = rs3.getString("IdLibro");
                out.print("<option value='" + idL + "'>" + idL + "</option>");
            }
            out.print("</select>");
            rs3.close(); ps3.close(); c3.close();
        } catch (Exception e) {
            out.print("Error idLibro: " + e);
        }
    %>
    </p>

    <input type="submit" value="Devolver" onclick="this.form.action='Devolver.jsp'">
    <% } %>

</form>

<a href="index.html">Volver al login</a>
</body>
</html>