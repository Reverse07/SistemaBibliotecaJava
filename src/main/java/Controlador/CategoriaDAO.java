package Controlador;

import java.sql.Connection;
import Modelo.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriaDAO {

    public Map<String, Integer> obtenerTodas() {
        Map<String, Integer> mapa = new HashMap<>();
        String sql = "SELECT c.nombre, COUNT(l.id) AS cantidad "
                + "FROM categorias c "
                + "LEFT JOIN libros l ON l.id_categoria = c.id "
                + "GROUP BY c.nombre";

        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                mapa.put(rs.getString("nombre"), rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapa;
    }

    public Categoria obtenerPorId(int id) {
        String sql = "SELECT * FROM categorias WHERE id = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Categoria(rs.getInt("id"), rs.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean guardar(Categoria categoria) {
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizar(Categoria categoria) {
        String sql = "UPDATE categorias SET nombre = ? WHERE id = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setInt(2, categoria.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM categorias WHERE id = ?";

        try (Connection con = Conexion.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static int contarCategorias() {
        int total = 0;

        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM categorias"); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (Exception e) {
            System.err.println("❌ Error contando categorías: " + e.getMessage());
        }

        return total;
    }
    
    public static List<Categoria> obtenerTodasCategorias() {
    List<Categoria> lista = new ArrayList<>();
    String sql = "SELECT id, nombre FROM categorias";

    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Categoria cat = new Categoria(rs.getInt("id"), rs.getString("nombre"));
            lista.add(cat);
        }

    } catch (SQLException e) {
        System.err.println("❌ Error al obtener categorías: " + e.getMessage());
    }

    return lista;
}

}
