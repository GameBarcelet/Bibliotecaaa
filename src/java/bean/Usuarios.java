/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.*;
import java.io.*;

public class Usuarios {

    private String nombre, paterno, materno, mail, respuesta, usuario, pass, tipo, opcion;
    private String domicilio, telefono;
    private int matricula;
    private String titulo, autor, editorial;
    private int cantidad;
    private int idLibro;
    private String[] titulos;
    private String[][] datos;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String[] getTitulos() {
        return titulos;
    }

    public void setTitulos(String[] titulos) {
        this.titulos = titulos;
    }

    public String[][] getDatos() {
        return datos;
    }

    public void setDatos(String[][] datos) {
        this.datos = datos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;

        if (opcion.equals("registro") || opcion.equals("altaUsuario")) {
            altaUsuario();
        }
        if (opcion.equals("verifica")) {
            buscaUsuario();
        }
        if (opcion.equals("llenaS")) {
            llenarSelector();
        }
        if (opcion.equals("modificaT")) {
            modificarTipo();
        }
        if (opcion.equals("muestraTabla")) {
            muestraTabla();
        }
        if (opcion.equals("altaEditorial")) {
            altaEditorial();
        }
        if (opcion.equals("altaAutor")) {
            altaAutor();
        }
        if (opcion.equals("comboAutor")) {
            llenaComboAutor();
        }
        if (opcion.equals("comboEditorial")) {
            llenaComboEditorial();
        }
        if (opcion.equals("altaLibro")) {
            altaLibro();
        }
        if (opcion.equals("exportar")) {
            exportar();
        }
        if (opcion.equals("crearPdf")) {
            crearPdf();
        }
        if (opcion.equals("generaPie")) {
            generaPie();
        }
        if (opcion.equals("generaBarras")) {
            generaBarras();
        }
        if (opcion.equals("crearBarras")) {
            crearBarras();
        }
        if (opcion.equals("crearPie")) {
            crearPie();
        }
        if (opcion.equals("consultaLibros")) {
            consultaLibros();
        }
        if (opcion.equals("buscaU")) {
            buscaAlumnos();
        }
        if (opcion.equals("altaPrestamo")) {
            altaPrestamo();
        }
        if (opcion.equals("buscaP")) {
            buscaProfesores();
        }
        if (opcion.equals("buscaI")) {
            buscaInvitados();
        }
    }

    public int generaAleatorio() {
        return (int) (Math.random() * 100);
    }

