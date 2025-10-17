/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
public class interfaz {
    private void agregaActionPerformed(java.awt.event.ActionEvent evt) {
    // Validación de campos los vacíos
    if (nombres.getText().isEmpty() || apellidos.getText().isEmpty() || telefono.getText().isEmpty() || direccion.getText().isEmpty() || email.getText().isEmpty()) {
        salida.setText("Todos los campos deben estar completos"); //código meza
        return;
    }

    // Conexión y agregado
    controladores.controlador_general controlador = new controladores.controlador_general();
    Connection con = controlador.conectar(); //código meza

    if (con != null) {
        String query = "datos"; //código meza
        try {
            PreparedStatement preparar = con.prepareStatement(query); //código meza
            preparar.setString(1, nombres.getText());
            preparar.setString(2, apellidos.getText());
            preparar.setString(3, telefono.getText());
            preparar.setString(4, direccion.getText());
            preparar.setString(5, email.getText());
            preparar.executeUpdate(); //código meza
            salida.setText("Contacto agregado correctamente"); //código meza
        } catch (SQLException ex) {
            salida.setText("Error al agregar contacto"); //código meza
        }
    } else {
        salida.setText("No se pudo conectar a la base de datos"); //código meza
    }
}
}
