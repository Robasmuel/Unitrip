package com.example.LogicaNegocio;

import com.example.modelo.Usuario;
import com.example.modelo.UsuariosRepositorio;
import com.example.modelo.Vehiculo;
import com.example.modelo.VehiculosRepositorio;

public class ServiceConductor {

    private UsuariosRepositorio usuariosRepo = new UsuariosRepositorio();
    private VehiculosRepositorio vehiculosRepo = new VehiculosRepositorio();

    public Usuario guardarConductor(
            String placa,
            String modelo,
            String color,
            String capacidad,
            String nombre,
            String correo,
            String pass,
            String carrera
    ) throws Exception {

        int cap;

        if (placa.isEmpty() || modelo.isEmpty() || color.isEmpty() || capacidad.isEmpty()) {
            throw new Exception("Completa todos los campos del vehículo.");
        }

        if (!placa.toUpperCase().matches("[A-Z]{3}[0-9]{3}")) {
            throw new Exception("La matrícula debe tener formato ABC123.");
        }

        try {
            cap = Integer.parseInt(capacidad);
        } catch (NumberFormatException e) {
            throw new Exception("La capacidad debe ser un número.");
        }

        // Guardar vehículo
        Vehiculo veh = new Vehiculo(placa, modelo, color, cap, correo);
        vehiculosRepo.guardarVehiculo(veh);

        // Guardar usuario
        Usuario u = new Usuario(nombre, correo, pass, "Conductor", carrera);
        usuariosRepo.guardarUsuario(u);

        return u;
    }
}
