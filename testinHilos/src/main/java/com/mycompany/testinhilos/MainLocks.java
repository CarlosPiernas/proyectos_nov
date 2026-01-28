/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.testinhilos;

/**
 *
 * @author Alumno
 */
public class MainLocks {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int capacidad = 3, n = 10;
        CajaTomatesLock caja = new CajaTomatesLock(capacidad);
        
        Thread p = new ProductorLock(caja, n);
        Thread c = new ConsumidorLock(caja, n);
        
        p.start();
        c.start();
        System.out.println("Fin Locks");
    }
    
}