    public void altaUsuario() {
        int y = generaAleatorio();
        String r = nombre.substring(0, Math.min(3, nombre.length()));
        r += paterno.substring(0, Math.min(3, paterno.length()));
        r += materno.substring(0, Math.min(3, materno.length()));

        if (y < 10) {
            r += "00" + y;
        } else if (y < 100) {
            r += "0" + y;
        } else {
            r += y;
        }

        usuario = r;
        String passSinEncriptar = r;
        pass = MD5.encriptar(passSinEncriptar);

        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                CallableStatement cs = c.prepareCall("{call AltaUsuarios2(?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setInt(1, matricula);
                cs.setString(2, nombre);
                cs.setString(3, materno);
                cs.setString(4, paterno);
                cs.setString(5, mail);
                cs.setString(6, tipo);
                
                cs.setString(7, domicilio);
                cs.setString(8, telefono);
                cs.setString(9, usuario);
                cs.setString(10, pass);
                cs.registerOutParameter(11, Types.VARCHAR);
                cs.execute();
                respuesta = cs.getString(11) + "<br><br>Tu usuario es: " + usuario + "<br>Tu password es: " + passSinEncriptar;
                cs.close();
                c.close();
            } else {
                respuesta = "No hay conexion a la base";
            }
        } catch (Exception e) {
            respuesta = "Error al guardar usuario: " + e.getMessage();
        }
    }

    public void modificarTipo() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                PreparedStatement ps = c.prepareStatement("update Usuarios set tipo=? where usuario=?");
                ps.setString(1, tipo);
                ps.setString(2, usuario);
                ps.execute();
                respuesta = "Tipo modificado";
            } else {
                respuesta = "No hay conexion a la base";
            }
        } catch (Exception er) {
            respuesta = "Error al modificar tipo " + er;
        }
    }

    public void llenarSelector() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                PreparedStatement ps = c.prepareStatement("select * from Acceso");
                ResultSet rs = ps.executeQuery();
                respuesta = "<select name='usuario'>";
                while (rs.next()) {
                    String u = rs.getString("usuario");
                    respuesta += "<option value='" + u + "'>" + u + "</option>";
                }
                respuesta += "</select>";
            } else {
                respuesta = "Error.jsp?respuesta=No hay conexion en la base";
            }
        } catch (Exception e) {
            respuesta = "Error.jsp?respuesta=Error al llenar Selector " + e;
        }
    }

    public void buscaUsuario() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                PreparedStatement ps = c.prepareStatement("select * from Acceso where usuario=? and password=?");
                ps.setString(1, usuario);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String t = rs.getString("tipo");
                    if (t.equals("I")) {
                        respuesta = "index5.html";
                    } else if (t.equals("A")) {
                        respuesta = "index2.html";
                    } else if (t.equals("P")) {
                        respuesta = "index4.html";
                    } else if (t.equals("L")) {
                        respuesta = "index3.html";
                    }
                } else {
                    respuesta = "Error.jsp?respuesta=Usuario o contraseña incorrectos";
                }
            } else {
                respuesta = "Error.jsp?respuesta=No hay conexion en la base";
            }
        } catch (Exception e) {
            respuesta = "Error.jsp?respuesta=Error al buscar " + e;
        }
    }
    
    public void buscaProfesores() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                PreparedStatement ps = c.prepareStatement("SELECT concat(nombre,' ',paterno,' ',materno) as nombreCompleto FROM Usuarios WHERE tipo = 'P'");
                ResultSet rs = ps.executeQuery();
                
                respuesta = "<select name='usuario'>";
                while (rs.next()) {
                    String nom = rs.getString("nombreCompleto");
                    respuesta += "<option value='" + nom + "'>" + nom + "</option>";
                }
                respuesta += "</select>";
                
                c.close();
            }
        } catch (Exception e) {
            respuesta = "Error: " + e;
        }
    }

    public void buscaInvitados() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                PreparedStatement ps = c.prepareStatement("SELECT concat(nombre,' ',paterno,' ',materno) as nombreCompleto FROM Usuarios WHERE tipo = 'D'");
                ResultSet rs = ps.executeQuery();
                
                respuesta = "<select name='usuario'>";
                while (rs.next()) {
                    String nom = rs.getString("nombreCompleto");
                    respuesta += "<option value='" + nom + "'>" + nom + "</option>";
                }
                respuesta += "</select>";
                
                c.close();
            }
        } catch (Exception e) {
            respuesta = "Error: " + e;
        }
    }

    public void consultaLibros() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                Statement ps = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ps.executeQuery("SELECT * FROM Libro");
                int filas = 0, columnas = 0;
                if (rs.next()) {
                    columnas = rs.getMetaData().getColumnCount();
                    rs.last();
                    filas = rs.getRow();
                    titulos = new String[columnas];
                    datos = new String[filas][columnas];
                    for (int i = 0; i < titulos.length; i++) {
                        titulos[i] = rs.getMetaData().getColumnName((i + 1));
                    }
                    rs.first();
                    for (int i = 0; i < datos.length; i++) {
                        for (int j = 0; j < datos[i].length; j++) {
                            datos[i][j] = rs.getString((j + 1));
                        }
                        rs.next();
                    }
                    crearTabla(titulos, datos);
                } else {
                    respuesta = "<h3>Aún no hay libros registrados en la biblioteca.</h3>";
                }
            } else {
                respuesta = "No hay conexion a la base de datos";
            }
        } catch (Exception e) {
            respuesta = "Error al mostrar libros: " + e;
        }
    }

    public void altaEditorial() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                CallableStatement cs = c.prepareCall("{call AltaEditorial(?,?)}");
                cs.setString(1, nombre);
                cs.registerOutParameter(2, Types.VARCHAR);
                cs.execute();
                respuesta = cs.getString(2);
                cs.close();
                c.close();
            } else {
                respuesta = "No hay conexion a la base";
            }
        } catch (Exception e) {
            respuesta = "Error al guardar Editorial: " + e.getMessage();
        }
    }

    public void altaAutor() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                CallableStatement cs = c.prepareCall("{call AltaAutor(?,?,?,?)}");
                cs.setString(1, nombre);
                cs.setString(2, paterno);
                cs.setString(3, materno);
                cs.registerOutParameter(4, Types.VARCHAR);
                cs.execute();
                respuesta = cs.getString(4);
                cs.close();
                c.close();
            } else {
                respuesta = "No hay conexion a la base";
            }
        } catch (Exception e) {
            respuesta = "Error al guardar Autor: " + e.getMessage();
        }
    }

    public void llenaComboAutor() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                PreparedStatement ps = c.prepareStatement("SELECT CONCAT(NombreA, ' ', PaternoA, ' ', MaternoA) AS nombreCompleto FROM Autor WHERE estatusA = 'A'");
                ResultSet rs = ps.executeQuery();
                respuesta = "<select name='autor' required>";
                respuesta += "<option value=''>Selecciona un autor...</option>";
                while (rs.next()) {
                    String nom = rs.getString("nombreCompleto");
                    respuesta += "<option value='" + nom + "'>" + nom + "</option>";
                }
                respuesta += "</select>";
            }
        } catch (Exception e) {
            respuesta = "Error al llenar combo Autor: " + e.getMessage();
        }
    }

    public void llenaComboEditorial() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                PreparedStatement ps = c.prepareStatement("SELECT NombreE FROM Editorial WHERE estatusE = 'A'");
                ResultSet rs = ps.executeQuery();
                respuesta = "<select name='editorial' required>";
                respuesta += "<option value=''>Selecciona una editorial...</option>";
                while (rs.next()) {
                    String nom = rs.getString("NombreE");
                    respuesta += "<option value='" + nom + "'>" + nom + "</option>";
                }
                respuesta += "</select>";
            }
        } catch (Exception e) {
            respuesta = "Error al llenar combo Editorial: " + e.getMessage();
        }
    }

    public void altaLibro() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                CallableStatement cs = c.prepareCall("{call AltaLibro(?,?,?,?,?)}");
                cs.setString(1, titulo);
                cs.setString(2, autor);
                cs.setString(3, editorial);
                cs.setInt(4, cantidad);
                cs.registerOutParameter(5, Types.VARCHAR);
                cs.execute();
                respuesta = cs.getString(5);
                cs.close();
                c.close();
            } else {
                respuesta = "No hay conexion a la base";
            }
        } catch (Exception e) {
            respuesta = "Error al guardar Libro: " + e.getMessage();
        }
    }

    public void muestraTabla() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                Statement ps = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ps.executeQuery("select * from Usuarios");
                int filas = 0, columnas = 0;
                if (rs.next()) {
                    columnas = rs.getMetaData().getColumnCount();
                    rs.last();
                    filas = rs.getRow();
                    titulos = new String[columnas];
                    datos = new String[filas][columnas];
                    for (int i = 0; i < titulos.length; i++) {
                        titulos[i] = rs.getMetaData().getColumnName((i + 1));
                    }
                    rs.first();
                    for (int i = 0; i < datos.length; i++) {
                        for (int j = 0; j < datos[i].length; j++) {
                            datos[i][j] = rs.getString((j + 1));
                        }
                        rs.next();
                    }
                    crearTabla(titulos, datos);
                } else {
                    respuesta = "Error.jsp?respuesta=No hay datos en la tabla";
                }
            } else {
                respuesta = "Error.jsp?respuesta='No hay conexion en la base'";
            }
        } catch (Exception e) {
            respuesta = "Error.jsp?respuesta=Error al mostrar tabla" + e;
        }
    }

    public void crearTabla(String t[], String d[][]) {
        String te = "<table border='1'><tr>";
        for (int i = 0; i < t.length; i++) {
            te += "<th>" + t[i] + "</th>";
        }
        te += "</tr>";
        for (int i = 0; i < d.length; i++) {
            te += "<tr>";
            for (int j = 0; j < d[i].length; j++) {
                te += "<td>" + d[i][j] + "</td>";
            }
            te += "</tr>";
        }
        te += "</table>";
        respuesta = te;
    }

    public void exportar() {
        try {
            File f = new File("c:\\dell\\datos.xls");
            FileWriter fw = new FileWriter(f, false);
            for (int i = 0; i < titulos.length; i++) {
                fw.write(titulos[i] + "\t");
            }
            fw.write("\n");
            for (int i = 0; i < datos.length; i++) {
                for (int j = 0; j < datos[i].length; j++) {
                    fw.write(datos[i][j] + "\t");
                }
                fw.write("\n");
            }
            fw.close();
            respuesta = "Datos exportados";
        } catch (Exception e) {
            respuesta = "Error al exportar datos";
        }
    }

    public void crearPdf() {
        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream("c://dell//tablas.pdf"));
            documento.open();
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLUE));
            parrafo.add("\nUsuarios\n\n");
            documento.add(parrafo);
            Image imagen = Image.getInstance("c://usuarios.jpeg");
            imagen.setAlignment(Image.ALIGN_CENTER);
            imagen.scalePercent(50);
            documento.add(imagen);
            parrafo = new Paragraph();
            parrafo.setFont(FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLUE));
            parrafo.add("\n\n\n");
            documento.add(parrafo);
            PdfPTable table = new PdfPTable(4);
            table.addCell("Matricula");
            table.addCell("usuario");
            table.addCell("Password");
            table.addCell("tipo");
            try {
                Connection c = Conexion.conectar();
                if (c != null) {
                    PreparedStatement ps = c.prepareStatement("select * from acceso");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        table.addCell(rs.getString(1));
                        table.addCell(rs.getString(2));
                        table.addCell(rs.getString(3));
                        table.addCell(rs.getString(4));
                    }
                    respuesta = "Archivo Pdf creado";
                } else {
                    respuesta = "No hay conexion a la base";
                }
            } catch (Exception e) {
                respuesta = "Error al generar Pdf " + e;
            }
            documento.add(table);
            documento.close();
        } catch (Exception e) {
            respuesta = "Error al generar pdf " + e;
        }
    }

    public void crearBarras() {
        generaBarras();
        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream("c://dell//rbarras.pdf"));
            documento.open();
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLUE));
            parrafo.add("\nLibros\n\n");
            documento.add(parrafo);
            Image imagen = Image.getInstance("c://dell//barras.jpg");
            imagen.setAlignment(Image.ALIGN_CENTER);
            imagen.scalePercent(50);
            documento.add(imagen);
            parrafo = new Paragraph();
            parrafo.setFont(FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLUE));
            parrafo.add("\n\n\n");
            documento.add(parrafo);
            PdfPTable table = new PdfPTable(4);
            table.addCell("IdL");
            table.addCell("Titulo");
            table.addCell("IdA");
            table.addCell("Cantidad");
            try {
                Connection c = Conexion.conectar();
                if (c != null) {
                    PreparedStatement ps = c.prepareStatement("select IdL, titulo, IdA, Cantidad from Libro");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        table.addCell(rs.getString(1));
                        table.addCell(rs.getString(2));
                        table.addCell(rs.getString(3));
                        table.addCell(rs.getString(4));
                    }
                    respuesta = "Archivo Pdf creado";
                } else {
                    respuesta = "No hay conexion a la base";
                }
            } catch (Exception e) {
                respuesta = "Error al generar Pdf " + e;
            }
            documento.add(table);
            documento.close();
        } catch (Exception e) {
            respuesta = "Error al generar pdf " + e;
        }
    }

    public void crearPie() {
        generaPie();
        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream("c://dell//rpie.pdf"));
            documento.open();
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLUE));
            parrafo.add("\nLibros\n\n");
            documento.add(parrafo);
            Image imagen = Image.getInstance("c://dell//pie.jpg");
            imagen.setAlignment(Image.ALIGN_CENTER);
            imagen.scalePercent(50);
            documento.add(imagen);
            parrafo = new Paragraph();
            parrafo.setFont(FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLUE));
            parrafo.add("\n\n\n");
            documento.add(parrafo);
            PdfPTable table = new PdfPTable(4);
            table.addCell("IdL");
            table.addCell("Titulo");
            table.addCell("IdA");
            table.addCell("Cantidad");
            try {
                Connection c = Conexion.conectar();
                if (c != null) {
                    PreparedStatement ps = c.prepareStatement("select IdL, titulo, IdA, Cantidad from Libro");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        table.addCell(rs.getString(1));
                        table.addCell(rs.getString(2));
                        table.addCell(rs.getString(3));
                        table.addCell(rs.getString(4));
                    }
                    respuesta = "Archivo Pdf creado";
                } else {
                    respuesta = "No hay conexion a la base";
                }
            } catch (Exception e) {
                respuesta = "Error al generar Pdf " + e;
            }
            documento.add(table);
            documento.close();
        } catch (Exception e) {
            respuesta = "Error al generar pdf " + e;
        }
    }

    public void generaPie() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                DefaultPieDataset datos = new DefaultPieDataset();
                PreparedStatement ps = c.prepareStatement("SELECT titulo, Cantidad FROM Libro");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String nombreLibro = rs.getString("titulo");
                    int cantidadLibro = rs.getInt("Cantidad");
                    datos.setValue(nombreLibro, cantidadLibro);
                }
                int largo = 600;
                int ancho = 400;
                JFreeChart grafico = ChartFactory.createPieChart3D("Cantidad de Libros", datos, true, true, true);
                ChartUtilities.saveChartAsJPEG(new File("c://dell/pie.jpg"), grafico, largo, ancho);
                respuesta = "Grafico de pie creado";
                c.close();
            } else {
                respuesta = "No hay conexion a la base para generar la grafica";
            }
        } catch (Exception er) {
            respuesta = "Error al crear grafico de pie: " + er;
        }
    }

    public void generaBarras() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                DefaultCategoryDataset datos = new DefaultCategoryDataset();
                PreparedStatement ps = c.prepareStatement("SELECT titulo, Cantidad FROM Libro");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String nombreLibro = rs.getString("titulo");
                    int cantidadLibro = rs.getInt("Cantidad");
                    datos.addValue(cantidadLibro, nombreLibro, "");
                }
                int largo = 600;
                int ancho = 400;
                JFreeChart grafico = ChartFactory.createBarChart3D("Cantidad de libros", "Libros", "Cantidad", datos, PlotOrientation.HORIZONTAL, true, true, true);
                ChartUtilities.saveChartAsJPEG(new File("c://dell/barras.jpg"), grafico, largo, ancho);
                respuesta = "Grafico de barras creado";
                c.close();
            } else {
                respuesta = "No hay conexion a la base para generar la grafica";
            }
        } catch (Exception er) {
            respuesta = "Error al crear grafico barras: " + er;
        }
    }

    public void buscaAlumnos() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                PreparedStatement ps = c.prepareStatement("SELECT concat(nombreA,' ',paternoA,' ',maternoA) as nombreCompleto FROM Alumno");
                ResultSet rs = ps.executeQuery();
                respuesta = "<select name='usuario'>";
                while (rs.next()) {
                    String nom = rs.getString("nombreCompleto");
                    respuesta += "<option value='" + nom + "'>" + nom + "</option>";
                }
                respuesta += "</select>";
                c.close();
            }
        } catch (Exception e) {
            respuesta = "Error: " + e;
        }
    }

    public void altaPrestamo() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                CallableStatement cs = c.prepareCall("{call AltaPrestamo(?,?,?,?)}");
                cs.setString(1, usuario);
                cs.setString(2, titulo);
                cs.setInt(3, idLibro);
                cs.registerOutParameter(4, Types.VARCHAR);
                cs.execute();
                respuesta = cs.getString(4);
                cs.close();
                c.close();
            } else {
                respuesta = "No hay conexion a la base";
            }
        } catch (Exception e) {
            respuesta = "Error al registrar préstamo: " + e.getMessage();
        }
    }
    public void reporteLibrosPrestamos() {
    generaBarrasLibros();
    try {
        Document documento = new Document();
        PdfWriter.getInstance(documento, new FileOutputStream("c:/dell/reporte_libros_prestamos.pdf"));
        documento.open();

        Font fuenteTitulo = FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.BLUE);
        Paragraph parrafo = new Paragraph("\nLibros con Mayor Número de Préstamos\n\n", fuenteTitulo);
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(parrafo);

        Image imagen = Image.getInstance("c:/dell/barras_libros.jpg");
        imagen.setAlignment(Image.ALIGN_CENTER);
        imagen.scalePercent(50);
        documento.add(imagen);
        documento.add(new Paragraph("\n\n"));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        Font fuenteHeader = FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE);
        String[] headers = {"Título del Libro", "Total Préstamos"};
        for (String h : headers) {
            PdfPCell cell = new PdfPCell(new Paragraph(h, fuenteHeader));
            cell.setBackgroundColor(BaseColor.BLUE);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(cell);
        }

        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                // Ajustado a tu estructura: Prestamos -> LibroCantidad -> Libro
                PreparedStatement ps = c.prepareStatement(
                    "SELECT TOP 10 L.titulo, COUNT(P.IdP) AS TotalPrestamos " +
                    "FROM Prestamos P " +
                    "INNER JOIN LibroCantidad LC ON P.IdLibro = LC.IdLibro " +
                    "INNER JOIN Libro L ON LC.IdL = L.IdL " +
                    "GROUP BY L.titulo " +
                    "ORDER BY TotalPrestamos DESC"
                );
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    table.addCell(rs.getString("titulo"));
                    table.addCell(rs.getString("TotalPrestamos"));
                }
                respuesta = "Reporte de libros creado en c:/dell/";
            }
        } catch (Exception e) {
            respuesta = "Error BD: " + e.getMessage();
        }
        documento.add(table);
        documento.close();
    } catch (Exception e) {
        respuesta = "Error PDF: " + e.getMessage();
    }
}
   

    // REPORTE 3 – USUARIOS CON TOTAL DE PRÉSTAMOS
