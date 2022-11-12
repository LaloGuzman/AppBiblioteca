/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbiblioteca;

import java.util.GregorianCalendar;

/**
 *
 * @author Lalo Guzmán
 */
public class Devolucion {
    private GregorianCalendar fechaDevolucion;

    public Devolucion(GregorianCalendar fechaDevolucion) {
        setFechaDevolucion(fechaDevolucion);
    }

    /**
     * @return the fechaDevolucion
     */
    public GregorianCalendar getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * @param fechaDevolucion the fechaDevolucion to set
     */
    public void setFechaDevolucion(GregorianCalendar fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
}
