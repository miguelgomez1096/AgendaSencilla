/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class interfaz {

    DefaultTableModel modelo;

    private void agregaActionPerformed(java.awt.event.ActionEvent evt) {
        if (nombres.getText().isEmpty() || apellidos.getText().isEmpty() || telefono.getText().isEmpty() || direccion.getText().isEmpty() || email.getText().isEmpty()) {
            salida.setText("Todos los campos deben estar completos");
            return;
        }

        controladores.controlador_general controlador = new controladores.controlador_general();
        Connection con = controlador.conectar();

        if (con != null) {
            String query = "INSERT INTO datos(id, nombres, apellidos, telefono, direccion, email) VALUES (null, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparar = con.prepareStatement(query);
                preparar.setString(1, nombres.getText());
                preparar.setString(2, apellidos.getText());
                preparar.setString(3, telefono.getText());
                preparar.setString(4, direccion.getText());
                preparar.setString(5, email.getText());
                preparar.executeUpdate();
                salida.setText("Contacto agregado correctamente");
            } catch (SQLException ex) {
                salida.setText("Error al agregar contacto");
            }
        } else {
            salida.setText("No se pudo conectar a la base de datos");
        }
    }

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {
        controladores.controlador_general controlador = new controladores.controlador_general();
        String idTexto = idEliminar.getText();
        if (idTexto.isEmpty()) {
            salida.setText("Debes ingresar un ID para eliminar");
            return;
        }
        try {
            int id = Integer.parseInt(idTexto);
            boolean eliminado = controlador.eliminarPorId(id);
            if (eliminado) {
                salida.setText("Contacto eliminado correctamente");
            } else {
                salida.setText("No se encontró el contacto con ese ID");
            }
        } catch (NumberFormatException e) {
            salida.setText("ID inválido");
        }
    }

    private void listarCiudadActionPerformed(java.awt.event.ActionEvent evt) {
        modelo.setRowCount(0);
        controladores.controlador_general controlador = new controladores.controlador_general();
        String ciudad = campoCiudad.getText();
        controlador.listarPorCiudad(ciudad, modelo);
    }

    private void listarRangoIdActionPerformed(java.awt.event.ActionEvent evt) {
        modelo.setRowCount(0);
        controladores.controlador_general controlador = new controladores.controlador_general();
        int min = Integer.parseInt(campoMinId.getText());
        int max = Integer.parseInt(campoMaxId.getText());
        controlador.listarPorRangoId(min, max, modelo);
    }

    private void buscarNombreActionPerformed(java.awt.event.ActionEvent evt) {
        modelo.setRowCount(0);
        controladores.controlador_general controlador = new controladores.controlador_general();
        String nombreBuscado = campoBuscarNombre.getText();
        controlador.buscarPorNombre(nombreBuscado, modelo);
    }
}