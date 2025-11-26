package com.example.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosRepositorio {

    private static final String FILE_PATH = "usuarios.csv";


    public void guardarUsuario(Usuario u) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(
                    u.getNombre() + ";" +
                            u.getCorreo() + ";" +
                            u.getContraseña() + ";" +
                            u.getRol() + ";" +
                            u.getCarrera() + "\n"
            );
        }
    }


    // ---- NUEVO ----
    public Usuario buscarPorCorreo(String correoBuscado) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] p = linea.split(";");
                if (p.length < 5) continue;

                if (p[1].equalsIgnoreCase(correoBuscado)) {
                    return new Usuario(p[0], p[1], p[2], p[3], p[4]);
                }
            }
        } catch (Exception ignored) {}

        return null;
    }

    public String obtenerNombrePorCorreo(String correoBuscado) {
    Usuario u = buscarPorCorreo(correoBuscado);
    return (u != null) ? u.getNombre() : "Desconocido";
}
}