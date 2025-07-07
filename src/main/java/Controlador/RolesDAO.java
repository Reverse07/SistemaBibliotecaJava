
package Controlador;

import Modelo.RolesBiblioteca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RolesDAO {
    
    
    public List<RolesBiblioteca> obtenerTodos() {
        List<RolesBiblioteca> roles = new ArrayList<>();
        String sql = "SELECT * FROM roles_biblioteca";

        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RolesBiblioteca rol = new RolesBiblioteca(
                    rs.getInt("id"),
                    rs.getString("nombre")
                );
                roles.add(rol);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public RolesBiblioteca obtenerPorId(int id) {
        String sql = "SELECT * FROM roles_biblioteca WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new RolesBiblioteca(
                    rs.getInt("id"),
                    rs.getString("nombre")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertar(RolesBiblioteca rol) {
        String sql = "INSERT INTO roles_biblioteca (nombre) VALUES (?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, rol.getNombre());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizar(RolesBiblioteca rol) {
        String sql = "UPDATE roles_biblioteca SET nombre = ? WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, rol.getNombre());
            stmt.setInt(2, rol.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM roles_biblioteca WHERE id = ?";
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

