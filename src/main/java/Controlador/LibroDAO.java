package Controlador;

import Modelo.Categoria;
import Modelo.Libro;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class LibroDAO {

       public List<Libro> obtenerTodos() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT l.*, c.nombre AS nombre_categoria FROM libros l "
                   + "LEFT JOIN categorias c ON l.id_categoria = c.id";

        try (Connection con = Conexion.getConnection(); 
             Statement stmt = con.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Categoria c = new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));
                Libro l = new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anio_publicacion"), // ← ya no getDate
                        rs.getString("isbn"),
                        rs.getInt("stock"),
                        rs.getString("rutaImagen"),
                        c,
                        rs.getString("descripcion")
                );

                libros.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public Libro obtenerPorId(int id) {
        String sql = "SELECT l.*, c.nombre AS nombre_categoria FROM libros l "
                   + "JOIN categorias c ON l.id_categoria = c.id WHERE l.id = ?";

        try (Connection con = Conexion.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categoria c = new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));
                return new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("anio_publicacion"),
                        rs.getString("isbn"),
                        rs.getInt("stock"),
                        rs.getString("rutaImagen"),
                        c,
                        rs.getString("descripcion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertar(Libro libro) {
        String sql = "INSERT INTO libros (titulo, autor, anio_publicacion, id_categoria, isbn, stock, rutaImagen, descripcion) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getAnio_publicacion()); // ← ya no setDate
            stmt.setInt(4, libro.getCategoria().getId());
            stmt.setString(5, libro.getIsbn());
            stmt.setInt(6, libro.getStock());
            stmt.setString(7, libro.getRutaImagen());
            stmt.setString(8, libro.getDescripcion());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizar(Libro libro) {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, anio_publicacion = ?, id_categoria = ?, isbn = ?, stock = ?, rutaImagen = ?, descripcion = ? WHERE id = ?";
        try (Connection con = Conexion.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getAnio_publicacion()); // ← ya no setDate
            stmt.setInt(4, libro.getCategoria().getId());
            stmt.setString(5, libro.getIsbn());
            stmt.setInt(6, libro.getStock());
            stmt.setString(7, libro.getRutaImagen());
            stmt.setString(8, libro.getDescripcion());
            stmt.setInt(9, libro.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM libros WHERE id = ?";
        try (Connection con = Conexion.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int contarLibros() {
        int total = 0;
        try (Connection conn = Conexion.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM libros"); 
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("❌ Error contando libros: " + e.getMessage());
        }
        return total;
    }
    
    public int contarStockBajo(int limite) {
    int cantidad = 0;
    String sql = "SELECT COUNT(*) FROM libros WHERE stock <= ?";

    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, limite);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            cantidad = rs.getInt(1);
        }

    } catch (SQLException e) {
        System.err.println("❌ Error al contar libros con stock bajo: " + e.getMessage());
    }
    return cantidad;
}
    
    public Libro buscarPorISBN(String isbn) {
    String sql = "SELECT l.*, c.nombre AS nombre_categoria FROM libros l "
               + "LEFT JOIN categorias c ON l.id_categoria = c.id "
               + "WHERE l.isbn = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setString(1, isbn);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Categoria c = new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));
            return new Libro(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getInt("anio_publicacion"),
                rs.getString("isbn"),
                rs.getInt("stock"),
                rs.getString("rutaImagen"),
                c,
                rs.getString("descripcion")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public List<Libro> buscarPorTitulo(String titulo) {
    List<Libro> libros = new ArrayList<>();
    String sql = "SELECT l.*, c.nombre AS nombre_categoria FROM libros l "
               + "LEFT JOIN categorias c ON l.id_categoria = c.id "
               + "WHERE LOWER(l.titulo) LIKE ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setString(1, "%" + titulo.toLowerCase() + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Categoria c = new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));
            Libro l = new Libro(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getInt("anio_publicacion"),
                rs.getString("isbn"),
                rs.getInt("stock"),
                rs.getString("rutaImagen"),
                c,
                rs.getString("descripcion")
            );
            libros.add(l);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return libros;
}
    
    public List<Libro> buscarPorCategoria(String nombreCategoria) {
    List<Libro> libros = new ArrayList<>();
    String sql = "SELECT l.*, c.nombre AS nombre_categoria FROM libros l "
               + "JOIN categorias c ON l.id_categoria = c.id "
               + "WHERE LOWER(c.nombre) = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setString(1, nombreCategoria.toLowerCase());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Categoria c = new Categoria(rs.getInt("id_categoria"), rs.getString("nombre_categoria"));
            Libro l = new Libro(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getInt("anio_publicacion"),
                rs.getString("isbn"),
                rs.getInt("stock"),
                rs.getString("rutaImagen"),
                c,
                rs.getString("descripcion")
            );
            libros.add(l);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return libros;
}
}


