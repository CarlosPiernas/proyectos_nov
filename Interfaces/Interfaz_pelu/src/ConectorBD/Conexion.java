/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConectorBD;

import interfaz_pelu.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane; // <-- Importación necesaria para el pop-up de error

/**
 *
 * @author Carlos
 */
public class Conexion {

    //se declara el objeto connection (ya no es static, se manejará localmente en cada método)
    private static Connection connection; // Aunque se maneje localmente en los métodos, mantenemos la declaración estática si fuera necesaria para otros usos.

    //constructor
    public void Conexion() throws SQLException {

    }

    //conexion --> devuelve array clientes
    public List<Object[]> Clientes() throws SQLException {
        //crea el String url donde pone la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosClientes
        List<Object[]> datosClientes = new ArrayList<>();
        //Guarda en el String query la consulta a realizar, seleccionando todos los campos de la tabla 'clientes' ordenados por 'id_cliente'
        String query = "SELECT id_cliente, nombre, apellido, vip, fecha_alta FROM clientes ORDER BY id_cliente";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //bucle para obtener los datos de la consulta
            while (rs.next()) {
                //Crea un arrays de objetos llamado fila
                Object[] fila = new Object[5];
                //Cada objeto del arrays se llena con los atributos de las tablas: id_cliente, nombre, apellido, vip, fecha_alta
                fila[0] = rs.getString("id_cliente");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getBoolean("vip");
                fila[4] = rs.getString("fecha_alta");
                //añade estos datos en orden al arrays datosClientes
                datosClientes.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar los clientes: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción para que el método calling pueda manejarla
        }
        //devuelve el arrays datosClientes
        return datosClientes;
    }
    
