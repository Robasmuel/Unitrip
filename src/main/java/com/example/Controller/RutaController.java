package com.example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RutaController {

    @FXML private TextField origenField;
    @FXML private TextArea paradasField;
    @FXML private TextField destinoField;
    @FXML private TextField horaField;
    @FXML private TextField cuposField;
    @FXML private Label mensajeLabel;

    // Si quieres relacionarlo con el conductor:
    private String correoConductor;

    public void setCorreoConductor(String correo) {
        this.correoConductor = correo;
    }

    @FXML
    private void guardarRuta() {
        String origen   = origenField.getText().trim();
        String paradas  = paradasField.getText().trim();
        String destino  = destinoField.getText().trim();
        String hora     = horaField.getText().trim();
        String cuposStr = cuposField.getText().trim();

        // 1. Campos obligatorios
        if (origen.isEmpty() || destino.isEmpty() || hora.isEmpty() || cuposStr.isEmpty()) {
            mensajeLabel.setStyle("-fx-text-fill: red;");
            mensajeLabel.setText("Completa origen, destino, hora y cupos.");
            return;
        }

        // 2. Origen y destino distintos
        if (origen.equalsIgnoreCase(destino)) {
            mensajeLabel.setStyle("-fx-text-fill: red;");
            mensajeLabel.setText("Origen y destino deben ser diferentes.");
            return;
        }

        // 3. Cupos numérico y > 0
        int cupos;
        try {
            cupos = Integer.parseInt(cuposStr);
            if (cupos <= 0) {
                mensajeLabel.setStyle("-fx-text-fill: red;");
                mensajeLabel.setText("Los cupos deben ser mayores que 0.");
                return;
            }
        } catch (NumberFormatException e) {
            mensajeLabel.setStyle("-fx-text-fill: red;");
            mensajeLabel.setText("Los cupos deben ser un número.");
            return;
        }

        // 4. Validación simple de hora (ej. 7:30, 7:30 AM)
        if (!hora.matches("[0-9]{1,2}:[0-9]{2}(\\s?(AM|PM|am|pm))?")) {
            mensajeLabel.setStyle("-fx-text-fill: red;");
            mensajeLabel.setText("La hora debe ser válida (ej. 7:30 AM).");
            return;
        }

        // 5. Aquí guardarías la ruta en un archivo rutas.csv o en un repositorio
        // String linea = origen + ";" + paradas + ";" + destino + ";" + hora + ";" + cupos + ";" + correoConductor;
        // rutasRepo.guardarRuta(...);

        mensajeLabel.setStyle("-fx-text-fill: green;");
        mensajeLabel.setText("Ruta publicada con éxito.");
    }
}
