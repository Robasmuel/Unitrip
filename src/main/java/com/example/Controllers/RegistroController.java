package com.example.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistroController {

    @FXML private TextField campoNombre;
    @FXML private TextField campoCorreo;
    @FXML private PasswordField campoPassword;
    @FXML private PasswordField campoConfirmacion;
    @FXML private ComboBox<String> campoRol;
    @FXML private TextField campoCarrera;
    @FXML private Label mensajeError;

    @FXML
    public void initialize() {
        campoRol.getItems().addAll("Conductor", "Pasajero");
    }

    @FXML
    private void registrarUsuario() {

        String nombre = campoNombre.getText();
        String correo = campoCorreo.getText();
        String pass = campoPassword.getText();
        String confirm = campoConfirmacion.getText();
        String rol = campoRol.getValue();
        String carrera = campoCarrera.getText();

        // Validaciones
        if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty() || confirm.isEmpty() ||
                rol == null || carrera.isEmpty()) {
            mensajeError.setText("Todos los campos son obligatorios.");
            return;
        }

        if (!correo.endsWith("@javeriana.edu.co")) {
            mensajeError.setText("Debe usar su correo institucional.");
            return;
        }

        if (!pass.equals(confirm)) {
            mensajeError.setText("Las contraseñas no coinciden.");
            return;
        }

        mensajeError.setText("Registro exitoso!");
    }
}