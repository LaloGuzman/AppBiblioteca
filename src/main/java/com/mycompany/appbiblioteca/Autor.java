/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbiblioteca;

import java.util.ArrayList;

/**
 *
 * @author Lalo Guzmán
 */
public class Autor {
    private String nombreAutor;

    public Autor(String nombreAutor) {
        setNombreAutor(nombreAutor);
    }

    /**
     * @return the nombreAutor
     */
    public String getNombreAutor() {
        return nombreAutor;
    }

    /**
     * @param nombreAutor the nombreAutor to set
     */
    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }
    
    private static void msjError(String msj) {
        throw new IllegalArgumentException(msj);
    }
    
    public static Autor validaAutor(String nombre, ArrayList<Autor> autores){
        for (int i = 0; i < autores.size(); i++) {
            if (nombre.equals(autores.get(i).getNombreAutor())){
                Autor autor = new Autor(nombre);
                return autor;
            }
        }
        msjError("Autor no existe...");
        return null;
    }
    
    @Override
    public String toString() {
        return getNombreAutor();
    }
}
