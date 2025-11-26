package com.example.modelo;

public class Ruta {
    private String origen;
    private String paradas;
    private String destino;
    private String hora;
    private int cupos;
    private String correoConductor;


    public Ruta(String origen, String paradas, String destino, String hora, int cupos, String correoConductor) {
        this.origen = origen;
        this.paradas = paradas;
        this.destino = destino;
        this.hora = hora;
        this.cupos = cupos;
        this.correoConductor = correoConductor;
    }


    public String getOrigen() { return origen; }
    public String getParadas() { return paradas; }
    public String getDestino() { return destino; }
    public String getHora() { return hora; }
    public int getCupos() { return cupos; }
    public String getCorreoConductor() { return correoConductor; }
}