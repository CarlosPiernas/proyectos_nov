package com.mycompany.pruebapsp;

/**
 *
 * @author Carlos
 */

//Representa una de las cuatro zonas de trabajo (Lavado, Corte, Tinte, Peinado).
//Implementa sincronización mediante Monitores (synchronized).
public class Zona {

    final String nombreZona;
    private boolean puestoOcupado = false; // Indicador de si un cliente está usando la zona
    private Clientes clienteActual = null; // Referencia al cliente actualmente sentado
    
    /**
     * Constructor de la clase Zona.
     */
    public Zona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    // Método invocado por el Cliente para sentarse en la zona. 
    //El cliente espera si la zona está ocupada o hasta que el servicio finalice.
    public synchronized void ocuparPuestoCliente(Clientes cli) {
        try {
            //El cliente espera hasta que la zona esté libre
            while (puestoOcupado) {            
                wait(); 
            }

            //Ocupa la zona
            clienteActual = cli;
            puestoOcupado = true;
            
            System.out.println(cli.getNombre() + " se sienta en la Zona de " + nombreZona);
            System.out.println("*********************");
            
            // El cliente espera a que la peluquera lo atienda y finalice el servicio
            wait();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    //Método invocado por la Peluquera para intentar atender a un cliente en esta zona.
    public synchronized boolean intentarAtender(Peluqueras pelu) {
        // Solo se atiende si hay un cliente sentado
        if (!puestoOcupado) {
            return false;
        }
        try {
            // Inicio de la atención
            System.out.println(pelu.getNombre() + " empieza a atender a " + clienteActual.getNombre() + 
            " en la Zona de " + nombreZona);
            System.out.println("*********************");
            // Simulación del tiempo de servicio
            int tiempoDescanso = (int) (Math.random() * (1500 - 500 + 1)) + 500;
            Thread.sleep(tiempoDescanso);
            
            System.out.println(pelu.getNombre() + " termina de atender a " + clienteActual.getNombre() + " en " + nombreZona);
            System.out.println("*********************");
            
            // El servicio termina y la zona queda libre
            puestoOcupado = false; 
            clienteActual = null; 

            // Notifica al cliente para que continúe su camino
            notifyAll();
            return true; 
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // En caso de interrupción durante el servicio, liberamos la zona
            if (puestoOcupado) {
                puestoOcupado = false;
                clienteActual = null;
                notifyAll();
            }
            return true;
        }
    }

    //Verifica si hay un cliente en la zona (usado por la Peluquera para buscar trabajo).
    public synchronized boolean hayClienteOcupandoPuesto() {
        return puestoOcupado;
    }
}