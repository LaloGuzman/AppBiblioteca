/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbiblioteca;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Lalo Guzmán
 */
public class Prestamo {
    private Usuario usuario;
    private Libro libro;
    private GregorianCalendar fechaPrestamo;
    private int diasPrestamo;
    private Devolucion devolucion;

    public Prestamo(Usuario usuario, Libro libro, GregorianCalendar fechaPrestamo, int diasPrestamo) {
        setUsuario(usuario);
        setLibro(libro);
        setFechaPrestamo(fechaPrestamo);
        setDiasPrestamo(diasPrestamo);
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the libro
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * @param libro the libro to set
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * @return the fechaPrestamo
     */
    public GregorianCalendar getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * @param fechaPrestamo the fechaPrestamo to set
     */
    public void setFechaPrestamo(GregorianCalendar fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * @return the diasPrestamo
     */
    public int getDiasPrestamo() {
        return diasPrestamo;
    }

    /**
     * @param diasPrestamo the diasPrestamo to set
     */
    public void setDiasPrestamo(int diasPrestamo) {
        this.diasPrestamo = diasPrestamo;
    }

    /**
     * @return the devolucion
     */
    public Devolucion getDevolucion() {
        return devolucion;
    }

    /**
     * @param devolucion the devolucion to set
     */
    public void setDevolucion(Devolucion devolucion) {
        this.devolucion = devolucion;
    }
    
    private static void msjError(String msj) {
        throw new IllegalArgumentException(msj);
    }
    
    private static GregorianCalendar calculaFechaEntrega(GregorianCalendar fechaP, int diasPrestamo){
        GregorianCalendar fpGC = new GregorianCalendar(fechaP.get(Calendar.YEAR),fechaP.get(Calendar.MONTH), fechaP.get(Calendar.DAY_OF_MONTH));
        fpGC.add(Calendar.DAY_OF_MONTH, diasPrestamo);
        return fpGC;
    } 
    
    private static void imprimirTarjeta(Prestamo prestamo, GregorianCalendar fechaEntrega){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");   
        System.out.println("\n\n" + "===================================================================================================\n"+
                "\t\t\tT A R J E T A    D E   P R E S T A M O" + "\n" +
                "\t\t\t                U N A B" + "\n\n" +
                "\t\t_________________________________________________________________" + "\n" +
                "\t\tUSUARIO          : " + prestamo.getUsuario().getRun() + " / " + prestamo.getUsuario().getNombre() + "\n" +
                "\t\tLIBRO            : " + prestamo.getLibro().getIsbn() + " / " + prestamo.getLibro().getTitulo() + "\n" +
                "\t\tAUTOR            : " + prestamo.getLibro().getAutor().getNombreAutor() + "\n\n" +
                "\t\tFECHA PRESTAMO   : " + sdf.format(prestamo.getFechaPrestamo().getTime()) + "\n" +
                "\t\tDIAS PRESTAMOS   : " + prestamo.getDiasPrestamo()+ "\n" +
                "\t\tFECHA DEVOLUCION : " + sdf.format(fechaEntrega.getTime())+ "\n" +
                "\t\t_________________________________________________________________" + "\n" +
                "\n" + "===================================================================================================\n"
                );
    }
    
    public String obtenerTipoDeUsuario() {
        if (getUsuario() instanceof Docente) {
            return "Docente";
        }
        return "Estudiante";
    }
    
    public static Prestamo ingresarPrestamo(String ISBN, String RUN, int diasPrestamo, ArrayList<Libro> libros, ArrayList<Usuario> usuarios) {
        Libro libro = buscarLibro(ISBN, libros);
        
        Usuario usuario = buscarUsuario(RUN, usuarios);
        
        if (libro.getCantidadDisponible() < 1) {
            msjError("No existe ejemplares disponibles para prestar del libro: " + libro.getIsbn());
        }

        if (!usuario.getPrestamo().equals("0")){
            msjError("Usuario " + usuario.getRun() + " ya tiene un prestamo vigente...");
        }
        
        if ((usuario instanceof Docente) && (diasPrestamo > 20)) {
                msjError("Docente no puede recibir préstamo por mas de 20 días");
        }
        
        if ((usuario instanceof Estudiante) && (diasPrestamo > 10)) {
                msjError("Estudiante no puede recibir préstamo por mas de 10 días");
        }
        
        Calendar hoy = new GregorianCalendar();
        GregorianCalendar hoyGC = new GregorianCalendar(hoy.get(Calendar.YEAR),hoy.get(Calendar.MONTH), hoy.get(Calendar.DAY_OF_MONTH));
        System.out.println("Fecha de hoy: " + hoyGC.getTime());
        GregorianCalendar fechaEntrega = new GregorianCalendar();
        fechaEntrega = calculaFechaEntrega(hoyGC, diasPrestamo);

        // GENERAMOS UNA INSTANCIA DE PRESTAMO
        Prestamo prestamo = new Prestamo(usuario, libro, hoyGC, diasPrestamo);
        
        // IMPRIMIMOS TARJETA
        imprimirTarjeta(prestamo, fechaEntrega);

        // ---------------- LO QUE SE DEBE HACER A CONTINUACIÓN SE PUEDE REALIZAR DENTRO DE ÉSTE MÉTODO Ó ----------------
        // ----------------------------- DENTRO DE LA INSTANCIACIÓN DEL OBJETO -------------------------------------------
        // REDUCIMOS LA CANTIDAD DISPONIBLE DEL LIBRE Y AUMENTAMOS LA CANTIDAD EN USO
        // DEJAMOS AL USUARIO NO DISPONIBLE PARA EL NUEVO PRÉSTAMO
        // 
        
        // RETORNAMOS EL PRÉSTAMO VALIDADO
        return prestamo;
    }
    
    public static Libro buscarLibro(String ISBN, ArrayList<Libro> libros) {
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            if (ISBN.equals(libro.getIsbn())) {
                return libro;
            }
        }
        msjError("Libro a prestar NO existe");
        return null;
    }
    
    public static Usuario buscarUsuario(String RUN, ArrayList<Usuario> usuarios) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (RUN.equals(usuarios.get(i).getRun())) {
                return usuarios.get(i);
            }
        }
        msjError("Usuario del préstamo NO existe...");
        return null;
    }
    
    @Override
    public String toString() {
        // GENERAMOS UN ESTADO BASE
        String texto = "Prestamo: \n" + 
                "ISBN: " + getLibro().getIsbn() + "\n" +
                "RUN: " + getUsuario().getRun() + "\n" +
                "Arrendado por: " + obtenerTipoDeUsuario() + "\n" + 
                "Estado: ";
        
        // LO MODIFICAMOS EN BASE A LA DEVOLUCIÓN
        if (getDevolucion() == null) {
            texto += "En préstamo.";
        } else {
            texto += "Devuelto.";
        }
        return texto;
    }
}
