/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

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
            String query = "DELETE FROM datos WHERE id = ?";
            PreparedStatement preparar = con.prepareStatement(query); 
            preparar.setInt(1, id); 
            int resultado = preparar.executeUpdate(); 
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public void listarPorCiudad(String ciudad, DefaultTableModel modelo) {
        String usuario = "root"; 
        String contrasena = "root";
        String url = "jdbc:mysql://localhost:3306/agenda"; 
        try (Connection con = DriverManager.getConnection(url, usuario, contrasena)) {
            String query = "SELECT * FROM datos WHERE direccion LIKE ?";
            PreparedStatement preparar = con.prepareStatement(query); 
            preparar.setString(1, "%" + ciudad + "%"); 
            ResultSet resultado = preparar.executeQuery(); 
            while (resultado.next()) {
                modelo.addRow(new Object[]{
                    resultado.getInt("id"),
                    resultado.getString("nombres"),
                    resultado.getString("apellidos"),
                    resultado.getString("telefono"),
                    resultado.getString("direccion"),
                    resultado.getString("email")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error al listar por ciudad: " + e.getMessage());
        }
    }

    public void listarPorRangoId(int min, int max, DefaultTableModel modelo) {
        String usuario = "root"; 
        String contrasena = "root";
        String url = "jdbc:mysql://localhost:3306/agenda"; 
        try (Connection con = DriverManager.getConnection(url, usuario, contrasena)) {
            String query = "SELECT * FROM datos WHERE id BETWEEN ? AND ?";
            PreparedStatement preparar = con.prepareStatement(query); 
            preparar.setInt(1, min); 
            preparar.setInt(2, max); 
            ResultSet resultado = preparar.executeQuery(); 
            while (resultado.next()) {
                modelo.addRow(new Object[]{
                    resultado.getInt("id"),
                    resultado.getString("nombres"),
                    resultado.getString("apellidos"),
                    resultado.getString("telefono"),
                    resultado.getString("direccion"),
                    resultado.getString("email")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error al listar por rango de ID: " + e.getMessage());
        }
    }

    public void buscarPorNombre(String nombreBuscado, DefaultTableModel modelo) {
        String usuario = "root"; 
        String contrasena = "root";
        String url = "jdbc:mysql://localhost:3306/agenda"; 
        try (Connection con = DriverManager.getConnection(url, usuario, contrasena)) {
            String query = "SELECT * FROM datos WHERE nombres LIKE ?";
            PreparedStatement preparar = con.prepareStatement(query); 
            preparar.setString(1, nombreBuscado + "%"); 
            ResultSet resultado = preparar.executeQuery(); 
            while (resultado.next()) {
                modelo.addRow(new Object[]{
                    resultado.getInt("id"),
                    resultado.getString("nombres"),
                    resultado.getString("apellidos"),
                    resultado.getString("telefono"),
                    resultado.getString("direccion"),
                    resultado.getString("email")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar por nombre: " + e.getMessage());
        }
    }
}