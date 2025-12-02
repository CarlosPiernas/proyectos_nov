package com.mycompany.pruebapsp;
/**
 *
 * @author Carlos
 */
//Representa a cada uno de los clientes como un hilo. Cada cliente debe pasar por las 4 zonas de servicio.
public class Clientes extends Thread {

    private final String nombre;
    // Referencias a las zonas de servicio
    private final Zona zLavado;
    private final Zona zCorte;
    private final Zona zTinte;
    private final Zona zPeinado;
    private final GestorJornada gJ;
    
    /* Constructor */
    public Clientes(String nombre, Zona zLavado, Zona zCorte, Zona zTinte, Zona zPeinado, GestorJornada gJ) {
        this.nombre = nombre;
        this.zLavado = zLavado;
        this.zCorte = zCorte;
        this.zTinte = zTinte;
        this.zPeinado = zPeinado;
        this.gJ = gJ;
    }

    public String getNombre() {
        return nombre;
    }

    //Define el recorrido del cliente a través de las zonas de la peluquería.
    @Override
    public void run() {
        // El cliente pasa secuencialmente por las 4 zonas en el orden establecido
        transitarYEntrarZona(zLavado);
        transitarYEntrarZona(zCorte);
        transitarYEntrarZona(zTinte);
        transitarYEntrarZona(zPeinado);

        // Una vez pasado por todas las zonas, el cliente se considera atendido y se marcha
        System.out.println(nombre + " ha completado su recorrido y se retira.");
        System.out.println("*********************");
    }
    //Simula el desplazamiento y la entrada a una zona de servicio.
    public void transitarYEntrarZona(Zona zona) {
        // Simula un tiempo de desplazamiento aleatorio entre zonas
        try {
            int tiempoDescanso = (int) (Math.random() * (1500 - 500 + 1)) + 500;
            Thread.sleep(tiempoDescanso);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // El cliente intenta sentarse y espera a ser atendido
        zona.ocuparPuestoCliente(this);
    }
}