public void reporteUsuariosPrestamos() {
    generaPieUsuarios(); // Llama a la generación del gráfico circular
    try {
        Document documento = new Document();
        // Ruta ajustada a tu carpeta c:/dell/
        PdfWriter.getInstance(documento, new FileOutputStream("c:/dell/reporte_usuarios_prestamos.pdf"));
        documento.open();

        Font fuenteTitulo = FontFactory.getFont("Arial", 20, Font.BOLD, new BaseColor(0, 128, 0));
        Paragraph parrafo = new Paragraph("\nUsuarios con Total de Préstamos\n\n", fuenteTitulo);
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(parrafo);

        // Insertar la imagen del gráfico de pie
        Image imagen = Image.getInstance("c:/dell/pie_usuarios.jpg");
        imagen.setAlignment(Image.ALIGN_CENTER);
        imagen.scalePercent(50);
        documento.add(imagen);
        documento.add(new Paragraph("\n\n"));

        // Tabla con 4 columnas: Matricula, Nombre Completo, Mail, Total
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        Font fuenteHeader = FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE);
        String[] headers = {"Matrícula", "Nombre Completo", "Mail", "Total Préstamos"};
        for (String h : headers) {
            PdfPCell cell = new PdfPCell(new Paragraph(h, fuenteHeader));
            cell.setBackgroundColor(new BaseColor(0, 128, 0)); // Color verde
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(cell);
        }

        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                // Consulta ajustada a tus columnas: nombre, paterno, materno
                PreparedStatement ps = c.prepareStatement(
                    "SELECT TOP 15 " +
                    "    U.matricula, " +
                    "    CONCAT(U.nombre, ' ', U.paterno, ' ', U.materno) AS NombreCompleto, " +
                    "    U.mail, " +
                    "    COUNT(P.IdP) AS TotalPrestamos " +
                    "FROM Usuarios U " +
                    "INNER JOIN Prestamos P ON P.matricula = U.matricula " +
                    "GROUP BY U.matricula, U.nombre, U.paterno, U.materno, U.mail " +
                    "ORDER BY TotalPrestamos DESC"
                );
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    table.addCell(rs.getString("matricula"));
                    table.addCell(rs.getString("NombreCompleto"));
                    table.addCell(rs.getString("mail"));
                    table.addCell(rs.getString("TotalPrestamos"));
                }
                respuesta = "Reporte de usuarios con préstamos creado correctamente en c:/dell/";
                c.close();
            } else {
                respuesta = "No hay conexión a la base para el reporte";
            }
        } catch (Exception e) {
            respuesta = "Error al consultar BD para reporte: " + e.getMessage();
        }

        documento.add(table);
        documento.close();

    } catch (Exception e) {
        respuesta = "Error al generar PDF usuarios: " + e.getMessage();
    }
}

