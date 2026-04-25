<%-- 
    Document   : Registro
    Created on : 4 mar. 2026, 16:34:41
    Author     : angel
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Procesando Registro</title>
    </head>
    <body>
        <jsp:useBean id="bd" class="bean.Usuarios"/>

        <%
            // 1. Capturamos los datos del formulario como Strings
            String r = request.getParameter("matricula");
            String n = request.getParameter("nombre");
            String p = request.getParameter("paterno");
            String m = request.getParameter("materno");
            String e = request.getParameter("mail");
            String i = request.getParameter("tipo");
            String d = request.getParameter("domicilio");
            String t = request.getParameter("telefono");

            // 2. Validamos que la matrícula no llegue nula
            if (r != null && !r.isEmpty()) {
                try {
                    // SOLUCIÓN AL ERROR 500: Convertimos el String a int manualmente
                    int matriculaNumerica = Integer.parseInt(r);
                    bd.setMatricula(matriculaNumerica); 
                    
                    // 3. Pasamos el resto de los textos al Bean
                    bd.setNombre(n);
                    bd.setPaterno(p);
                    bd.setMaterno(m);
                    bd.setMail(e);
                    bd.setTipo(i);
                    bd.setDomicilio(d);
                    bd.setTelefono(t);
                    
                    // 4. Ejecutamos la lógica de registro
                    bd.setOpcion("registro");
                    
                    // 5. Imprimimos el resultado (Usuario y Password generados)
                    out.print(bd.getRespuesta());
                    
                } catch (NumberFormatException ex) {
                    out.print("<h3>Error: La matrícula debe ser un número válido.</h3>");
                }
            } else {
                out.print("<h3>Error: Faltan datos obligatorios.</h3>");
            }
        %>

        <br><br>
        <a href="Registro.html">Volver al formulario</a>
    </body>
</html>