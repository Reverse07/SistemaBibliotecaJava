
package Modelo;

import java.util.Date;

public class Libro {

    private int id;
    private String titulo;
    private String autor;
    private Date anio_publicacion;
    private String isbn;
    private int stock;
    private String rutaImagen;
    private Categoria categoria;
    private String descripcion;

    public Libro() {
    }

    public Libro(int id, String titulo, String autor, Date anio_publicacion, String isbn, int stock, String rutaImagen, Categoria categoria, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anio_publicacion = anio_publicacion;
        this.isbn = isbn;
        this.stock = stock;
        this.rutaImagen = rutaImagen;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getAnio_publicacion() {
        return anio_publicacion;
    }

    public void setAnio_publicacion(Date anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