public void generaPieUsuarios() {
    try {
        DefaultPieDataset datos = new DefaultPieDataset();

        Connection c = Conexion.conectar();
        if (c != null) {
            PreparedStatement ps = c.prepareStatement(
                "SELECT TOP 15 " +
                "    CONCAT(U.nombre, ' ', U.paterno) AS Corto, " +
                "    COUNT(P.IdP) AS TotalPrestamos " +
                "FROM Usuarios U " +
                "INNER JOIN Prestamos P ON P.matricula = U.matricula " +
                "GROUP BY U.nombre, U.paterno " +
                "ORDER BY TotalPrestamos DESC"
            );
            ResultSet rs = ps.executeQuery();
            int filas = 0;
            while (rs.next()) {
                datos.setValue(rs.getString("Corto"), rs.getInt("TotalPrestamos"));
                filas++;
            }
            if (filas == 0) {
                datos.setValue("Sin datos de préstamos", 1);
            }
            c.close();
        }

        JFreeChart grafico = ChartFactory.createPieChart3D(
            "Distribución de Préstamos por Usuario", datos, true, true, true
        );

        File archivo = new File("c:/dell/pie_usuarios.jpg");
        ChartUtilities.saveChartAsJPEG(archivo, grafico, 600, 400);

    } catch (Exception e) {
        System.out.println("Error generaPieUsuarios: " + e.getMessage());
    }
}

