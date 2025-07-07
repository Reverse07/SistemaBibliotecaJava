
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "Nomeacuerdo2006@";
    private static Connection connection;

    // Constructor privado para evitar instancias
    private Conexion() {}

    // Obtener conexión
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión exitosa a la base de datos.");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ Error: No se encontró el driver JDBC.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("❌ Error de conexión a la base de datos.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Cerrar conexión
    public static void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("🔒 Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}

