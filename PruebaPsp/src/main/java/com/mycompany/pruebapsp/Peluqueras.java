package com.mycompany.pruebapsp;

/**
 *
 * @author Carlos
 */

// Representa a la peluquera como un hilo de trabajo.
 public class Peluqueras extends Thread {

    private final String nombrePelu;
    private final Zona[] puestosDeTrabajo;
    private final GestorJornada gJ;
    
    // Variables para la lógica de descanso dinámico
    private int clientesAtendidos; 
    private int nuevaSiesta;              

    /* Constructor */
    public Peluqueras(String nombrePelu, Zona zLavado, Zona zCorte, Zona zTinte, Zona zPeinado, GestorJornada gJ) {
        this.nombrePelu = nombrePelu;
        this.puestosDeTrabajo = new Zona[]{zPeinado, zTinte, zCorte, zLavado}; 
        this.gJ = gJ;
        
        nuevoLimiteSiesta(); 
    }

    public String getNombre() {
        return nombrePelu;
    }

    //Genera un nuevo límite aleatorio (8-15) usando Math.random(), resetea el contador y lo imprime.
    private void nuevoLimiteSiesta() {
        nuevaSiesta = (int) (Math.random() * (15 - 8 + 1)) + 8;
        clientesAtendidos = 0;
        System.out.println(nombrePelu + " decide que va a atender " + nuevaSiesta + " antes de la próxima siesta");
        System.out.println("*********************");
    }

    // Bucle principal de trabajo de la peluquera.
    @Override
    public void run() {
        while (gJ.verificarJornadaActiva() || hayClientesEnPuestos()) {
            boolean atencionExitosa = false;
            //itera mientras haya zonas de trabajo
            for (Zona zona : puestosDeTrabajo) {
                //si hay un puesto ocupado
                if (zona.hayClienteOcupandoPuesto()) {
                    if (zona.intentarAtender(this)) {
                        atencionExitosa = true;
                        if (zona.nombreZona.equals("Peinado")) {
                            gJ.registrarAtencion(nombrePelu);
                        }
                        //saca a la peluquera del bucle cuando encuentra trabajo
                        break; 
                    }
                }
            }
            if (atencionExitosa) {
                clientesAtendidos++;

                if (clientesAtendidos >= nuevaSiesta) {
                    iniciarSiesta();
                }
            } else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println(nombrePelu + " terminó su jornada.");
    }

    //Simula la siesta periódica de la peluquera.
    private void iniciarSiesta() {
        System.out.println(nombrePelu + " se retira a tomar una siesta.");
        try {
            // Uso de Math.random() para el tiempo de siesta también, siguiendo la preferencia.
            int tiempoDescanso = (int) (Math.random() * (1500 - 500 + 1)) + 500;
            Thread.sleep(tiempoDescanso);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(nombrePelu + " se despierta y vuelve al trabajo.");
        
        nuevoLimiteSiesta(); 
    }
    
    private boolean hayClientesEnPuestos() {
        for (Zona zona : puestosDeTrabajo) {
            if (zona.hayClienteOcupandoPuesto()) {
                return true;
            }
        }
        return false;
    }
}