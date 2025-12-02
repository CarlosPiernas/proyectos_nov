/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controlador;

import interfaz_pelu.Login;

/**
 *
 * @author Carlos
 */
public class Controlador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Crea el objeto Login
        Login log = new Login();
        //Hace visible la ventana de login
        log.abrirLogin();
    }
    
}
