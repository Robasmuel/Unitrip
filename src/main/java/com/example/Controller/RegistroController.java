package com.example.Controller;

import com.example.LogicaNegocio.ServiceRegistro;
import com.example.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegistroController {

    @FXML private TextField nombreField;
    @FXML private TextField correoField;
    @FXML private PasswordField contraseñaField;
    @FXML private PasswordField confirmacionField;
    @FXML private ComboBox<String> rolBox;
    @FXML private TextField carreraField;
    @FXML private Label mensajeLabel;

    private ServiceRegistro service = new ServiceRegistro();

    @FXML
    public void initialize() {
        rolBox.getItems().addAll("Conductor", "Pasajero");
    }

    @FXML
    private void registrarUsuario() {

        try {
            Usuario usuario = service.registrarUsuario(
                    nombreField.getText(),
                    correoField.getText(),
                    contraseñaField.getText(),
                    confirmacionField.getText(),
                    rolBox.getValue(),
                    carreraField.getText()
            );

            mensajeLabel.setStyle("-fx-text-fill: green;");
            mensajeLabel.setText("Registro exitoso. Verifica tu correo.");

            if (usuario.getRol().equals("Pasajero")) {
                abrirPantallaPasajero(usuario.getCorreo());
            } else if (usuario.getRol().equals("Conductor")) {
                abrirPantallaConductor(usuario);
            }

        } catch (Exception e) {
            mensajeLabel.setStyle("-fx-text-fill: red;");
            mensajeLabel.setText(e.getMessage());
        }
    }


    private void abrirPantallaPasajero(String correo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/RutasPasajero.fxml"));
            Parent root = loader.load();

            RutasPasajeroController controller = loader.getController();

            Stage stage = (Stage) nombreField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Buscar Viajes - Pasajero");
            stage.show();

        } catch (Exception e) {
            mensajeLabel.setStyle("-fx-text-fill: red;");
            mensajeLabel.setText("No se pudo abrir la pantalla de pasajero.");
        }
    }

    private void abrirPantallaConductor(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/registroConductor.fxml"));
            Parent root = loader.load();

            RegistroConductorController controller = loader.getController();
            controller.recibirDatosUsuario(
                    usuario.getNombre(),
                    usuario.getCorreo(),
                    usuario.getContraseña(),
                    usuario.getCarrera()
            );

            Stage stage = (Stage) nombreField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Datos del vehículo");
            stage.show();

        } catch (Exception e) {
            mensajeLabel.setText("No se pudo abrir la pantalla de conductor.");
        }
    }
}
