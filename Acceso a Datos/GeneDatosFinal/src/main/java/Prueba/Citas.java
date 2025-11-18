/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Prueba;

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
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Carlos\\Desktop\\Citas_Tabla.txt"));
            
            // Se generan los 1000 datos
            for (int i = 1; i <= 2500; i++) {
                String fecha = ((int) (Math.random() * 28) + 1) + "/" + ((int) (Math.random() * 12) + 1) + "/" + 2025;
                int idc = ((int)(Math.random() * 500) + 1);
                double precio = (Math.random() * 15) + 5;
                bw.write(i + "," + fecha + "," + precio + "," + i + "," + idc);
                bw.newLine();
            }

            bw.close();
            System.out.println("Listo bb");
        } catch (IOException e) {
        }
    }
}
