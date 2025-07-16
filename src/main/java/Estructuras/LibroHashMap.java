
package Estructuras;

import Modelo.Libro;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LibroHashMap {
    
    
    private Map<String, Libro> mapaISBN = new HashMap<>();

    public void cargarLibros(List<Libro> libros) {
        for (Libro l : libros) {
            mapaISBN.put(l.getIsbn(), l);
        }
    }

    public Libro buscarPorISBN(String isbn) {
        return mapaISBN.getOrDefault(isbn, null);
    }

    public void imprimirTodos() {
        mapaISBN.forEach((k, v) -> System.out.println("ISBN: " + k + " → " + v.getTitulo()));
    }
}

