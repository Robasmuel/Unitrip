package com.example.modelo;

public class Vehiculo {
    
    private String placa;
    private String modelo;
    private String color;
    private int capacidad;
    private String correoUsuario; // o un idUsuario si lo tienes

    public Vehiculo(String placa, String modelo, String color, int capacidad, String correoUsuario) {
        this.placa = placa;
        this.modelo = modelo;
        this.color = color;
        this.capacidad = capacidad;
        this.correoUsuario = correoUsuario;
    }
    // Getters
    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public String getColor() { return color; }
    public int getCapacidad() { return capacidad; }
    public String getCorreoUsuario() { return correoUsuario; }
}
