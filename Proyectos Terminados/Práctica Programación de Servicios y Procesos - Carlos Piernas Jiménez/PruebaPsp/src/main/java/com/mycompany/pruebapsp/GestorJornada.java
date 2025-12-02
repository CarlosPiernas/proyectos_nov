package com.mycompany.pruebapsp;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Carlos
 */
//Clase para centralizar el conteo de clientes y el estado de la jornada. Actúa como monitor para sincronización global.

public class GestorJornada {

    // Contador total de clientes atendidos
    private int clientesAtendidosTotal = 0;
    // Indicador del estado de la peluquería (Abierta/Cerrada)
    private boolean jornadaActiva = true;

    // Mapa para almacenar el contador de clientes atendidos por cada peluquera
    private final Map<String, Integer> atendidosPorPeluquera = new HashMap<>();

    /* Variables estáticas para los parámetros de la simulación */
    //El total de clientes que va a haber en la simulación
    private static int totalClientes;
    //La cantidad de clientes que deben ser atendidos para que las peluqueras se tomen una siesta
    private static final int clientesSiesta = 10;
    //El total de peluqueras que habrá trabajando
    private static int peluTrabajando;

    /* Métodos Setter para la configuración por teclado */
    public static void setTotalClientes(int numClientes) {
        GestorJornada.totalClientes = numClientes;
    }

    public static void setPeluTrabajando(int numPeluqueras) {
        GestorJornada.peluTrabajando = numPeluqueras;
    }
    // Métodos Getter que usan las variables estáticas
    public static int getPeluTrabajando() {
        return peluTrabajando;
    }

    public static int getTotalClientes() {
        return totalClientes;
    }

    public static int getClientesSiesta() {
        return clientesSiesta;
    }
   

    /**
     * Incrementa los contadores de clientes atendidos (total y por peluquera).
     */
    public synchronized int registrarAtencion(String nombrePelu) {
        clientesAtendidosTotal++;

        atendidosPorPeluquera.put(nombrePelu, atendidosPorPeluquera.getOrDefault(nombrePelu, 0) + 1);

        if (clientesAtendidosTotal == totalClientes) {
            finalizarJornada();
            notifyAll();
        }

        return clientesAtendidosTotal;
    }

    /**
     * Comprueba si la peluquera ha alcanzado el umbral para tomar una siesta.
     */
    public synchronized boolean verificarNecesidadSiesta(String nombrePelu) {
        int atendidos = atendidosPorPeluquera.getOrDefault(nombrePelu, 0);
        return atendidos > 0 && (atendidos % clientesSiesta == 0);
    }

    /**
     * Establece el estado de la jornada a inactiva (cerrada).
     */
    public synchronized void finalizarJornada() {
        if (jornadaActiva) {
            jornadaActiva = false;
        }
    }

    /**
     * Comprueba el estado actual de la peluquería.
     */
    public synchronized boolean verificarJornadaActiva() {
        return jornadaActiva;
    }

    /**
     * Devuelve el total de clientes atendidos hasta el momento.
     */
    public synchronized int getClientesAtendidosTotal() {
        return clientesAtendidosTotal;
    }

    /**
     * Permite que el hilo principal espere a que todos los clientes sean
     * atendidos.
     */
    public synchronized void esperarCierreJornada() throws InterruptedException {
        while (clientesAtendidosTotal < totalClientes) {
            wait();
        }
    }

    
}
