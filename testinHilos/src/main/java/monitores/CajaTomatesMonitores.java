/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monitores;

import semaforos.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Alumno
 */
public class CajaTomatesMonitores {

    private final Queue<Integer> caja = new ArrayDeque<>();
    private final int capacidad;

    public CajaTomatesMonitores(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void poner(int tomate) throws InterruptedException {
        while (caja.size() == capacidad){
            wait();
        }
        caja.add(tomate);
        System.out.println("Producido tomate " + tomate);
        notifyAll();
    }

    public synchronized int coger(int i) throws InterruptedException {
        while (caja.isEmpty()){
            wait();
        }
        int t = caja.remove();
        System.out.println("Consumido tomate " + t);
        notifyAll();
        return t;
    }
}