public void generaBarrasLibros() {
    try {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();

        Connection c = Conexion.conectar();
        if (c != null) {
            // Ajustado a tu BD: Prestamos -> LibroCantidad -> Libro
            PreparedStatement ps = c.prepareStatement(
                "SELECT TOP 10 L.titulo, COUNT(P.IdP) AS TotalPrestamos " +
                "FROM Prestamos P " +
                "INNER JOIN LibroCantidad LC ON P.IdLibro = LC.IdLibro " +
                "INNER JOIN Libro L ON LC.IdL = L.IdL " +
                "GROUP BY L.titulo " +
                "ORDER BY TotalPrestamos DESC"
            );
            ResultSet rs = ps.executeQuery();
            int filas = 0;
            while (rs.next()) {
                // Se agregan los datos: (Valor, Categoría, Serie)
                datos.addValue(rs.getInt("TotalPrestamos"), rs.getString("titulo"), "");
                filas++;
            }
            // Si no hay préstamos aún, ponemos un dato por defecto para que no falle la gráfica
            if (filas == 0) {
                datos.addValue(0, "Sin préstamos", "");
            }
            c.close();
        } else {
            respuesta = "Error: no hay conexión en generaBarrasLibros";
            return;
        }

        // Crear el gráfico de barras 3D en orientación Horizontal
        JFreeChart grafico = ChartFactory.createBarChart3D(
            "Préstamos por Libro", "Libro", "Cantidad",
            datos, PlotOrientation.HORIZONTAL, true, true, true
        );

        // Guardamos la imagen en tu carpeta dell
        File archivo = new File("c:/dell/barras_libros.jpg");
        ChartUtilities.saveChartAsJPEG(archivo, grafico, 600, 400);

    } catch (Exception e) {
        System.out.println("Error generaBarrasLibros: " + e.getMessage());
    }
}
// REPORTE 2 – MULTAS POR MES (CORREGIDO)
public void reporteMultasPorMes() {
    this.respuesta = ""; // Inicializamos para evitar el 'null' en el JSP
    generaBarrasMultas(); // Genera la imagen para el PDF
    
    try {
        Document documento = new Document();
        PdfWriter.getInstance(documento, new FileOutputStream("c:/dell/reporte_multas_mes.pdf"));
        documento.open();

        Font fuenteTitulo = FontFactory.getFont("Arial", 20, Font.BOLD, BaseColor.RED);
        Paragraph parrafo = new Paragraph("\nCantidad de Multas por Mes\n\n", fuenteTitulo);
        parrafo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(parrafo);

        // Insertar la gráfica generada
        Image imagen = Image.getInstance("c:/dell/barras_multas.jpg");
        imagen.setAlignment(Image.ALIGN_CENTER);
        imagen.scalePercent(50);
        documento.add(imagen);
        documento.add(new Paragraph("\n\n"));

        // Tabla de datos
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);

        Font fuenteHeader = FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.WHITE);
        String[] headers = {"Mes", "Cantidad de Multas", "Monto Total"};
        for (String h : headers) {
            PdfPCell cell = new PdfPCell(new Paragraph(h, fuenteHeader));
            cell.setBackgroundColor(BaseColor.RED);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(cell);
        }

        String[] meses = {"", "Enero","Febrero","Marzo","Abril","Mayo","Junio",
                           "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                // Consulta corregida con 'FechaM' y filtrando por el año actual
                PreparedStatement ps = c.prepareStatement(
                    "SELECT MONTH(FechaM) AS Mes, " +
                    "       COUNT(IdM) AS Cantidad, " +
                    "       SUM(Monto) AS MontoTotal " +
                    "FROM Multas " +
                    "WHERE YEAR(FechaM) = YEAR(GETDATE()) " +
                    "GROUP BY MONTH(FechaM) " +
                    "ORDER BY Mes"
                );
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int numMes = rs.getInt("Mes");
                    table.addCell(meses[numMes]);
                    table.addCell(rs.getString("Cantidad"));
                    table.addCell("$" + rs.getString("MontoTotal"));
                }
                this.respuesta = "Reporte de multas creado correctamente en c:/dell/";
                c.close();
            }
        } catch (Exception e) {
            this.respuesta = "Error en consulta SQL: " + e.getMessage();
        }

        documento.add(table);
        documento.close();

    } catch (Exception e) {
        this.respuesta = "Error al generar PDF: " + e.getMessage();
    }
}

