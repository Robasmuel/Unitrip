package com.example.Controller;

import com.example.modelo.Ruta;
import com.example.modelo.RutasRepositorio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.List;

public class RutaController {

    // --- FXML de Publicación (Tarea 129) ---
    @FXML private TextField origenField;
    @FXML private TextArea paradasField;
    @FXML private TextField destinoField;
    @FXML private TextField horaField;
    @FXML private TextField cuposField;
    @FXML private Label mensajeLabel;

    // --- FXML de Búsqueda y Tabla (Tarea 133) ---
    @FXML private TextField campoBusquedaDestino;
    @FXML private TableView<Ruta> tablaRutas;
    @FXML private TableColumn<Ruta, String> columnaDestino;
    @FXML private TableColumn<Ruta, String> columnaOrigen;
    @FXML private TableColumn<Ruta, String> columnaHora;
    @FXML private TableColumn<Ruta, String> columnaCupos;
    @FXML private TableColumn<Ruta, String> columnaConductor;

    // --- Repositorio y Datos ---
    private RutasRepositorio rutasRepo = new RutasRepositorio();
    private ObservableList<Ruta> rutasData = FXCollections.observableArrayList();
    private String correoConductor; // Viene de la pantalla anterior (RegistroConductorController)

    public void setCorreoConductor(String correo) {
        this.correoConductor = correo;
    }

    // Inicializa el controlador y la tabla de rutas
    @FXML
    public void initialize() {
        configurarTabla();
        cargarRutas(null); // Cargar todas las rutas al inicio
        configurarFiltroBusqueda();
    }

    // Configura cómo los datos de la clase Ruta se muestran en las columnas
    private void configurarTabla() {
        columnaDestino.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestino()));
        columnaOrigen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrigen()));
        columnaHora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHora()));
        columnaConductor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreoConductor()));

        // Para tipos numéricos, convertimos a String para la TableColumn
        columnaCupos.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCupos())));

        tablaRutas.setItems(rutasData);
    }

    // Configura el listener que dispara el filtrado al escribir
    private void configurarFiltroBusqueda() {
        campoBusquedaDestino.textProperty().addListener((observable, oldValue, newValue) -> {
            cargarRutas(newValue); // Recargar la tabla con el nuevo filtro
        });
    }

    // Método de carga/filtrado que llama al Repositorio
    private void cargarRutas(String filtro) {
        List<Ruta> rutasFiltradas;

        // Si el filtro es nulo o vacío, obtiene todas; de lo contrario, aplica el filtro.
        if (filtro == null || filtro.trim().isEmpty()) {
            rutasFiltradas = rutasRepo.obtenerTodasLasRutas();
        } else {
            rutasFiltradas = rutasRepo.obtenerRutasPorDestino(filtro);
        }

        rutasData.clear();
        rutasData.addAll(rutasFiltradas);
    }


    // Método de acción para el botón "Publicar ruta" (Tarea 129)
    @FXML
    private void guardarRuta() {
        String origen   = origenField.getText().trim();
        String paradas  = paradasField.getText().trim();
        String destino  = destinoField.getText().trim();
        String hora     = horaField.getText().trim();
        String cuposStr = cuposField.getText().trim();

        // --- 1. Validaciones ---
        if (origen.isEmpty() || destino.isEmpty() || hora.isEmpty() || cuposStr.isEmpty()) {
            mensajeLabel.setStyle("-fx-text-fill: red;");
            mensajeLabel.setText("Completa origen, destino, hora y cupos.");
            return;
        }
        if (origen.equalsIgnoreCase(destino)) {
            mensajeLabel.setStyle("-fx-text-fill: red;");
            mensajeLabel.setText("Origen y destino deben ser diferentes.");
            return;
        }

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

        if (!hora.matches("[0-9]{1,2}:[0-9]{2}(\\s?(AM|PM|am|pm))?")) {
            mensajeLabel.setStyle("-fx-text-fill: red;");
            mensajeLabel.setText("La hora debe ser válida (ej. 7:30 AM).");
            return;
        }


        Ruta nuevaRuta = new Ruta(
                origen,
                paradas,
                destino,
                hora,
                cupos,
                correoConductor
        );

        rutasRepo.guardarRuta(nuevaRuta);
        // ----------------------------------------------------

        mensajeLabel.setStyle("-fx-text-fill: green;");
        mensajeLabel.setText("Ruta publicada con éxito.");


        cargarRutas(campoBusquedaDestino.getText());


        origenField.clear();
        paradasField.clear();
        destinoField.clear();
        horaField.clear();
        cuposField.clear();
    }
}