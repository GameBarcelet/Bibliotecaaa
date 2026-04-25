<%-- 
    Document   : PagoMultas
    Created on : 15 abr 2026, 22:39:16
    Author     : luisa
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*, bean.Conexion" %>
<%
    String u = request.getParameter("usuario");
    String titulo = request.getParameter("titulo");
    if (u == null) u = "";
    if (titulo == null) titulo = "";
%>
<html>
<head><title>Pago de Multas</title></head>
<body>
<h2>Pago de Multas</h2>

<form action="PagoMultas.jsp" method="post">

    Usuario:
    <p>
    <%
        try {
            Connection c1 = Conexion.conectar();
            PreparedStatement ps1 = c1.prepareStatement(
                "SELECT DISTINCT Matricula, NombreCompleto FROM MultasPendientes ORDER BY NombreCompleto");
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
                "SELECT DISTINCT Titulo FROM MultasPendientes WHERE NombreCompleto = ?");
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
                "SELECT IdM, IdLibro, Monto FROM MultasPendientes WHERE NombreCompleto = ? AND Titulo = ?");
            ps3.setString(1, u);
            ps3.setString(2, titulo);
            ResultSet rs3 = ps3.executeQuery();
            if (rs3.next()) {
                String idM   = rs3.getString("IdM");
                String idL   = rs3.getString("IdLibro");
                String monto = rs3.getString("Monto");
                out.print("<input type='text' value='" + idL + "' readonly />");
                out.print("<input type='hidden' name='idM' value='" + idM + "' />");
                out.print("<br><br>Monto a pagar: <strong>$" + monto + "</strong>");
            }
            rs3.close(); ps3.close(); c3.close();
        } catch (Exception e) {
            out.print("Error idLibro: " + e);
        }
    %>
    </p>

    <input type="submit" value="Pagar" onclick="this.form.action='Pagar.jsp'">
    <% } %>

</form>

<a href="index.html">Volver al login</a>
</body>
</html>