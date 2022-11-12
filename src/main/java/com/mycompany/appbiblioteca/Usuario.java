/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbiblioteca;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Lalo Guzmán
 */
public class Usuario {
    private String run;
    private String nombre;
    private char genero;
    private String prestamo;

    public Usuario(String run, String nombre, char genero) {
        setRun(run);
        setNombre(nombre);
        setGenero(genero);
        setPrestamo("0");
    }

    /**
     * @return the run
     */
    public String getRun() {
        return run;
    }

    /**
     * @param rut the run to set
     */
    public void setRun(String run) {
        if (!validarRUN(run)) {
            msjError("RUT Inválido");
        }
        this.run = run.toUpperCase();
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the genero
     */
    public char getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(char genero) {
        if (genero != 'M' && genero != 'F') {
            msjError("Genero debe ser M o F");
        }
        this.genero = genero;
    }

    /**
     * @return the prestamo
     */
    public String getPrestamo() {
        return prestamo;
    }

    /**
     * @param prestamo the prestamo to set
     */
    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }
    
    private  boolean validarRUN(String run){
        run = run.toUpperCase();
        //Validamos largo mínimo para evitar el típico 1-9
        if (run.length() < 8 || run.length() > 11) return false;
        
        //Validamos que el rut tenga formato XXXXXX-X
        Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(run);
        if ( matcher.matches() == false ) return false;
        
        //Validamos dígito verificador
        String[] stringRun = run.split("-");
        int runInt = Integer.parseInt( stringRun[0] );
        char dv = stringRun[1].charAt(0);
        int contador = 2;
        int acumulado = 0;
        int multiplo = 0;
        while (runInt != 0){
            multiplo=(runInt % 10) * contador;
            acumulado += multiplo;
            runInt = runInt / 10;
            contador += 1;
            if(contador == 8) contador = 2;
        }
        int resto = acumulado % 11;
        String digito = String.valueOf(11 - resto);
        if (digito.equals("11")) {
            digito = "0";
        }
        if (digito.equals("10")) {
            digito = "K";
        }
        
        if (dv != digito.charAt(0)) return false;
        
        return true;
    }
    
    private static void msjError(String msj) {
        throw new IllegalArgumentException(msj);
    }   
    
    public static void validaUnico(String RUN, ArrayList<Usuario> usuarios){
        for (int i = 0; i < usuarios.size(); i++) {
            if (RUN.equals(usuarios.get(i).getRun())){
                msjError("RUT de usuario debe ser único");
            }
        }
    }
    
    public  ArrayList<Usuario> editUsuario(String ISBN, ArrayList<Usuario> usuarios){
        for (int i = 0; i < usuarios.size(); i++) {
            if (getRun().equals(usuarios.get(i).getRun())){
                setPrestamo(ISBN);
                usuarios.get(i).setPrestamo(ISBN);
                return usuarios;
            }
        }
        return usuarios;
    }
    
    public  ArrayList<Usuario> delUsuario(ArrayList<Usuario> usuarios){
        boolean existe = false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (getRun().equals(usuarios.get(i).getRun())){
                usuarios.remove(i);
                existe = true;
                return usuarios;
            }
        }
        if (!existe) {
            msjError("Usuario a borrar no existe");
        }
        return null;
    }
    
    @Override
    public String toString() {
        String texto = "RUN       : " + getRun() + "\n" +
               "NOMBRE    : " + getNombre() + "\n" +
               "GENERO    : " + getGenero() + "\n" +
               "PRESTAMO  : ";
        
           if (getPrestamo() == "0") {
               texto += "Sin préstamo vigente \n";
           }else{
               texto += "Con prestamo vigente (" + getPrestamo() + ")\n";
           }
        return texto;
    }
}
