package com.example.Controller;

import com.example.modelo.Usuario;
import com.example.modelo.UsuariosRepositorio;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistroController {

    @FXML private TextField nombreField;
    @FXML private TextField correoField;
    @FXML private PasswordField contraseñaField;
    @FXML private PasswordField confirmacionField;
    @FXML private ComboBox<String> rolBox;
    @FXML private TextField carreraField;
    @FXML private Label mensajeLabel;

    private UsuariosRepositorio repository = new UsuariosRepositorio();

    @FXML
    public void initialize() {
        rolBox.getItems().addAll("Conductor", "Pasajero");
    }

    @FXML
    private void registrarUsuario() {
        String nombre = nombreField.getText();
        String correo = correoField.getText();
        String pass = contraseñaField.getText();
        String confirm = confirmacionField.getText();
        String rol = rolBox.getValue();
        String carrera = carreraField.getText();

        // Validaciones:
        if(nombre.isEmpty() || correo.isEmpty() || pass.isEmpty() || confirm.isEmpty() || rol == null || carrera.isEmpty()) {
            mensajeLabel.setText("Completa todos los campos.");
            return;
        }

        if(!correo.endsWith("@correo.udistrital.edu.co")) {
            mensajeLabel.setText("El correo debe ser institucional.");
            return;
        }

        if(!pass.equals(confirm)) {
            mensajeLabel.setText("Las contraseñas no coinciden.");
            return;
        }

        try {
            Usuario u = new Usuario(nombre, correo, pass, rol, carrera);
            repository.guardarUsuario(u);
            mensajeLabel.setStyle("-fx-text-fill: green;");
            mensajeLabel.setText("Registro exitoso. Verifica tu correo institucional.");
        } catch (Exception e) {
            mensajeLabel.setText("Error al registrar usuario.");
        }
    }
}
