package com.example.Controller;

import com.example.modelo.Usuario;
import com.example.modelo.UsuariosRepositorio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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
        correo = correoField.getText().trim();
        if(!correo.endsWith("@javeriana.edu.co")) {
            mensajeLabel.setText("El correo debe ser institucional.");
            return;
        }

        // Si es Pasajero, guarda el usuario y redirige a la pantalla de búsqueda
        if ("Pasajero".equals(rol)) {
            try {
                Usuario u = new Usuario(nombre, correo, pass, rol, carrera);
                repository.guardarUsuario(u);
                mensajeLabel.setStyle("-fx-text-fill: green;");
                mensajeLabel.setText("Registro exitoso. Verifica tu correo institucional.");

                // --- REDIRECCIÓN DEL PASAJERO A LA VISTA DE BÚSQUEDA ---
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/RutasPasajero.fxml"));
                Parent root = loader.load();

                RutasPasajeroController controller = loader.getController();
                controller.setCorreoUsuario(correo); // Pasa el correo del pasajero

                Stage stage = (Stage) nombreField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Buscar Viajes - Pasajero");
                stage.show();
                // -----------------------------------------------------------

            } catch (IOException e) {
                e.printStackTrace();
                mensajeLabel.setText("Error al cargar la pantalla de rutas.");
            } catch (Exception e) {
                mensajeLabel.setText("Error al registrar usuario.");
            }
        }

        // Si es conductor, vas a la pantalla de vehículo
        if ("Conductor".equals(rol)) {
            abrirPantallaConductor(nombre, correo, pass, carrera);
        }
    }

    private void abrirPantallaConductor(String nombre, String correo, String pass, String carrera) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/registroConductor.fxml"));
            Parent root = loader.load();

            RegistroConductorController controller = loader.getController();
            controller.recibirDatosUsuario(nombre, correo, pass, carrera);

            Stage stage = (Stage) nombreField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Datos del vehículo");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            mensajeLabel.setText("No se pudo abrir la pantalla de conductor.");
        }
    }
}