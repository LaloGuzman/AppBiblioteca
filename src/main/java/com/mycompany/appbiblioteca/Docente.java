/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbiblioteca;

/**
 *
 * @author Lalo Guzmán
 */
public class Docente extends Usuario{
    private String profesionDocente;
    private String gradoDocente;

    public Docente(String run, String nombre, char genero, String profesionDocente, String gradoDocente) {
        super(run, nombre, genero);
        setProfesionDocente(profesionDocente);
        setGradoDocente(gradoDocente);
    }

    /**
     * @return the profesionDocente
     */
    public String getProfesionDocente() {
        return profesionDocente;
    }

    /**
     * @param profesionDocente the profesionDocente to set
     */
    public void setProfesionDocente(String profesionDocente) {
        if (profesionDocente == null){
            msjError("Debe ingresar profesion del docente");        }
        this.profesionDocente = profesionDocente;
    }

    /**
     * @return the gradoDocente
     */
    public String getGradoDocente() {
        return gradoDocente;
    }

    /**
     * @param gradoDocente the gradoDocente to set
     */
    public void setGradoDocente(String gradoDocente) {
        this.gradoDocente = gradoDocente;
    }
    
    private void msjError(String msj) {
        throw new IllegalArgumentException(msj);
    }
    
    public static String toCSV(Docente usuario){
        String linea = usuario.getRun() + ";" + 
               usuario.getNombre() + ";" + 
               usuario.getGenero() + ";" + 
               usuario.getPrestamo() + ";" +
               ";" +
               usuario.getProfesionDocente()+ ";" +
               usuario.getGradoDocente();
        return linea;
    }
    
    @Override
    public String toString() {
        String texto = super.toString();
        texto += "Datos del Docente: \n" +
                "Profesión: " + getProfesionDocente() + "\n" +
                "Grado    : " + getGradoDocente() + "\n";     
        return texto;
    }
}
