/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.appbiblioteca;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lalo Guzmán
 */
public class AppBiblioteca {

    public static void main(String[] args) throws FileNotFoundException {
        
        // Llenamos Arreglo de Usuarios desde Archivo 
        //-------------------------------------------
        File archivo = new File("usuarios.csv");
        if (!archivo.exists()) {
            throw new IllegalArgumentException("El archivo 'usuarios.csv' no existe.");
        }
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        Scanner lector = new Scanner(archivo);
        while(lector.hasNextLine()) {
            String linea = lector.nextLine();
            String datos[] = linea.split(";");
            if (datos[3] != ""){
                Estudiante usr = new Estudiante (datos[0], datos[1], datos[2].charAt(0), datos[3]);
                usuarios.add(usr);
            }else{
                Docente usr = new Docente (datos[0], datos[1], datos[2].charAt(0), datos[4], datos[5]);
                usuarios.add(usr);
            }
        }
        lector.close();
        
        /* descomentar para verificar que la carga es correcta por tipo de usuario
        System.out.println("Parte for...\n");
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i) instanceof Docente) {
                System.out.println("docente: " + usuarios.get(i));
            }
            if (usuarios.get(i) instanceof Estudiante) {
                System.out.println("Estudiante: " + usuarios.get(i));
            }
        } */
        
        impTitulo("Bienvenido al Sistema de Bibliotecas - Grupo 15", "=");
        impTitulo("1.- Usuarios:", "=");
        impTitulo("1.2.- Instanciar y agregar Usuarios: (Estudiante y Docente", "-");
                
        Estudiante e1 = new Estudiante("15331749-6", "María Estudiante", 'F', "Ingeniería en Minas");
        System.out.println(e1.toString());
        //Valida Usuario, si no falla lo agregamos al arreglo
        Usuario.validaUnico(e1.getRun(), usuarios);
        usuarios.add(e1);
        
        Docente d1 = new Docente("4004562-7", "Nicolás Docente", 'M', "Ingeniero en Ciberseguridad", "MBA");
        System.out.println(d1.toString());
        Usuario.validaUnico(d1.getRun(), usuarios);
        usuarios.add(d1);
        
        impTitulo("1.2.- Editar usuario usuario (cambio de estado)", "-");
        usuarios = e1.editUsuario("333-33-33033-33-3", usuarios);
        System.out.println("Nuevo estado Usuario: \n");
        System.out.println(e1.toString());
        
        /* Descomentar para verificar que también se cambió estado en el arreglo Usuarios
        System.out.println("Usuarios en el arreglo...\n");
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i) instanceof Docente) {
                System.out.println("docente: " + usuarios.get(i));
            }
            if (usuarios.get(i) instanceof Estudiante) {
                System.out.println("Estudiante: " + usuarios.get(i));
            }
        } */
        
        impTitulo("1.3.- Eliminar usuario", "-");
        usuarios = e1.delUsuario(usuarios);
        System.out.println("Usuario : " + e1.getRun() + " eliminado... \n");
        
        /* Descomentar para verificar que se elimino desde el arreglo Usuarios 
        System.out.println("Usuarios en el arreglo...\n");
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i) instanceof Docente) {
                System.out.println("docente: " + usuarios.get(i));
            }
            if (usuarios.get(i) instanceof Estudiante) {
                System.out.println("Estudiante: " + usuarios.get(i));
            }
        }*/
        
        
        impTitulo("2.- Libros", "=");
        impTitulo("2.2.- Crear Libros", "-");
        
        impTitulo("2.3.- Eliminar Libros", "-");
        
        impTitulo("3.- Préstamos", "=");
        impTitulo("3.1.- Ingresar Préstamos", "-");
        
        impTitulo("3.2.- Ingresar Devolución", "-");
        
    }
    
    public static void impTitulo(String txt, String c){
        System.out.println(txt);
        System.out.println(c.repeat(txt.length()) + "\n");
    }
}
