package Controlador;

import Modelo.Libro;
import Modelo.Prestamo;
import Modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public boolean insertar(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos (id_usuario, fecha_prestamo, fecha_devolucion, estado) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, prestamo.getUsuario().getId());
            stmt.setDate(2, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            stmt.setDate(3, new java.sql.Date(prestamo.getFechaDevolucion().getTime()));
            stmt.setString(4, prestamo.getEstado());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    prestamo.setId(rs.getInt(1));
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Prestamo> obtenerTodos() {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT p.*, u.nombre, u.apellido FROM prestamos p "
                + "JOIN usuarios u ON p.id_usuario = u.id";

        try (Connection con = Conexion.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));

                Prestamo p = new Prestamo(
                        rs.getInt("id"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion"),
                        rs.getString("estado"),
                        u
                );

                prestamos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prestamos;
    }

    public Prestamo obtenerPorId(int id) {
        String sql = "SELECT p.*, u.nombre, u.apellido FROM prestamos p "
                + "JOIN usuarios u ON p.id_usuario = u.id WHERE p.id = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));

                return new Prestamo(
                        rs.getInt("id"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion"),
                        rs.getString("estado"),
                        u
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM prestamos WHERE id = ?";
        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizar(Prestamo prestamo) {
        String sql = "UPDATE prestamos SET id_usuario = ?, fecha_prestamo = ?, fecha_devolucion = ?, estado = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, prestamo.getUsuario().getId());
            stmt.setDate(2, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            stmt.setDate(3, new java.sql.Date(prestamo.getFechaDevolucion().getTime()));
            stmt.setString(4, prestamo.getEstado());
            stmt.setInt(5, prestamo.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static int contarPrestamosActivos() {
        int total = 0;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM prestamos WHERE estado = 'Activo'"); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            System.err.println("❌ Error contando préstamos activos: " + e.getMessage());
        }

        return total;
    }

    public List<Prestamo> obtenerPrestamosActivos() {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT p.*, u.nombre, u.apellido FROM prestamos p "
                + "JOIN usuarios u ON p.id_usuario = u.id "
                + "WHERE p.estado = 'Activo'";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));

                Prestamo p = new Prestamo(
                        rs.getInt("id"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion"),
                        rs.getString("estado"),
                        u
                );

                prestamos.add(p);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener préstamos activos: " + e.getMessage());
        }

        return prestamos;
    }
    
}
