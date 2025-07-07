
package Modelo;

import java.util.Date;


public class Devolucion {

    private int id;
    private Prestamo prestamo;
    private Date fechaDevolucion;
    private String observaciones;

    public Devolucion() {
    }

    public Devolucion(int id, Prestamo prestamo, Date fechaDevolucion, String observaciones) {
        this.id = id;
        this.prestamo = prestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}