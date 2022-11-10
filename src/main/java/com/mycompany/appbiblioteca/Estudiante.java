/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbiblioteca;

/**
 *
 * @author Lalo Guzmán
 */
public class Estudiante extends Usuario{
    private String carreraEstudiante;

    public Estudiante(String run, String nombre, char genero, String carreraEstudiante) {
        super(run, nombre, genero);
        setCarreraEstudiante(carreraEstudiante);
    }

    /**
     * @return the carreraEstudiante
     */
    public String getCarreraEstudiante() {
        return carreraEstudiante;
    }

    /**
     * @param carreraEstudiante the carreraEstudiante to set
     */
    public void setCarreraEstudiante(String carreraEstudiante) {
        if (carreraEstudiante == null) {
            msjError("Debe ingresar carrera del estudiante.");
        }
        this.carreraEstudiante = carreraEstudiante;
    }
    
    private void msjError(String msj) {
        throw new IllegalArgumentException(msj);
    }   
    
    @Override
    public String toString() {
        String texto = super.toString();
        texto += "Datos del Estudiante: \n" +
                "Carrera   : " + getCarreraEstudiante() + "\n" ;     
        return texto;
    }
}
