package com.example.Controller;

import com.example.modelo.Vehiculo;

import java.io.IOException;

import com.example.modelo.Usuario;
import com.example.modelo.UsuariosRepositorio;
import com.example.modelo.VehiculosRepositorio;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


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
         int cap;

        if (placa.isEmpty() || modelo.isEmpty() || color.isEmpty() || capacidad.isEmpty()) {
            mensajeLabel.setStyle("-fx-text-fill: red;");
            mensajeLabel.setText("Completa todos los campos del vehículo.");
            return;

        }
         // 2. Validar formato de matrícula (ejemplo: ABC123)
        if (!placa.toUpperCase().matches("[A-Z]{3}[0-9]{3}")) {
            mensajeLabel.setStyle("-fx-text-fill: red;");
            mensajeLabel.setText("La matrícula debe tener formato ABC123.");
            return;
        }
    try {
        cap = Integer.parseInt(capacidad);
    } catch (NumberFormatException e) {
        mensajeLabel.setStyle("-fx-text-fill: red;");   
        mensajeLabel.setText("La capacidad debe ser un número.");
        return;
    }

    Vehiculo veh = new Vehiculo(placa, modelo, color, cap, correo);
    vehiculosRepo.guardarVehiculo(veh);

     Usuario u = new Usuario(nombre, correo, pass, "Conductor", carrera);
    usuariosRepo.guardarUsuario(u);

    mensajeLabel.setStyle("-fx-text-fill: green;");
    mensajeLabel.setText(" vehículo registrado con exito.");

      
    }
}
