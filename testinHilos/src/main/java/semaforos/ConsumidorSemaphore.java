/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semaforos;

import com.mycompany.testinhilos.*;

/**
 *
 * @author Alumno
 */
public class ConsumidorSemaphore extends Thread {

    private final CajaTomatesSemaphore caja;
    private final int n;

    public ConsumidorSemaphore(CajaTomatesSemaphore caja, int n) {
        this.caja = caja;
        this.n = n;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i <= n; i++) {
                caja.coger(i);

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    }