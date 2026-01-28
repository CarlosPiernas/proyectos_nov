/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.testinhilos;

/**
 *
 * @author Alumno
 */
public class ProductorLock extends Thread {
    
    private final CajaTomatesLock caja;
    private final int n;
    
    public ProductorLock(CajaTomatesLock caja, int n){
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
