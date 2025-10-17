/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controladores;
//__codigo meza _//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
