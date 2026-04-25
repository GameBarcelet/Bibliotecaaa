/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Types;

public class Libros {

    private String titulo;
    private String opcion;
    private String usuario;
    private String respuesta;
    private int idLibro;

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
    
    

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
        
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        if (opcion != null) {
            if (opcion.equals("buscaL")) {
                buscaLibrosDisponibles();
            } else if (opcion.equals("buscaId")) {
                buscaIdDelLibro();
            }else if (opcion.equals("altaD")) {
    devolver();
}
        }
        return respuesta;
    }

    private void buscaLibrosDisponibles() {
        try {
            Connection c = Conexion.conectar();
            PreparedStatement ps = c.prepareStatement("SELECT titulo FROM Libro WHERE Cantidad > 0");
            ResultSet rs = ps.executeQuery();

            respuesta = "<select name='titulo'>";
            while (rs.next()) {
                String tit = rs.getString("titulo");
                respuesta += "<option value='" + tit + "'>" + tit + "</option>";
            }
            respuesta += "</select>";

            c.close();
        } catch (Exception e) {
            respuesta = "Error al cargar libros: " + e.getMessage();
        }
    }

    private void buscaIdDelLibro() {
    try {
        Connection c = Conexion.conectar();
        PreparedStatement ps = c.prepareStatement(
            "SELECT lc.IdLibro FROM Libro l " +
            "INNER JOIN LibroCantidad lc ON l.IdL = lc.IdL " +
            "WHERE l.titulo = ?"
        );
        ps.setString(1, titulo);
        ResultSet rs = ps.executeQuery();

        respuesta = "<select name='idLibro'>";
        boolean hayResultados = false;
        while (rs.next()) {
            hayResultados = true;
            String id = rs.getString("IdLibro");
            respuesta += "<option value='" + id + "'>" + id + "</option>";
        }
        respuesta += "</select>";

        if (!hayResultados) {
            respuesta = "<select name='idLibro'><option value='0'>Sin copias disponibles</option></select>";
        }

        c.close();
    } catch (Exception e) {
        respuesta = "Error al buscar ID: " + e.getMessage();
    }
}
    private void devolver() {
    try {
        Connection c = Conexion.conectar();
        if (c != null) {
            CallableStatement cs = c.prepareCall("{call Devolver(?,?)}");
            cs.setInt(1, idLibro);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            respuesta = cs.getString(2);
            cs.close();
            c.close();
        } else {
            respuesta = "No hay conexión a la base";
        }
    } catch (Exception e) {
        respuesta = "Error al devolver: " + e.getMessage();
    }
}
}
