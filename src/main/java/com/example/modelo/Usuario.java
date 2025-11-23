package com.example.modelo;

public class Usuario {
    private String nombre;
    private String correo;
    private String contraseña;
    private String rol;
    private String carrera;

    public Usuario(String nombre, String correo, String contraseña, String rol, String carrera) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
        this.carrera = carrera;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getContraseña() { return contraseña; }
    public String getRol() { return rol; }
    public String getCarrera() { return carrera; }
}
