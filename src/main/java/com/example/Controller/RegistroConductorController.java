package com.example.Controller;

import com.example.LogicaNegocio.ServiceConductor;

import java.io.IOException;

import com.example.App;
import com.example.modelo.UsuariosRepositorio;
import com.example.modelo.VehiculosRepositorio;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegistroConductorController {

    private String nombre;
    private String correo;
    private String pass;
    private String carrera;

    @FXML private TextField placaField;
    @FXML private TextField modeloField;
    @FXML private TextField colorField;
    @FXML private TextField capacidadField;
    @FXML private Label mensajeLabel;

    private VehiculosRepositorio vehiculosRepo = new VehiculosRepositorio();

    private UsuariosRepositorio usuariosRepo = new UsuariosRepositorio();

    public void recibirDatosUsuario(String nombre, String correo, String pass, String carrera) {
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.carrera = carrera;
    }

    @FXML
    private void guardarConductor() throws IOException {

        String placa = placaField.getText().trim();
        String modelo = modeloField.getText().trim();
        String color = colorField.getText().trim();
        String capacidad = capacidadField.getText().trim();

        try {
            ServiceConductor service = new ServiceConductor();
            service.guardarConductor(
                    placa, modelo, color, capacidad,
                    nombre, correo, pass, carrera
            );

        } catch (Exception e) {
            mensajeLabel.setStyle("-fx-text-fill: red;");
            mensajeLabel.setText(e.getMessage());
            return;
        }

        // Cambiar pantalla
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/com/example/ruta.fxml"));
        Parent root = loader.load();

        RutaController rutaController = loader.getController();
        rutaController.setCorreoConductor(correo);

        Stage stage = (Stage) mensajeLabel.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Publicar ruta y buscar viajes");
        stage.show();
    }

}