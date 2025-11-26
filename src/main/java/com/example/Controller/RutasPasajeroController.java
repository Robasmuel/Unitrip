package com.example.Controller;

import com.example.modelo.Ruta;
import com.example.modelo.RutasRepositorio;
import com.example.modelo.UsuariosRepositorio;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox; // Importar VBox
import java.util.List;

public class RutasPasajeroController {

    @FXML private VBox contenedorRutas;
    @FXML private TextField campoBusquedaDestino;
    @FXML private TableView<Ruta> tablaRutas;
    @FXML private TableColumn<Ruta, String> columnaDestino;
    @FXML private TableColumn<Ruta, String> columnaOrigen;
    @FXML private TableColumn<Ruta, String> columnaHora;
    @FXML private TableColumn<Ruta, String> columnaCupos;
    @FXML private TableColumn<Ruta, String> columnaConductor;

    private RutasRepositorio rutasRepo = new RutasRepositorio();
    private ObservableList<Ruta> rutasData = FXCollections.observableArrayList();
    private String correoUsuario;

    public void setCorreoUsuario(String correo) {
        this.correoUsuario = correo;
    }

    @FXML
    public void initialize() {

        configurarTabla();
        // Nota: contenedorRutas ya está oculto por el FXML
    }

    @FXML
    private void mostrarRutas() {

        contenedorRutas.setVisible(true);
        contenedorRutas.setManaged(true);


        cargarRutas(null);
        configurarFiltroBusqueda();
    }



    private void configurarTabla() {
        columnaDestino.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestino()));
        columnaOrigen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrigen()));
        columnaHora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHora()));
        columnaConductor.setCellValueFactory(cellData -> {
    UsuariosRepositorio usuariosRepo = new UsuariosRepositorio();
    String nombre = usuariosRepo.obtenerNombrePorCorreo(
            cellData.getValue().getCorreoConductor()
    );
    return new SimpleStringProperty(nombre);
});
        columnaCupos.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getCupos())));

        tablaRutas.setItems(rutasData);
    }

    private void configurarFiltroBusqueda() {
        campoBusquedaDestino.textProperty().addListener((observable, oldValue, newValue) -> {
            cargarRutas(newValue);
        });
    }

    private void cargarRutas(String filtro) {
    UsuariosRepositorio usuariosRepo = new UsuariosRepositorio();

    List<Ruta> rutasFiltradas;

    if (filtro == null || filtro.trim().isEmpty()) {
        rutasFiltradas = rutasRepo.obtenerTodasLasRutas();
    } else {
        rutasFiltradas = rutasRepo.buscarRutas(filtro.trim(), usuariosRepo);
    }

    rutasData.clear();
    rutasData.addAll(rutasFiltradas);
}
}