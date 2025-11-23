package com.example.modelo;

import java.io.FileWriter;
import java.io.IOException;

public class UsuariosRepositorio {

    private static final String FILE_PATH = "usuarios.csv";

    public void guardarUsuario(Usuario u) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH, true);
        writer.write(
                u.getNombre() + ";" +
                        u.getCorreo() + ";" +
                        u.getContraseña() + ";" +
                        u.getRol() + ";" +
                        u.getCarrera() + "\n"
        );
        writer.close();
    }
}

