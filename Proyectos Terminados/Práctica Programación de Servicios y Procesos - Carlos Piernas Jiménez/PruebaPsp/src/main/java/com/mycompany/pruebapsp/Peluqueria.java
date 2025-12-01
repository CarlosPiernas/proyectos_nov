package com.mycompany.pruebapsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */

//Clase principal que inicializa y coordina la simulación concurrente de la peluquería. 
public class Peluqueria {

    public static void main(String[] args) throws InterruptedException {
        // 1. LECTURA DE PARÁMETROS POR CONSOLA
        Scanner scanner = new Scanner(System.in);
        int numClientes;
        int numPeluqueras;
                
        // Solicitar el número de clientes
        System.out.print("Introduce el número de CLIENTES para la simulación: ");
        numClientes = scanner.nextInt();
        GestorJornada.setTotalClientes(numClientes); // Asignar al GestorJornada
        
        // Solicitar el número de peluqueras
        System.out.print("Introduce el número de PELUQUERAS trabajando: ");
        numPeluqueras = scanner.nextInt();
        GestorJornada.setPeluTrabajando(numPeluqueras); // Asignar al GestorJornada
        
       
        System.out.println("--------------------------------------");

        /* Se definen las 4 zonas de trabajo de la peluquería */
        Zona zLavado = new Zona("Lavado");
        Zona zCorte = new Zona("Corte");
        Zona zTinte = new Zona("Tinte");
        Zona zPeinado = new Zona("Peinado");
        
        /* Se inicializa el monitor GestorJornada para el control global */
        GestorJornada gJ = new GestorJornada();
        
        /*Se inicializa el objeto pelu de la clase peluqueras*/
        Peluqueras[] pelu = new Peluqueras[GestorJornada.getPeluTrabajando()];
        
        /* Se crean los hilos peluqueras */
        for (int i = 0; i < GestorJornada.getPeluTrabajando(); i++) {
            pelu[i] = new Peluqueras("Peluquera-" + (i + 1), zLavado, zCorte, zTinte, zPeinado, gJ);
            /* Se inician los hilos peluqueras */
            pelu[i].start();
        }
        /* Se crea y arranca la lista de Clientes*/
        List<Clientes> listaClientes = new ArrayList<>();

        for (int i = 1; i <= GestorJornada.getTotalClientes(); i++) {
            Clientes cliente = new Clientes("Cliente-" + i, zLavado, zCorte, zTinte, zPeinado, gJ);
            listaClientes.add(cliente);
            cliente.start();
        }

        /* El hilo principal espera a que todos los clientes terminen su recorrido */
        gJ.esperarCierreJornada();

        /* Una vez despierto, el hilo principal confirma la finalización */
        System.out.println("FIN DE LA JORNADA. Clientes atendidos: " + gJ.getClientesAtendidosTotal());

        // El programa termina cuando los hilos peluqueras (p1, p2, p3) finalizan su bucle al detectar que la jornadaActiva es false y ya no hay clientes esperando.
    }
}
