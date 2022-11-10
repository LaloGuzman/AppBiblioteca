/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbiblioteca;

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
    
    private void msjError(String msj) {
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
