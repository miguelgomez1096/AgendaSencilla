/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
//__código meza__//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class controlador_general {

    public Connection conectar() {
        String usuario = "root";
        String contrasena = "root";
        String url = "jdbc:mysql://localhost:3306/agenda";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println(" Conexión correcta");
            return conn;
        } catch (SQLException e) {
            System.out.println("Conexión incorrecta: " + e.getMessage());
            return null;
        }
    }

    public boolean eliminarPorId(int id) {
        String usuario = "root"; 
        String contrasena = "root";
        String url = "jdbc:mysql://localhost:3306/agenda"; 
        try (Connection con = DriverManager.getConnection(url, usuario, contrasena)) {
            String query = "borrar datos con id"; 
            PreparedStatement preparar = con.prepareStatement(query); 
            preparar.setInt(1, id); 
            int resultado = preparar.executeUpdate(); 
            return resultado > 0; //__código meza__//
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
}