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

/**
 *
 * @author Alumno
 */
public class Conexion {
    private static Connection connection;
    public void Conexion() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/peluqueria";
        connection = DriverManager.getConnection(url, "postgres", "3434");
        
    }

    public void Clientes() throws SQLException {
         String query = "SELECT id_cliente, nombre, apellido, num_visitas, vip FROM clientes";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String id = rs.getString("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String num_visitas = rs.getString("num_visitas");
                String vip = rs.getString("vip");
                System.out.println(id + " " + nombre + " " + apellido + " " + num_visitas + " " + vip);
            }//poner catch de excepcion con popup por si falla
        }
    }
}

