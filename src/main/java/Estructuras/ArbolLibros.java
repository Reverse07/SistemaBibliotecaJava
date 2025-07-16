
package Estructuras;

import Modelo.Libro;


public class ArbolLibros {
private NodoLibro raiz;

    public void insertar(Libro libro) {
        raiz = insertarRec(raiz, libro);
    }

    private NodoLibro insertarRec(NodoLibro nodo, Libro libro) {
        if (nodo == null) return new NodoLibro(libro);

        if (libro.getTitulo().compareToIgnoreCase(nodo.libro.getTitulo()) < 0) {
            nodo.izquierda = insertarRec(nodo.izquierda, libro);
        } else {
            nodo.derecha = insertarRec(nodo.derecha, libro);
        }

        return nodo;
    }

    public void inOrden() {
        inOrdenRec(raiz);
    }

    private void inOrdenRec(NodoLibro nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierda);
            System.out.println("📘 " + nodo.libro.getTitulo() + " - " + nodo.libro.getAutor());
            inOrdenRec(nodo.derecha);
        }
    }

    public Libro buscarPorTitulo(String titulo) {
        return buscarRec(raiz, titulo);
    }

    private Libro buscarRec(NodoLibro nodo, String titulo) {
        if (nodo == null) return null;
        if (titulo.equalsIgnoreCase(nodo.libro.getTitulo())) return nodo.libro;

        if (titulo.compareToIgnoreCase(nodo.libro.getTitulo()) < 0)
            return buscarRec(nodo.izquierda, titulo);
        else
            return buscarRec(nodo.derecha, titulo);
    }
}
