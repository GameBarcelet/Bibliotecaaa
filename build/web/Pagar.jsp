<%-- 
    Document   : Pagar
    Created on : 15 abr 2026, 22:39:47
    Author     : luisa
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*, bean.Conexion" %>
<%
    String idM = request.getParameter("idM");
    String respuesta = "";

    if (idM == null || idM.isEmpty()) {
        respuesta = "Faltan datos";
    } else {
        Connection con = null;
        try {
            con = Conexion.conectar();
            CallableStatement cs = con.prepareCall("{call PagarMulta(?,?)}");
            cs.setInt(1, Integer.parseInt(idM));
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.execute();
            respuesta = cs.getString(2);
            cs.close();
        } catch (Exception e) {
            respuesta = "Error: " + e;
        } finally {
            if (con != null) try { con.close(); } catch (Exception ex) {}
        }
    }
%>
<html>
<head><title>Pago de Multa</title></head>
<body>
<h2>Resultado</h2>
<p><strong><%= respuesta %></strong></p>
<br>
<a href="PagoMultas.jsp">Realizar otro pago</a>
</body>
</html>