public void generaBarrasMultas() {
    try {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        String[] mesesCortos = {"", "Ene","Feb","Mar","Abr","May","Jun",
                                 "Jul","Ago","Sep","Oct","Nov","Dic"};

        Connection c = Conexion.conectar();
        if (c != null) {
            PreparedStatement ps = c.prepareStatement(
                "SELECT MONTH(FechaM) AS Mes, COUNT(IdM) AS Cantidad " +
                "FROM Multas " +
                "WHERE YEAR(FechaM) = YEAR(GETDATE()) " +
                "GROUP BY MONTH(FechaM) " +
                "ORDER BY Mes"
            );
            ResultSet rs = ps.executeQuery();
            int filas = 0;
            while (rs.next()) {
                int numMes = rs.getInt("Mes");
                datos.addValue(rs.getInt("Cantidad"), "Multas", mesesCortos[numMes]);
                filas++;
            }
            if (filas == 0) {
                datos.addValue(0, "Multas", "Sin datos");
            }
            c.close();
        }

        JFreeChart grafico = ChartFactory.createBarChart3D(
            "Multas por Mes", "Mes", "Cantidad",
            datos, PlotOrientation.VERTICAL, true, true, true
        );

        File archivo = new File("c:/dell/barras_multas.jpg");
        ChartUtilities.saveChartAsJPEG(archivo, grafico, 600, 400);

    } catch (Exception e) {
        System.out.println("Error en gráfica de multas: " + e.getMessage());
    }
}
}
