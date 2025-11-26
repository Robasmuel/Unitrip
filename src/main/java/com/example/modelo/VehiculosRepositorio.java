package com.example.modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehiculosRepositorio {

    private List<Vehiculo> vehiculos = new ArrayList<>();


    private static final String FILE_PATH = "vehiculos.csv";

    public void guardarVehiculo(Vehiculo v) {

        vehiculos.add(v);


        guardarEnArchivo(v);
    }

    private void guardarEnArchivo(Vehiculo v) {

        String linea = String.format("%s;%s;%s;%d;%s",
                v.getPlaca(),
                v.getModelo(),
                v.getColor(),
                v.getCapacidad(),
                v.getCorreoUsuario()
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {

            System.err.println("Error al guardar vehículo en archivo: " + e.getMessage());
        }
    }

    public List<Vehiculo> obtenerVehiculosPorUsuario(String correoUsuario) {
        return vehiculos.stream()
                .filter(v -> v.getCorreoUsuario().equals(correoUsuario))
                .toList();
    }
}
