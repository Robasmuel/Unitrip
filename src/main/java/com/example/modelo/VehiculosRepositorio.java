package com.example.modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VehiculosRepositorio {

    private List<Vehiculo> vehiculos = new ArrayList<>();

    // Ruta del archivo donde vas a guardar los vehículos
    // Puedes cambiarla si quieres otra carpeta
    private static final String FILE_PATH = "vehiculos.csv";

    public void guardarVehiculo(Vehiculo v) {
        // 1. Guardar en memoria
        vehiculos.add(v);

        // 2. Guardar (append) en el archivo
        guardarEnArchivo(v);
    }

    private void guardarEnArchivo(Vehiculo v) {
        // Formato simple tipo CSV: placa;modelo;color;capacidad;correoUsuario
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
            // Aquí podrías mostrar un mensaje o loguear el error
            System.err.println("Error al guardar vehículo en archivo: " + e.getMessage());
        }
    }

    public List<Vehiculo> obtenerVehiculosPorUsuario(String correoUsuario) {
        return vehiculos.stream()
                .filter(v -> v.getCorreoUsuario().equals(correoUsuario))
                .toList();
    }
}
