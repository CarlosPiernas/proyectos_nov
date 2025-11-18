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
public class Inventario {
        public Inventario() {
        System.out.println("Empiezo con la tabla Inventario");
    try {
        String productos[] = new String[15];
        String rutaN = "C:\\Users\\Carlos\\Desktop\\Productos.txt";
            FileReader fr1 = new FileReader(rutaN);
            BufferedReader br1 = new BufferedReader(fr1);
            for (int i=0;i<15;i++){
                productos[i]=br1.readLine();
                //System.out.println(nombres[i]);
        }
        //Crea el archivo donde se van a escribir los datos
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Carlos\\Desktop\\inventario_Tabla.txt"));
        String proveedor;
        // Se generan los 1000 datos
        for (int i = 0; i <= 14; i++) {
            if (i % 2 == 0) {
                proveedor = "Cosbell";
            } else if (i % 3 == 0) {
                proveedor = "Schwarzkopf";
            } else if (i % 4 == 0) {
                proveedor = "Pelushop";
            } else {
                proveedor = "Saylu";
            }
            int stock = (int) (Math.random() * 20);
            int stockF = (int) (Math.random() * (25 - 20 + 1)) + 20;
            bw.write((i+1) + "," + productos[i] + "," + stock + "," + proveedor + "," + stockF);
            bw.newLine();
        }

            bw.close();
            System.out.println("Listo bb");
        } catch (IOException e) {
        }
    }
}
