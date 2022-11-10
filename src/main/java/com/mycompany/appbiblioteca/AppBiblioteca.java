/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.appbiblioteca;

/**
 *
 * @author Lalo Guzmán
 */
public class AppBiblioteca {

    public static void main(String[] args) {
        System.out.println("Bienvenido al Sistema de Bibliotecas - Grupo 15");
        System.out.println("===============================================\n");
        
        Usuario u1 = new Usuario("10606035-5", "Lalo", 'F');
        System.out.println(u1.toString());
    }
}
