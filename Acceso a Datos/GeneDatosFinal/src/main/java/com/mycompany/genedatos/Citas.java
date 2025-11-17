/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.genedatos;

import Prueba.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Alumno
 */
public class Citas {
    public Citas() {
        System.out.println("Empiezo con la tabla Citas");
    try {
            //Crea el archivo donde se van a escribir los datos
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Alumno\\Desktop\\Datos\\Citas_Tabla.txt"));
            
            // Se generan los 1000 datos
            for (int i = 1; i <= 50; i++) {
                String fecha = ((int) (Math.random() * 28) + 1) + "/" + ((int) (Math.random() * 12) + 1) + "/" + 2025;
                double precio = (Math.random() * 15) + 5;
                bw.write(i + "," + fecha + "," + precio);
                bw.newLine();
            }

            bw.close();
            System.out.println("Listo bb");
        } catch (IOException e) {
        }
    }
}