    //conexion --> devuelve array Peluquera
    public List<Object[]> Peluqueras() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosPeluqueras para almacenar los resultados
        List<Object[]> datosPeluqueras = new ArrayList<>();
        //Guarda en el String query la consulta a realizar para obtener los datos de las peluqueras
        String query = "SELECT id_peluquera, exp_anio, nombre, especialidad, estado FROM peluqueras ORDER BY id_peluquera";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos de una peluquera
                Object[] fila = new Object[5];
                //Asigna los valores de las columnas a las posiciones del array: id_peluquera, exp_anio, nombre, especialidad, estado
                fila[0] = rs.getString("id_peluquera");
                fila[1] = rs.getString("exp_anio");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("especialidad"); 
                fila[4] = rs.getString("estado");
                //Añade el array (fila de datos) al ArrayList principal
                datosPeluqueras.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar las peluqueras: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosPeluqueras
        return datosPeluqueras;
    }

    //conexion --> devuelve array servicios
    public List<Object[]> Servicios() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosServicios para almacenar los resultados
        List<Object[]> datosServicios = new ArrayList<>();
        //Guarda en el String query la consulta para obtener los datos de los servicios.
        String query = "SELECT id_servicio, nombre, CAST(PRECIO AS DECIMAL(10,2)), duracion, requier_porducto, id_peluquera_peluqueras FROM servicios ORDER BY id_servicio";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos de un servicio
                Object[] fila = new Object[6];
                //Asigna los valores de las columnas a las posiciones del array
                fila[0] = rs.getString("id_servicio");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("precio");
                fila[3] = rs.getString("duracion"); 
                fila[4] = rs.getBoolean("requier_porducto");
                fila[5] = rs.getString("id_peluquera_peluqueras");
                //Añade el array (fila de datos) al ArrayList principal
                datosServicios.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar los servicios: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosServicios
        return datosServicios;
    }

    //conexion --> devuelve array productos
    public List<Object[]> Productos() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosProductos para almacenar los resultados
        List<Object[]> datosProductos = new ArrayList<>();
        //Guarda en el String query la consulta para obtener los datos del inventario (productos)
        String query = "SELECT id_producto, producto, stock_actual, proveedor, stock_max FROM inventario ORDER BY id_producto";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos de un producto
                Object[] fila = new Object[5];
                //Asigna los valores de las columnas a las posiciones del array
                fila[0] = rs.getString("id_producto");
                fila[1] = rs.getString("producto");
                fila[2] = rs.getString("stock_actual");
                fila[3] = rs.getString("proveedor");
                fila[4] = rs.getString("stock_max");
                //Añade el array (fila de datos) al ArrayList principal
                datosProductos.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar los productos: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosProductos
        return datosProductos;
    }

    //conexion --> devuelve array serviciosRentables
    public List<Object[]> ServiciosRentables() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosServiciosRentables para almacenar los resultados
        List<Object[]> datosServiciosRentables = new ArrayList<>();
        //Guarda en el String query la consulta para obtener los servicios, ordenados de forma descendente por precio (más rentables primero)
        String query = "SELECT id_servicio, nombre, CAST(PRECIO AS DECIMAL(10,2)), duracion, requier_porducto, id_peluquera_peluqueras FROM servicios ORDER BY precio DESC";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos de un servicio
                Object[] fila = new Object[6];
                //Asigna los valores de las columnas a las posiciones del array
                fila[0] = rs.getString("id_servicio");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("precio");
                fila[3] = rs.getString("duracion"); 
                fila[4] = rs.getBoolean("requier_porducto");
                fila[5] = rs.getString("id_peluquera_peluqueras");
                //Añade el array (fila de datos) al ArrayList principal
                datosServiciosRentables.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar los servicios rentables: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosServiciosRentables
        return datosServiciosRentables;
    }

    //conexion --> devuelve array clientesVip
    public List<Object[]> ClientesVip() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosClientesVip para almacenar los resultados
        List<Object[]> datosClientesVip = new ArrayList<>();
        //Guarda en el String query la consulta para obtener solo los clientes con la columna 'vip' en true
        String query = "SELECT id_cliente, nombre, apellido, vip, fecha_alta FROM clientes WHERE vip = true";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos de un cliente VIP
                Object[] fila = new Object[5];
                //Asigna los valores de las columnas a las posiciones del array
                fila[0] = rs.getString("id_cliente");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getBoolean("vip");
                fila[4] = rs.getString("fecha_alta");
                //Añade el array (fila de datos) al ArrayList principal
                datosClientesVip.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar los clientes VIP: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosClientesVip
        return datosClientesVip;
    }

    //conexion --> devuelve array StockBajo
    public List<Object[]> StockBajo() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosStockBajo para almacenar los resultados
        List<Object[]> datosStockBajo = new ArrayList<>();
        //Guarda en el String query la consulta para obtener los productos del inventario con 'stock_actual' menor que 5
        String query = "SELECT id_producto, producto, stock_actual, proveedor, stock_max FROM inventario WHERE stock_actual < 5";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos de un producto con stock bajo
                Object[] fila = new Object[5];
                //Asigna los valores de las columnas a las posiciones del array
                fila[0] = rs.getString("id_producto");
                fila[1] = rs.getString("producto");
                fila[2] = rs.getString("stock_actual");
                fila[3] = rs.getString("proveedor");
                fila[4] = rs.getString("stock_max");
                //Añade el array (fila de datos) al ArrayList principal
                datosStockBajo.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar el inventario con stock bajo: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosStockBajo
        return datosStockBajo;
    }

    //conexion --> devuelve array clientesVisitas
    public List<Object[]> clientesVisitas() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosClientesVisitas para almacenar los resultados
        List<Object[]> datosClientesVisitas = new ArrayList<>();
        //Guarda en el String query la consulta para obtener el nombre, apellido y el total de visitas
        String query = "SELECT nombre, apellido, \"Visitas totales\" FROM clientesVisitas ";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos de las visitas
                Object[] fila = new Object[3];
                //Asigna los valores de las columnas a las posiciones del array
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("apellido");
                fila[2] = rs.getString("Visitas totales");
                //Añade el array (fila de datos) al ArrayList principal
                datosClientesVisitas.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar los clientes y sus visitas: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosClientesVisitas
        return datosClientesVisitas;
    }

    //conexion --> devuelve array serviciosSolicitados
    public List<Object[]> serviciosSolicitados() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosServiciosSolicitados para almacenar los resultados
        List<Object[]> datosServiciosSolicitados = new ArrayList<>();
        //Guarda en el String query la consulta para obtener el nombre y el total de solicitudes
        String query = "SELECT nombre, \"Solicitudes totales\" FROM serviciosSolicitados ";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos de las solicitudes de servicios
                Object[] fila = new Object[2];
                //Asigna los valores de las columnas a las posiciones del array
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("Solicitudes totales");
                //Añade el array (fila de datos) al ArrayList principal
                datosServiciosSolicitados.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar los servicios solicitados: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosServiciosSolicitados
        return datosServiciosSolicitados;
    }

    //conexion --> devuelve array diasCitas
    public List<Object[]> diasCitas() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosDiasCitas para almacenar los resultados
        List<Object[]> datosDiasCitas = new ArrayList<>();
        //Guarda en el String query la consulta para obtener la fecha y el total de citas por día
        String query = "SELECT fecha, \"Citas totales\" FROM diasCitas";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos de las citas por día
                Object[] fila = new Object[2];
                //Asigna los valores de las columnas a las posiciones del array
                fila[0] = rs.getString("fecha");
                fila[1] = rs.getString("Citas totales");
                //Añade el array (fila de datos) al ArrayList principal
                datosDiasCitas.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar las citas por día: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosDiasCitas
        return datosDiasCitas;
    }

    //conexion --> devuelve array citasCaras
    public List<Object[]> citasCaras() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosCitasCaras para almacenar los resultados
        List<Object[]> datosCitasCaras = new ArrayList<>();
        //Guarda en el String query la consulta para obtener el id, el precio, el nombre y el apellido
        String query = "SELECT id_cita, CAST(precio as decimal (10,2)), nombre, apellido FROM citasCaras";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos de las citas
                Object[] fila = new Object[4];
                //Asigna los valores de las columnas a las posiciones del array
                fila[0] = rs.getString("id_cita");
                fila[1] = rs.getString("precio");
                fila[2] = rs.getString("nombre");
                fila[3] = rs.getString("apellido");
                //Añade el array (fila de datos) al ArrayList principal
                datosCitasCaras.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar las citas más caras: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosCitasCaras
        return datosCitasCaras;
    }

    //conexion --> devuelve array clientesTarjeta
    public List<Object[]> clientesTarjeta() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosClientesTarjeta para almacenar los resultados
        List<Object[]> datosClientesTarjeta = new ArrayList<>();
        //Guarda en el String query la consulta para obtener los clientes con método de pago 'tarjeta'
        String query = "SELECT distinct nombre, apellido, metodo_pago FROM clientesTarjeta";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos del cliente
                Object[] fila = new Object[3];
                //Asigna los valores de las columnas a las posiciones del array
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("apellido");
                fila[2] = rs.getString("metodo_pago");
                //Añade el array (fila de datos) al ArrayList principal
                datosClientesTarjeta.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar los clientes con pago por tarjeta: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosClientesTarjeta
        return datosClientesTarjeta;
    }

    //conexion --> devuelve array clientesEfectivo
    public List<Object[]> clientesEfectivo() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosClientesEfectivo para almacenar los resultados
        List<Object[]> datosClientesEfectivo = new ArrayList<>();
        //Guarda en el String query la consulta para obtener los clientes con método de pago 'efectivo'
        String query = "SELECT distinct nombre, apellido, metodo_pago FROM clientesEfectivo";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos del cliente
                Object[] fila = new Object[3];
                //Asigna los valores de las columnas a las posiciones del array
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("apellido");
                fila[2] = rs.getString("metodo_pago");
                //Añade el array (fila de datos) al ArrayList principal
                datosClientesEfectivo.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar los clientes con pago en efectivo: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosClientesEfectivo
        return datosClientesEfectivo;
    }

    //conexion --> devuelve array clientesCorteIngles
    public List<Object[]> clientesCorteIngles() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        //Crea el ArrayList de objetos datosClientesCorteIngles para almacenar los resultados
        List<Object[]> datosClientesCorteIngles = new ArrayList<>();
        //Guarda en el String query la consulta para obtener los clientes con método de pago 'El Corte Inglés'
        String query = "SELECT distinct nombre, apellido, metodo_pago FROM clientesCorteIngles";
        
        // El bloque try-with-resources ahora maneja la Connection, Statement y ResultSet
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            //Itera sobre cada registro (fila) devuelto por la consulta
            while (rs.next()) {
                //Crea un array de objetos para almacenar los datos del cliente
                Object[] fila = new Object[3];
                //Asigna los valores de las columnas a las posiciones del array
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("apellido");
                fila[2] = rs.getString("metodo_pago");
                //Añade el array (fila de datos) al ArrayList principal
                datosClientesCorteIngles.add(fila);
            }
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al cargar los clientes con pago 'El Corte Inglés': " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
        //devuelve el arrays datosClientesCorteIngles
        return datosClientesCorteIngles;
    }

    // Método para exportar tablas principales a archivos CSV
    public void exportarDatos() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        
        // El bloque try-with-resources ahora maneja la Connection y el Statement
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement()) {
            
            // Define la consulta SQL para exportar la tabla 'clientes' a un archivo CSV
            String query1 = "COPY public.clientes TO 'C:/Users/Carlos/Desktop/clientes.csv'WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            // Define la consulta SQL para exportar la tabla 'peluqueras' a un archivo CSV
            String query2 = "COPY public.peluqueras TO 'C:/Users/Carlos/Desktop/peluqueras.csv'WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            // Define la consulta SQL para exportar la tabla 'servicios' a un archivo CSV
            String query3 = "COPY public.servicios TO 'C:/Users/Carlos/Desktop/servicios.csv'WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            // Define la consulta SQL para exportar la tabla 'inventario' a un archivo CSV
            String query4 = "COPY public.inventario TO 'C:/Users/Carlos/Desktop/inventario.csv'WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            
            // Ejecuta las consultas de exportación (UPDATE ya que COPY no devuelve ResultSet)
              stmt.executeUpdate(query1); // Exporta clientes
              stmt.executeUpdate(query2); // Exporta peluqueras
              stmt.executeUpdate(query3); // Exporta servicios
              stmt.executeUpdate(query4); // Exporta inventario
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al exportar datos principales: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
    }

    // Método para exportar consultas específicas de datos a archivos CSV
    public void exportarServicios() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        
        // El bloque try-with-resources ahora maneja la Connection y el Statement
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement()) {
            
            // Define la consulta SQL para exportar la lista de servicios ordenados por precio (servicios rentables)
            String query1 = "COPY (SELECT id_servicio, nombre, CAST(PRECIO AS DECIMAL(10,2)), duracion, requier_porducto, id_peluquera_peluqueras FROM servicios ORDER BY precio DESC) TO 'C:/Users/Carlos/Desktop/serviciosRentables.csv' WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            // Define la consulta SQL para exportar la lista de clientes VIP
            String query2 = "COPY (SELECT id_cliente, nombre, apellido, vip, fecha_alta FROM clientes WHERE vip = true) TO 'C:/Users/Carlos/Desktop/clientesVip.csv' WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            // Define la consulta SQL para exportar la lista de productos con stock bajo
            String query3 = "COPY (SELECT id_producto, producto, stock_actual, proveedor, stock_max FROM inventario WHERE stock_actual < 5) TO 'C:/Users/Carlos/Desktop/stockBajo.csv' WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            
            // Ejecuta las consultas de exportación
              stmt.executeUpdate(query1); // Exporta servicios rentables
              stmt.executeUpdate(query2); // Exporta clientes VIP
              stmt.executeUpdate(query3); // Exporta stock bajo
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al exportar consultas de servicios/clientes: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
    }

    // Método para exportar consultas complejas (vistas/consultas) a archivos CSV
    public void exportarConsultas() throws SQLException {
        //crea el String url con la direccion de la base de datos
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        
        // El bloque try-with-resources ahora maneja la Connection y el Statement
        try (Connection connection = DriverManager.getConnection(url, "postgres", "3434"); // La conexión se crea y se asegura su cierre automático
             Statement stmt = connection.createStatement()) {
            
            // Define la consulta SQL para exportar clientes y su total de visitas
            String query1 = "COPY (SELECT nombre, apellido, \"Visitas totales\" FROM clientesVisitas ) TO 'C:/Users/Carlos/Desktop/clientesVisitas.csv' WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            // Define la consulta SQL para exportar las citas por día
            String query2 = "COPY (SELECT fecha, \"Citas totales\" FROM diasCitas) TO 'C:/Users/Carlos/Desktop/diasCitas.csv' WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            // Define la consulta SQL para exportar servicios y su total de solicitudes
            String query3 = "COPY (SELECT nombre, \"Solicitudes totales\" FROM serviciosSolicitados ) TO 'C:/Users/Carlos/Desktop/serviciosSolicitados.csv' WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            // Define la consulta SQL para exportar citas con precio más alto
            String query4 = "COPY (SELECT id_cita, CAST(precio as decimal (10,2)), nombre, apellido FROM citasCaras) TO 'C:/Users/Carlos/Desktop/citasCaras.csv' WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            // Define la consulta SQL para exportar clientes que pagan con tarjeta
            String query5 = "COPY (SELECT distinct nombre, apellido, metodo_pago FROM clientesTarjeta) TO 'C:/Users/Carlos/Desktop/clientesTarjeta.csv' WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            // Define la consulta SQL para exportar clientes que pagan con efectivo
            String query6 = "COPY (SELECT distinct nombre, apellido, metodo_pago FROM clientesEfectivo) TO 'C:/Users/Carlos/Desktop/clientesEfectivo.csv' WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            // Define la consulta SQL para exportar clientes que pagan con El Corte Inglés
            String query7 = "COPY (SELECT distinct nombre, apellido, metodo_pago FROM clientesCorteIngles) TO 'C:/Users/Carlos/Desktop/clientesCorteIngles.csv' WITH (FORMAT CSV, HEADER FALSE, DELIMITER ',', ENCODING 'UTF8');";
            
            // Ejecuta las consultas de exportación
              stmt.executeUpdate(query1); // Exporta clientesVisitas
              stmt.executeUpdate(query2); // Exporta diasCitas
              stmt.executeUpdate(query3); // Exporta serviciosSolicitados
              stmt.executeUpdate(query4); // Exporta citasCaras
              stmt.executeUpdate(query5); // Exporta clientesTarjeta
              stmt.executeUpdate(query6); // Exporta clientesEfectivo
              stmt.executeUpdate(query7); // Exporta clientesCorteIngles
        } catch (SQLException e) {
            // Muestra un pop-up de error con el mensaje de la excepción de SQL
            JOptionPane.showMessageDialog(null, "Error al exportar consultas complejas: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            throw e; // Lanza la excepción
        }
    }
}