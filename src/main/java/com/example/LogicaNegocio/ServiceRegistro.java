package com.example.LogicaNegocio;

import com.example.modelo.Usuario;
import com.example.modelo.UsuariosRepositorio;

public class ServiceRegistro {

    private UsuariosRepositorio repo = new UsuariosRepositorio();

    public Usuario registrarUsuario(
            String nombre,
            String correo,
            String pass,
            String confirm,
            String rol,
            String carrera
    ) throws Exception {

        // Validaciones
        if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty() ||
                confirm.isEmpty() || rol == null || carrera.isEmpty()) {
            throw new Exception("Completa todos los campos.");
        }

        if (!correo.endsWith("@javeriana.edu.co")) {
            throw new Exception("El correo debe ser institucional.");
        }

        if (!pass.equals(confirm)) {
            throw new Exception("Las contraseñas no coinciden.");
        }

        // Crear usuario
        Usuario u = new Usuario(nombre, correo, pass, rol, carrera);
        repo.guardarUsuario(u);

        return u;
    }
}
