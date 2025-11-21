/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz_pelu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alumno
 */
public class Conexion {

    //se declara el objeto connection
    private static Connection connection;

    //constructor
    public void Conexion() throws SQLException {

    }

    //conexion --> devuelve array clientes
    public List<Object[]> Clientes() throws SQLException {
        //crea el String url donde pone la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //se conecta con la base de datos mediante la libreria DriverManager pasando por parametro la url el usuario y la contraseña
        connection = DriverManager.getConnection(url, "postgres", "3434");
        //Crea el ArrayList de objetos
        List<Object[]> datosClientes = new ArrayList<>();
        String query = "SELECT id_cliente, nombre, apellido, vip, fecha_alta FROM clientes ORDER BY id_cliente";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("id_cliente");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getBoolean("vip");
                fila[4] = rs.getString("fecha_alta");
                datosClientes.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosClientes;
    }
    //conexion --> devuelve array Peluquera

    public List<Object[]> Peluqueras() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosPeluqueras = new ArrayList<>();
        String query = "SELECT id_peluquera, exp_anio, nombre, especialidad, estado FROM peluqueras ORDER BY id_peluquera";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("id_peluquera");
                fila[1] = rs.getString("exp_anio");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("especialidad"); // Es mejor obtener un entero si es un número
                fila[4] = rs.getString("estado");
                datosPeluqueras.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosPeluqueras;
    }

    //conexion --> devuelve array servicios
    public List<Object[]> Servicios() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosServicios = new ArrayList<>();
        String query = "SELECT id_servicio, nombre, CAST(PRECIO AS DECIMAL(10,2)), duracion, requier_porducto, id_peluquera_peluqueras FROM servicios ORDER BY id_servicio";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getString("id_servicio");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("precio");
                fila[3] = rs.getString("duracion"); // Es mejor obtener un entero si es un número
                fila[4] = rs.getBoolean("requier_porducto");
                fila[5] = rs.getString("id_peluquera_peluqueras");
                datosServicios.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosServicios;
    }

    //conexion --> devuelve array productos
    public List<Object[]> Productos() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosProductos = new ArrayList<>();
        String query = "SELECT id_producto, producto, stock_actual, proveedor, stock_max FROM inventario ORDER BY id_producto";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("id_producto");
                fila[1] = rs.getString("producto");
                fila[2] = rs.getString("stock_actual");
                fila[3] = rs.getString("proveedor");
                fila[4] = rs.getString("stock_max");
                datosProductos.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosProductos;
    }

    //conexion --> devuelve array serviciosRentables
    public List<Object[]> ServiciosRentables() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosServiciosRentables = new ArrayList<>();
        String query = "SELECT id_servicio, nombre, CAST(PRECIO AS DECIMAL(10,2)), duracion, requier_porducto, id_peluquera_peluqueras FROM servicios ORDER BY precio DESC";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getString("id_servicio");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("precio");
                fila[3] = rs.getString("duracion"); // Es mejor obtener un entero si es un número
                fila[4] = rs.getBoolean("requier_porducto");
                fila[5] = rs.getString("id_peluquera_peluqueras");
                datosServiciosRentables.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosServiciosRentables;
    }

    //conexion --> devuelve array clientesVip
    public List<Object[]> ClientesVip() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosClientesVip = new ArrayList<>();
        String query = "SELECT id_cliente, nombre, apellido, vip, fecha_alta FROM clientes WHERE vip = true";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("id_cliente");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getBoolean("vip");
                fila[4] = rs.getString("fecha_alta");
                datosClientesVip.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosClientesVip;
    }

    //conexion --> devuelve array StockBajo
    public List<Object[]> StockBajo() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosStockBajo = new ArrayList<>();
        String query = "SELECT id_producto, producto, stock_actual, proveedor, stock_max FROM inventario WHERE stock_actual < 5";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("id_producto");
                fila[1] = rs.getString("producto");
                fila[2] = rs.getString("stock_actual");
                fila[3] = rs.getString("proveedor");
                fila[4] = rs.getString("stock_max");
                datosStockBajo.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosStockBajo;
    }

    public List<Object[]> clientesVisitas() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosClientesVisitas = new ArrayList<>();
        String query = "SELECT nombre, apellido, \"Visitas totales\" FROM clientesVisitas ";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("apellido");
                fila[2] = rs.getString("Visitas totales");
                datosClientesVisitas.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosClientesVisitas;
    }

    public List<Object[]> serviciosSolicitados() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosServiciosSolicitados = new ArrayList<>();
        String query = "SELECT nombre, \"Solicitudes totales\" FROM serviciosSolicitados ";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[2];
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("Solicitudes totales");
                datosServiciosSolicitados.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosServiciosSolicitados;
    }

    public List<Object[]> diasCitas() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosDiasCitas = new ArrayList<>();
        String query = "SELECT fecha, \"Citas totales\" FROM diasCitas";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[2];
                fila[0] = rs.getString("fecha");
                fila[1] = rs.getString("Citas totales");
                datosDiasCitas.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosDiasCitas;
    }

    public List<Object[]> citasCaras() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosCitasCaras = new ArrayList<>();
        String query = "SELECT id_cita, CAST(precio as decimal (10,2)), nombre, apellido FROM citasCaras";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("id_cita");
                fila[1] = rs.getString("precio");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("apellido");
                datosCitasCaras.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosCitasCaras;
    }

    public List<Object[]> clientesTarjeta() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosClientesTarjeta = new ArrayList<>();
        String query = "SELECT distinct nombre, apellido, metodo_pago FROM clientesTarjeta";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("apellido");
                fila[2] = rs.getString("metodo_pago");
                datosClientesTarjeta.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosClientesTarjeta;
    }

    public List<Object[]> clientesEfectivo() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosClientesEfectivo = new ArrayList<>();
        String query = "SELECT distinct nombre, apellido, metodo_pago FROM clientesEfectivo";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("apellido");
                fila[2] = rs.getString("metodo_pago");
                datosClientesEfectivo.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosClientesEfectivo;
    }

    public List<Object[]> clientesCorteIngles() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosClientesCorteIngles = new ArrayList<>();
        String query = "SELECT distinct nombre, apellido, metodo_pago FROM clientesCorteIngles";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("apellido");
                fila[2] = rs.getString("metodo_pago");
                datosClientesCorteIngles.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosClientesCorteIngles;
    }
}
