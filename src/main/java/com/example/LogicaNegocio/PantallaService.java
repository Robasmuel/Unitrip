package com.example.LogicaNegocio;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PantallaService {

    public void cambiarPantalla(Stage stage, String rutaFXML, String titulo) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.setTitle(titulo);
        stage.show();
    }

    public FXMLLoader getFXML(String ruta) {
        return new FXMLLoader(getClass().getResource(ruta));
    }
}
