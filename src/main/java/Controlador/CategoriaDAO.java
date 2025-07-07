
package Controlador;

import java.sql.Connection;
import Modelo.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CategoriaDAO {


    public List<Categoria> obtenerTodas() {
        
        List<Categoria> categorias = new ArrayList<>();
        String sql = "select * from categorias";
        
        try(Connection con = Conexion.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql))       
        {
            
            while (rs.next())  {
                
                Categoria c = new Categoria (rs.getInt("id"), rs.getString("nombre"));
                categorias.add(c);                               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        return categorias;        
    }
    
    public Categoria obtenerPorId(int id) { 
        
        String sql = "select * from categorias where id = ?";
        
        try (Connection con = Conexion.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql))  {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                
                if(rs.next())   {
                    return new Categoria(rs.getInt("id"),rs.getString("nombre"));                  
                }
      }  catch (SQLException e)  {
    e.printStackTrace();   
}
return null;
}
    
    public boolean guardar (Categoria categoria) {
        
        String sql = "Insert into (nombre) values (?)";
        
        try(Connection con = Conexion.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
              stmt.setString(1, categoria.getNombre());
              stmt.setInt(2, categoria.getId());
              return stmt.executeUpdate()>0;
                           
          } catch (SQLException e) {
        e.printStackTrace();        
    }
    
        return false;
  }    
    
    public boolean actualizar (Categoria categoria) {
        
        String sql = "update categorias set nombre = ? where id = ?";
        
        try(Connection con = Conexion.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, categoria.getNombre());
            stmt.setInt(2, categoria.getId());
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {        
        e.printStackTrace();
    }
        
        return false;
}
    
    public boolean eliminar (int id) {
        
        String sql = "delete from categorias where id = ?";
        
        try (Connection con = Conexion.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
               stmt.setInt(1,id);
               return stmt.executeUpdate()>0;
    } catch (SQLException e) {
  
        e.printStackTrace();
        }
        return false;
        
        }
    
      public static int contarCategorias() {
        int total = 0;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM categoria");
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("❌ Error contando categorías: " + e.getMessage());
        }
        return total;
    }
}
