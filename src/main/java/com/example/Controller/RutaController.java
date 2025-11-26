package com.example.Controller;

import com.example.LogicaNegocio.RutaService;
import com.example.modelo.Ruta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class RutaController {

    @FXML private TextField origenField;
    @FXML private TextArea paradasField;
    @FXML private TextField destinoField;
    @FXML private TextField horaField;
    @FXML private TextField cuposField;
    @FXML private Label mensajeLabel;

    @FXML private TextField campoBusquedaDestino;
    @FXML private TableView<Ruta> tablaRutas;
    @FXML private TableColumn<Ruta, String> columnaDestino;
    @FXML private TableColumn<Ruta, String> columnaOrigen;
    @FXML private TableColumn<Ruta, String> columnaHora;
    @FXML private TableColumn<Ruta, String> columnaCupos;
    @FXML private TableColumn<Ruta, String> columnaConductor;

    private final RutaService rutaService = new RutaService();
    private ObservableList<Ruta> rutasData = FXCollections.observableArrayList();
    private String correoConductor;

    public void setCorreoConductor(String correo) {
        this.correoConductor = correo;
    }

    @FXML
    public void initialize() {
        configurarTabla();
        cargarRutas(null);
        configurarFiltroBusqueda();
    }

    private void configurarTabla() {
        columnaDestino.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDestino()));
        columnaOrigen.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getOrigen()));
        columnaHora.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHora()));
        columnaConductor.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getCorreoConductor()));
        columnaCupos.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getCupos())));

        tablaRutas.setItems(rutasData);
    }

    private void configurarFiltroBusqueda() {
        campoBusquedaDestino.textProperty().addListener((obs, oldVal, newVal) -> cargarRutas(newVal));
    }

    private void cargarRutas(String filtro) {
        List<Ruta> lista = (filtro == null || filtro.isEmpty()) ?
                rutaService.obtenerTodas() :
                rutaService.buscarPorDestino(filtro);

        rutasData.setAll(lista);
    }

    @FXML
    private void guardarRuta() {

        String resultado = rutaService.crearRuta(
                origenField.getText().trim(),
                paradasField.getText().trim(),
                destinoField.getText().trim(),
                horaField.getText().trim(),
                cuposField.getText().trim(),
                correoConductor
        );

        if (resultado.contains("éxito")) {
            mensajeLabel.setStyle("-fx-text-fill: green;");
            cargarRutas(campoBusquedaDestino.getText());
            origenField.clear();
            paradasField.clear();
            destinoField.clear();
            horaField.clear();
            cuposField.clear();
        } else {
            mensajeLabel.setStyle("-fx-text-fill: red;");
        }

        mensajeLabel.setText(resultado);
    }
}
