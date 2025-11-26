package com.example.LogicaNegocio;

import com.example.modelo.Usuario;
import com.example.modelo.UsuariosRepositorio;

public class UsuarioService {

    private final UsuariosRepositorio usuariosRepo = new UsuariosRepositorio();

    public String obtenerNombrePorCorreo(String correo) {
        return usuariosRepo.obtenerNombrePorCorreo(correo);
    }

    public Usuario buscarPorCorreo(String correo) {
        return usuariosRepo.buscarPorCorreo(correo);
    }
}
