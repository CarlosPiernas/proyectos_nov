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
public class Clientes {
    public Clientes() {
        System.out.println("Empiezo con la tabla Clientes");
    try {
        String nombres[] = new String[100];
        String apellidos[] = new String[100];
        String rutaN = "C:\\Users\\Carlos\\Desktop\\Nombres.txt";
            String rutaA = "C:\\Users\\Carlos\\Desktop\\Apellidos.txt";
            FileReader fr1 = new FileReader(rutaN);
            BufferedReader br1 = new BufferedReader(fr1);
            FileReader fr2 = new FileReader(rutaA);
            BufferedReader br2 = new BufferedReader(fr2);
            for (int i=0;i<100;i++){
                nombres[i]=br1.readLine();
                //System.out.println(nombres[i]);
                apellidos[i]=br2.readLine();
                //System.out.println(apellidos[i]);
            }
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Carlos\\Desktop\\Clientes_Tabla.txt"));
            
            // Se generan los 1000 datos
            for (int i = 1; i <= 500; i++) {
                int numero = (int)(Math.random() * 100);
                int numero2 = (int)(Math.random() * 100);
                String fecha = ((int) (Math.random() * 28) + 1) + "/" + ((int) (Math.random() * 12) + 1) + "/" + 2025;
                int visitas = (int) (Math.random() * 10) + 1;
                int vip;
            if (i % 50 == 0) {
                vip = 1;
            } else {
                vip = 0;
            }
            bw.write(i + "," + nombres[numero] + "," + apellidos[numero2] + "," + visitas + "," + vip + "," + fecha);
            bw.newLine();
        }

            bw.close();
            System.out.println("Listo bb");
        } catch (IOException e) {
        }
    }
}
