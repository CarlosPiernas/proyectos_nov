/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package semaforos;

import com.mycompany.testinhilos.*;

/**
 *
 * @author Alumno
 */
public class MainSemaphore {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int capacidad = 3, n = 10;
        CajaTomatesSemaphore caja = new CajaTomatesSemaphore(capacidad);
        
        Thread p = new ProductorSemaphore(caja, n);
        Thread c = new ConsumidorSemaphore(caja, n);
        
        p.start();
        c.start();
        System.out.println("Fin Locks");
    }
    
}
