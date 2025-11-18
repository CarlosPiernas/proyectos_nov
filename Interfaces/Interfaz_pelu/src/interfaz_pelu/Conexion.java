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

    private static Connection connection;
    
    //constructor
    public void Conexion() throws SQLException {
         
    }

    //conexion --> devuelve array clientes
    public List<Object[]> Clientes() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosClientes = new ArrayList<>();
        String query = "SELECT id_cliente, nombre, apellido, num_visitas, vip FROM clientes ORDER BY id_cliente";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("id_cliente");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getInt("num_visitas"); // Es mejor obtener un entero si es un número
                fila[4] = rs.getBoolean("vip");
                datosClientes.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosClientes;
    }
        //conexion --> devuelve array Peklu
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
    public List<Object[]> Productos() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosProductos = new ArrayList<>();
        String query = "SELECT id_producto, producto, stock, proveedor FROM inventario ORDER BY id_producto";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("id_producto");
                fila[1] = rs.getString("producto");
                fila[2] = rs.getString("stock");
                fila[3] = rs.getString("proveedor"); 
                datosProductos.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosProductos;
    }
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
    public List<Object[]> ClientesVip() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosClientesVip = new ArrayList<>();
        String query = "SELECT id_cliente, nombre, apellido, num_visitas, vip FROM clientes WHERE vip = true";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("id_cliente");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getInt("num_visitas"); // Es mejor obtener un entero si es un número
                fila[4] = rs.getBoolean("vip");
                datosClientesVip.add(fila);
            }//poner catch de excepcion con popup por si falla   
        }
        return datosClientesVip;
    }
    public List<Object[]> StockBajo() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        List<Object[]> datosStockBajo = new ArrayList<>();
        String query = "SELECT id_producto, producto, stock, proveedor FROM inventario WHERE stock < 5";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("id_producto");
                fila[1] = rs.getString("producto");
                fila[2] = rs.getString("stock");
                fila[3] = rs.getString("proveedor"); 
                datosStockBajo.add(fila);
            }//poner catch de excepcion con popup por si falla   
            
        }
        return datosStockBajo;
    }
    
}

