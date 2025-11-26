package com.example.Controller;

import com.example.LogicaNegocio.RutaService;
import com.example.LogicaNegocio.UsuarioService;
import com.example.modelo.Ruta;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class RutasPasajeroController {

    @FXML private TextField campoBusquedaDestino;
    @FXML private TableView<Ruta> tablaRutas;
    @FXML private TableColumn<Ruta, String> columnaDestino;
    @FXML private TableColumn<Ruta, String> columnaOrigen;
    @FXML private TableColumn<Ruta, String> columnaHora;
    @FXML private TableColumn<Ruta, String> columnaCupos;
    @FXML private TableColumn<Ruta, String> columnaConductor;

    private final RutaService rutaService = new RutaService();
    private final UsuarioService usuarioService = new UsuarioService();

    private ObservableList<Ruta> rutasData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarTabla();
    }

    @FXML
    private void mostrarRutas() {
        cargarRutas(null);
        configurarFiltroBusqueda();
    }

    private void configurarTabla() {
        columnaDestino.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDestino()));
        columnaOrigen.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getOrigen()));
        columnaHora.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHora()));

        columnaConductor.setCellValueFactory(c ->
                new SimpleStringProperty(usuarioService.obtenerNombrePorCorreo(
                        c.getValue().getCorreoConductor()
                ))
        );

        columnaCupos.setCellValueFactory(c ->
                new SimpleStringProperty(String.valueOf(c.getValue().getCupos()))
        );

        tablaRutas.setItems(rutasData);
    }

    private void configurarFiltroBusqueda() {
        campoBusquedaDestino.textProperty().addListener((obs, oldVal, newVal) -> cargarRutas(newVal));
    }

    private void cargarRutas(String filtro) {
        List<Ruta> lista = (filtro == null || filtro.isEmpty()) ?
                rutaService.obtenerTodas() :
                rutaService.buscarRutasCompletas(filtro);

        rutasData.setAll(lista);
    }
}
