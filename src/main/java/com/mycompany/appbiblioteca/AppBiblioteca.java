/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.appbiblioteca;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lalo Guzmán
 */
public class AppBiblioteca {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        // LLENAMOS ARREGLO DE USUARIOS DESDE ARCHIVO 
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
            if (!"".equals(datos[3])){
                Estudiante usr = new Estudiante (datos[0], datos[1], datos[2].charAt(0), datos[3]);
                usuarios.add(usr);
            }else{
                Docente usr = new Docente (datos[0], datos[1], datos[2].charAt(0), datos[4], datos[5]);
                usuarios.add(usr);
            }
        }
        lector.close();
        
        /* DESCOMENTAR PARA VERIFICAR QUE LA CARGA EN EL ARRAY FUE CORRECTA
        System.out.println("Parte for...\n");
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i) instanceof Docente) {
                System.out.println("docente: " + usuarios.get(i));
            }
            if (usuarios.get(i) instanceof Estudiante) {
                System.out.println("Estudiante: " + usuarios.get(i));
            }
        } */
        
        
        // LLENAMOS ARREGLO AUTOR DESDE ARCHIVO 
        //-------------------------------------------
        File archivoAutor = new File("autores.csv");
        if (!archivoAutor.exists()) {
            throw new IllegalArgumentException("El archivo 'autores.csv' no existe.");
        }
        ArrayList<Autor> autores = new ArrayList<Autor>();
        Scanner lectorAutor = new Scanner(archivoAutor);
        while(lectorAutor.hasNextLine()) {
            String linea = lectorAutor.nextLine();
            String datos[] = linea.split(";");
            Autor autor = new Autor (datos[0]);
            autores.add(autor);
        }
        lectorAutor.close();
        
        
        // LLENAMOS ARREGLO DE LIBROS DESDE ARCHIVO
        //-------------------------------------------
        File archivoLibros = new File("libros.csv");
        if (!archivoLibros.exists()) {
            throw new IllegalArgumentException("El archivo 'libros.csv' no existe.");
        }
        ArrayList<Libro> libros = new ArrayList<Libro>();
        Scanner lectorLibro = new Scanner(archivoLibros);
        while(lectorLibro.hasNextLine()) {
            String linea = lectorLibro.nextLine();
            String datos[] = linea.split(";");
            Autor autor = Autor.validaAutor(datos[2], autores);
            Libro libro = new Libro (datos[0], datos[1], autor, Integer.parseInt(datos[3]), Integer.parseInt(datos[4]), datos[5]);
            libros.add(libro);
        }
        lectorAutor.close();
        
        // ARREGLO DE PRESTAMOS
        // -----------------------
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        
        impTitulo("Bienvenido al Sistema de Bibliotecas - Grupo 15", "=");
        impTitulo("1.- Usuarios:", "=");
        impTitulo("1.2.- Instanciar y agregar Usuarios: (Estudiante y Docente", "-");
        
        // GENERAMOS UN USUARIO-ESTUDIANTE
        // -------------------------------
        Estudiante e1 = new Estudiante("15331749-6", "María Estudiante", 'F', "Ingeniería en Minas");
        System.out.println(e1.toString());
        //VALIDA USUARIO, SI NO FALLA SE AGREGA AL ARREGLO
        //-------------------------------------------------
        Usuario.validaUnico(e1.getRun(), usuarios);
        usuarios.add(e1);
        
        // GENERAMOS UN USUARIO-DOCENTE
        //-----------------------------
        Docente d1 = new Docente("4004562-7", "Nicolás Docente", 'M', "Ingeniero en Ciberseguridad", "MBA");
        System.out.println(d1.toString());
        Usuario.validaUnico(d1.getRun(), usuarios);
        usuarios.add(d1);
        
        impTitulo("1.2.- Editar usuario usuario (cambio de estado)", "-");
        // EDITAMOS USUARIO CAMBIANDO ESTADO DEL PRESTAMO
        //-----------------------------------------------
        usuarios = e1.editUsuario("333-33-33033-33-3", usuarios);
        System.out.println("Nuevo estado Usuario: \n");
        System.out.println(e1.toString());
        
        /* DESCOMENTAR PARA VERIFICAR SI USUARIO SE AGREGO AL ARREGLO
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
        // ELIMINAMOS UN USUARIO
        //----------------------
        usuarios = e1.delUsuario(usuarios);
        System.out.println("Usuario : " + e1.getRun() + " eliminado... \n");
        
        /* DESCOMENTAR PARA VERIFICAR SI SE BORRO USUARIO DESDE EL ARREGLO 
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
        // GENERAMOS UN AUTOR Y UN LIBRO
        //------------------------------
        Autor a1 = Autor.validaAutor("Joshua Bloch", autores);
        Libro l1 = new Libro("978-2-666-66666-6","Java para novatos",a1, 10, 10, "imagenes/libro123.jpg");
        
        //VALIDA LIBRO, SI NO FALLA SE AGREGA AL ARREGLO
        //-------------------------------------------------
        Libro.validaUnico(l1.getIsbn(), libros);
        System.out.println(l1.toString());
        libros.add(l1);
        
        impTitulo("2.3.- Eliminar Libros", "-");
        // ELIMINAMOS UN LIBRO
        // --------------------
        libros = l1.delLibro(libros);
        System.out.println("Libro : " + l1.getIsbn() + " eliminado... \n");
        
        /* DESCOMENTAR PARA VERIFICAR QUE EL LIBRO FUE ELIMINADO DEL ARREGLO
        System.out.println("Libros en el arreglo...\n");
        for (int i = 0; i < libros.size(); i++) {
            System.out.println("Libro: " + libros.get(i));
        } */       
        
        impTitulo("3.- Préstamos", "=");
        impTitulo("3.1.- Ingresar Préstamos", "-");
        // GENERAMOS UN PRESTAMO
        //----------------------
        Prestamo p1 = Prestamo.ingresarPrestamo("978-2-409-02961-5", "3085920-0", 11, libros, usuarios);
        prestamos.add(p1);
        
        // ACTUALIZAMOS USUARIO
        //---------------------
        usuarios = p1.getUsuario().editUsuario(p1.getLibro().getIsbn(), usuarios);
        
        // ACTUALIZAMOS DISPONIBILIDAD DE LIBRO 
        //-------------------------------------
        libros = p1.getLibro().editLibro(p1.getLibro().getIsbn(), libros, 'P');
        /* DESCOMENTAR PARA VERIFICAR QUE EL LIBRO FUE DESCONTADO EN EL ARREGLO 
        System.out.println("Libros en el arreglo...\n");
        for (int i = 0; i < libros.size(); i++) {
            System.out.println(libros.get(i));
        }   */
        
        impTitulo("3.2.- Ingresar Devolución", "-");
        // GENERAMOS UNA DEVOLUCION
        //--------------------------
        prestamos = Prestamo.ingresaDevolucion("978-2-409-02961-5", "3085920-0", prestamos, libros, usuarios);
        usuarios = p1.getUsuario().editUsuario("0", usuarios);
        libros = p1.getLibro().editLibro("978-2-409-02961-5", libros, 'P');
        
        // GENERAMOS ARCHIVOS CON ACTUALIZACIONES DEL PROCESO
        //---------------------------------------------------
        // LIBROS
        FileWriter aLibros = new FileWriter("librosOUT.csv");
        for (int i = 0; i < libros.size(); i++) {
            String linea = libros.get(i).getIsbn() + ";" + 
                           libros.get(i).getTitulo() + ";" + 
                           libros.get(i).getAutor().getNombreAutor() + ";" + 
                           libros.get(i).getCantidadBiblioteca() + ";" +
                           libros.get(i).getCantidadDisponible() + ";" +
                           libros.get(i).getImagen()
                    ;
            aLibros.write(linea + "\n");
        }
        aLibros.close();
        System.out.println("Archivo librosOUT.csv generado...");
        
        //USUARIOS
        FileWriter aUsuarios = new FileWriter("usuariosOUT.csv");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario instanceof Docente) {
                String linea = ((Docente) usuario).getRun() + ";" + 
                               ((Docente) usuario).getNombre() + ";" + 
                               ((Docente) usuario).getGenero() + ";" + 
                               ((Docente) usuario).getPrestamo() + ";" +
                               ";" +
                               ((Docente) usuario).getProfesionDocente() + ";" +
                               ((Docente) usuario).getGradoDocente()
                        ;
                aUsuarios.write(linea + "\n");
            } else {
                String linea = ((Estudiante) usuario).getRun() + ";" + 
                               ((Estudiante) usuario).getNombre() + ";" + 
                               ((Estudiante) usuario).getGenero() + ";" + 
                               ((Estudiante) usuario).getCarreraEstudiante() + ";" +
                               ((Estudiante) usuario).getPrestamo() + ";" +
                               ";"
                        ;    
                aUsuarios.write(linea + "\n");
            }
        }
        aUsuarios.close();
        System.out.println("Archivo usuariosOUT.csv generado...");

        //PRESTAMOS
        FileWriter aPrestamos = new FileWriter("prestamosOUT.csv");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (int i = 0; i < prestamos.size(); i++) {
            String linea = prestamos.get(i).getLibro().getIsbn() + ";" + 
                           prestamos.get(i).getUsuario().getRun() + ";" + 
                           sdf.format(prestamos.get(i).getFechaPrestamo().getTime()) + ";" + 
                           prestamos.get(i).getDiasPrestamo() + ";" +
                           sdf.format(prestamos.get(i).getDevolucion().getFechaDevolucion().getTime())
                    ;
            aPrestamos.write(linea + "\n");
        }
        aPrestamos.close();
        System.out.println("Archivo prestamosOUT.csv generado...");
    }
    
    public static void impTitulo(String txt, String c){
        System.out.println(txt);
        System.out.println(c.repeat(txt.length()) + "\n");
    }
}
