/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Prueba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Alumno
 */
public class Servicios {
    public Servicios() {
        System.out.println("Empiezo con la tabla Servicios");
    try {
        String nombres[] = new String[10];
        String rutaN = "C:\\Users\\Alumno\\Desktop\\Servicios.txt";
            FileReader fr1 = new FileReader(rutaN);
            BufferedReader br1 = new BufferedReader(fr1);
            for (int i=0;i<10;i++){
                nombres[i]=br1.readLine();
                //System.out.println(nombres[i]);
            }
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Alumno\\Desktop\\Datos\\Servicios_Tabla.txt"));
            
            // Se generan los 1000 datos
            for (int i = 0; i <= 9; i++) {
                int dur1 = (int) (Math.random() * 6) + 5;
                int dur2 = (int) (Math.random() * 11) + 10;
                int dur3 = 25;
                double precio = (Math.random() * 15) + 5;
                int rec;
            if (i % 2 == 0) {
                rec = 1;
            } else {
                rec = 0;
            }
            if (i<4){
            bw.write((i+1) + "," + nombres[i] + "," + precio + "," + dur1 + "," + rec);
            bw.newLine();}
            else if(i==9){
            bw.write((i+1) + "," + nombres[i] + "," + precio + "," + dur3 + "," + rec);
            bw.newLine();
            }
            else{
            bw.write((i+1) + "," + nombres[i] + "," + precio + "," + dur2 + "," + rec);
            bw.newLine();
            }
        }

            bw.close();
            System.out.println("Listo bb");
        } catch (IOException e) {
        }
    }
}
