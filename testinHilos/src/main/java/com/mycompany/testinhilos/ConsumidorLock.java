/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testinhilos;

/**
 *
 * @author Alumno
 */
public class ConsumidorLock extends Thread {

    private final CajaTomatesLock caja;
    private final int n;

    public ConsumidorLock(CajaTomatesLock caja, int n) {
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