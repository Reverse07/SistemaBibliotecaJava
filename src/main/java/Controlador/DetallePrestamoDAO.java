package Controlador;

import Modelo.DetallePrestamo;
import Modelo.Libro;
import Modelo.Prestamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePrestamoDAO {

    public boolean insertar(DetallePrestamo detalle) {
        String sql = "INSERT INTO detalles_prestamo (id_prestamo, id_libro, cantidad) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, detalle.getPrestamo().getId());
            stmt.setInt(2, detalle.getLibro().getId());
            stmt.setInt(3, detalle.getCantidad());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<DetallePrestamo> obtenerPorIdPrestamo(int idPrestamo) {
        List<DetallePrestamo> detalles = new ArrayList<>();
        String sql = "SELECT dp.*, l.titulo FROM detalles_prestamo dp "
                + "JOIN libros l ON dp.id_libro = l.id WHERE dp.id_prestamo = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idPrestamo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));

                Prestamo prestamo = new Prestamo();
                prestamo.setId(idPrestamo);

                DetallePrestamo dp = new DetallePrestamo(
                        rs.getInt("id"),
                        prestamo,
                        libro,
                        rs.getInt("cantidad")
                );

                detalles.add(dp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    public List<Libro> obtenerLibrosPorPrestamo(int idPrestamo) {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT l.id, l.titulo FROM detalles_prestamo dp "
                + "JOIN libros l ON dp.id_libro = l.id "
                + "WHERE dp.id_prestamo = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idPrestamo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libros.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public boolean eliminarPorPrestamoId(int idPrestamo) {
        String sql = "DELETE FROM detalles_prestamo WHERE id_prestamo = ?";
        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idPrestamo);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
