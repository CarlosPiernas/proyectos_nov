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
public class Peluqueras {

    public Peluqueras() {
        System.out.println("Empiezo con la tabla Peluqueras");
        try {
            String nombres[] = new String[100];
            String rutaN = "C:\\Users\\Alumno\\Desktop\\Nombres.txt";
            FileReader fr1 = new FileReader(rutaN);
            BufferedReader br1 = new BufferedReader(fr1);
            for (int i = 0; i < 100; i++) {
                nombres[i] = br1.readLine();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Alumno\\Desktop\\Datos\\Peluqueres_Tabla.txt"));
            for (int i = 0; i <= 5; i++) {
                int numero = (int) (Math.random() * 100);
                int anio = (int) (Math.random() * 40) + 1;
                String esp;
                String est;
                if (i % 2 == 0) {
                    esp = "Lavado";
                } else if (i % 3 == 0) {
                    esp = "Corte";
                } else if (i % 4 == 0) {
                    esp = "Peinado";
                } else {
                    esp = "Tinte";
                }
                if (i % 2 == 0 || i % 3 == 0) {
                    est = "Disponible";
                } else {
                    est = "Descansando";
                }

                bw.write((i + 1) + "," + anio + "," + nombres[numero] + "," + "," + esp + "," + est);
                bw.newLine();
            }

            bw.close();
            System.out.println("Listo bb");
        } catch (IOException e) {
        }
    }
}
