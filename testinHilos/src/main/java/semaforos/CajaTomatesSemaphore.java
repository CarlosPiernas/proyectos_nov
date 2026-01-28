/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semaforos;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Alumno
 */
public class CajaTomatesSemaphore {

    private final Queue<Integer> caja = new ArrayDeque<>();
    private final Semaphore huecos;
    private final Semaphore tomates;
    private final Semaphore mutex = new Semaphore(1);

    public CajaTomatesSemaphore(int capacidad) {
        huecos = new Semaphore(capacidad);
        tomates = new Semaphore(0);
    }

    public void poner(int tomate) throws InterruptedException {
        huecos.acquire(); //espera hueco
        mutex.acquire(); //entra zona critica
        caja.add(tomate); //pone un tomate
        System.out.println("Producido tomate " + tomate);
        mutex.release(); //sale de lazna critica
        tomates.release(); //anuncia que hay tomates
    }

    public int coger(int i) throws InterruptedException {
        tomates.acquire(); //espera a que haya tomates
        mutex.acquire(); //entra en zona critica
        int t = caja.remove(); //coge un tomate
        System.out.println("Consumido tomate " + t);
        mutex.release(); //sale de la zona critica
        huecos.release(); //anuncia que hay huecos libres
        return t;
    }
}
