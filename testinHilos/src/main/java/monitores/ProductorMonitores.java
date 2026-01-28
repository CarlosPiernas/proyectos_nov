/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package monitores;

import semaforos.*;
import com.mycompany.testinhilos.*;

/**
 *
 * @author Alumno
 */
public class ProductorMonitores extends Thread {
    
    private final CajaTomatesMonitores caja;
    private final int n;
    
    public ProductorMonitores(CajaTomatesMonitores caja, int n){
        this.caja = caja;
        this.n = n;
    }
    
    @Override
    public void run() {
        try{
            for (int i = 0; i <= n; i++) {
                caja.poner(i);
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
