
package Controlador;

import Modelo.RolesBiblioteca;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {
    
   public boolean insertar(Usuario usuario) {
    String sql = "INSERT INTO usuarios (nombre, apellido, dni, correo, telefono, id_rol) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, usuario.getNombre());
        stmt.setString(2, usuario.getApellido());
        stmt.setInt(3, usuario.getDni());
        stmt.setString(4, usuario.getCorreo());
        stmt.setInt(5, usuario.getTelefono());
        stmt.setInt(6, usuario.getRol().getId()); // ⚠️ CUIDADO: Si esto es 0 y tu tabla no acepta eso, fallará

        int filas = stmt.executeUpdate();
        return filas > 0;
    } catch (SQLException e) {
        System.err.println("❌ Error al insertar usuario: " + e.getMessage());
        return false;
    }
}


   public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();
      String sql = "SELECT u.*, r.nombre AS nombre_rol FROM usuarios u " +
             "JOIN roles r ON u.id_rol = r.id";


        try (Connection con = Conexion.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RolesBiblioteca rol = new RolesBiblioteca(rs.getInt("id_rol"), rs.getString("nombre_rol"));
                Usuario u = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("dni"),
                    rs.getString("correo"),
                    rs.getInt("telefono"),
                    rol
                );
                usuarios.add(u);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario obtenerPorId(int id) {
        String sql = "SELECT u.*, r.nombre AS nombre_rol FROM usuarios u " +
                     "JOIN roles r ON u.id_rol = r.id WHERE u.id = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                RolesBiblioteca rol = new RolesBiblioteca(rs.getInt("id_rol"), rs.getString("nombre_rol"));
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("dni"),
                    rs.getString("correo"),
                    rs.getInt("telefono"),
                    rol
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, dni = ?, correo = ?, telefono = ?, id_rol = ? WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setInt(3, usuario.getDni());
            stmt.setString(4, usuario.getCorreo());
            stmt.setInt(5, usuario.getTelefono());
            stmt.setInt(6, usuario.getRol().getId());
            stmt.setInt(7, usuario.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public static int contarUsuarios() {
        int total = 0;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM usuarios");
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("❌ Error contando usuarios: " + e.getMessage());
        }
        return total;
    }
}
