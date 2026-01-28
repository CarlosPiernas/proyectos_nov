/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testinhilos;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Alumno
 */
public class CajaTomatesLock {

    private final Queue<Integer> caja = new ArrayDeque<>();
    private final int capacidad;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition noLlena = lock.newCondition();
    private final Condition noVacia = lock.newCondition();

    public CajaTomatesLock(int capacidad) {
        this.capacidad = capacidad;
    }

    public void poner(int tomate) throws InterruptedException {
        //estructura obligatoria de los locks
        lock.lock(); //cerramos el perimetro
        try {
            //siempre se pone el while para que siga verificando la condición
            while (caja.size() == capacidad) {
                noLlena.await(); //como la caja esta llena duerme este hilo
            }
            //esto pasa una vez despierto
            caja.add(tomate);
            System.out.println("Producido tomate " + tomate);
            noVacia.signal();//despierta el consumidor
            //siempre se pone el unlock en el finally
        } finally {
            lock.unlock();
        }
    }

    public int coger(int i) throws InterruptedException {
        lock.lock();
        try {
            while (caja.isEmpty()) {
                noVacia.await();
            }
            int t = caja.remove();
            System.out.println("Consumido tomate " + t);
            noLlena.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }
}
