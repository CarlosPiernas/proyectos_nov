/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package monitores;

import semaforos.*;
import com.mycompany.testinhilos.*;

/**
 *
 * @author Alumno
 */
public class MainMonitores {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int capacidad = 3, n = 10;
        CajaTomatesMonitores caja = new CajaTomatesMonitores(capacidad);
        
        Thread p = new ProductorMonitores(caja, n);
        Thread c = new ConsumidorMonitores(caja, n);
        
        p.start();
        c.start();
        System.out.println("Fin Locks");
    }
    
}
