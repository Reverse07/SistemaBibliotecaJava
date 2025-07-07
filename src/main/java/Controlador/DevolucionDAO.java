package Controlador;

import Modelo.Devolucion;
import Modelo.Prestamo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DevolucionDAO {

    public boolean registrarDevolucion(Devolucion devolucion) {
        String sql = "INSERT INTO devoluciones (id_prestamo, fecha_devolucion, observaciones) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, devolucion.getPrestamo().getId());
            stmt.setDate(2, new java.sql.Date(devolucion.getFechaDevolucion().getTime()));
            stmt.setString(3, devolucion.getObservaciones());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Devolucion> obtenerTodas() {
        List<Devolucion> devoluciones = new ArrayList<>();
        String sql = "SELECT d.*, p.fecha_prestamo FROM devoluciones d " +
                     "JOIN prestamos p ON d.id_prestamo = p.id";

        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setId(rs.getInt("id_prestamo"));
                prestamo.setFechaPrestamo(rs.getDate("fecha_prestamo"));

                Devolucion d = new Devolucion(
                    rs.getInt("id"),
                    prestamo,
                    rs.getDate("fecha_devolucion"),
                    rs.getString("observaciones")
                );

                devoluciones.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devoluciones;
    }
    
     public boolean eliminar(int id) {
        String sql = "DELETE FROM devoluciones WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

