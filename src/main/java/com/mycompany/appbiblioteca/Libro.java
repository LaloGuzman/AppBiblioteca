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
public class Libro {
    private String isbn;
    private String titulo;
    private Autor autor;
    private int cantidadBiblioteca;
    private int cantidadDisponible;
    private String imagen;

    public Libro(String isbn, String titulo, Autor autor, int cantidadBiblioteca, int cantidadDisponible, String imagen) {
        setIsbn(isbn);
        setTitulo(titulo);
        setAutor(autor);
        setCantidadBiblioteca(cantidadBiblioteca);
        setCantidadDisponible(cantidadDisponible);
        setImagen(imagen);
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        if (isbn.length() != 17){
            msjError("ISBN debe ser de largo 17...");
        }
        this.isbn = isbn;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the autor
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * @return the cantidadBiblioteca
     */
    public int getCantidadBiblioteca() {
        return cantidadBiblioteca;
    }

    /**
     * @param cantidadBiblioteca the cantidadBiblioteca to set
     */
    public void setCantidadBiblioteca(int cantidadBiblioteca) {
        if (cantidadBiblioteca < 1) {
            msjError("Cantidad de Libros en Biblioteca debe ser mayor que cero...");
        }
        this.cantidadBiblioteca = cantidadBiblioteca;
    }

    /**
     * @return the cantidadDisponible
     */
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    /**
     * @param cantidadDisponible the cantidadDisponible to set
     */
    public void setCantidadDisponible(int cantidadDisponible) {
        if (cantidadDisponible < 1 || cantidadDisponible > this.getCantidadBiblioteca()){
            msjError("Cantidad Disponible debe ser mayor a 0 y menor o igual que la cantidad de libros en biblioteca...");
        }
        this.cantidadDisponible = cantidadDisponible;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public static void validaUnico(String ISBN, ArrayList<Libro> libros){
        for (int i = 0; i < libros.size(); i++) {
            if (ISBN.equals(libros.get(i).getIsbn())){
                msjError("ISBN del Libro debe ser único");
            }
        }
    }
    
    public  ArrayList<Libro> delLibro(ArrayList<Libro> libros){
        boolean existe = false;
        for (int i = 0; i < libros.size(); i++) {
            if (getIsbn().equals(libros.get(i).getIsbn())){
                libros.remove(i);
                existe = true;
                return libros;
            }
        }
        if (!existe) {
            msjError("Libro a borrar no existe...");
        }
        return null;
    }
    
        public  ArrayList<Libro> editLibro(String ISBN, ArrayList<Libro> libros){
        for (int i = 0; i < libros.size(); i++) {
            if (getIsbn().equals(libros.get(i).getIsbn())){
                libros.get(i).setCantidadDisponible(libros.get(i).getCantidadDisponible()-1);
                return libros;
            }
        }
        return null;
    }
    
    private static void msjError(String msj) {
        throw new IllegalArgumentException(msj);
    }
    
    @Override
    public String toString() {
        String texto = 
               "ISBN           : " + getIsbn() + "\n" +
               "TITULO         : " + getTitulo() + "\n" +
               "AUTOR          : " + getAutor().toString() + "\n" +
               "CANTIDAD TOTAL : " + getCantidadBiblioteca() + "\n" +
               "DISPONIBLES    : " + getCantidadDisponible() + "\n" +
               "IMAGEN         : " + getImagen() + "\n";
        return texto;
    }
}
