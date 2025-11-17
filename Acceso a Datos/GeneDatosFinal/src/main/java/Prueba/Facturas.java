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
public class Facturas {
    public Facturas() {
        System.out.println("Empiezo con la tabla Citas");
    try {
            //Crea el archivo donde se van a escribir los datos
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Alumno\\Desktop\\Datos\\facturas_Tabla.txt"));
            
            // Se generan los 1000 datos
            String met;
            for (int i = 1; i <= 500; i++) {
                 if (i % 2 == 0) {
                met = "tarjeta";
            } else if(i%3 == 0){
                met = "tarjeta de compra del Corte Inglés";
            }else met = "efectivo";
                double importe = (Math.random() * 15) + 5;
                bw.write(i + "," + importe + "," + met);
                bw.newLine();
            }

            bw.close();
            System.out.println("Listo bb");
        } catch (IOException e) {
        }
    }
}
