package com.example.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RutasRepositorio {

    private static final String FILE_PATH = "rutas.csv";


    public void guardarRuta(Ruta r) {

        String linea = String.format("%s;%s;%s;%s;%d;%s",
                r.getOrigen(),
                r.getParadas().isEmpty() ? "N/A" : r.getParadas(),
                r.getDestino(),
                r.getHora(),
                r.getCupos(),
                r.getCorreoConductor()
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar ruta en archivo: " + e.getMessage());
        }
    }


    private Ruta parseRuta(String linea) {
        String[] partes = linea.split(";");

        if (partes.length < 6) return null;

        try {
            String origen = partes[0];
            String paradas = partes[1].equals("N/A") ? "" : partes[1];
            String destino = partes[2];
            String hora = partes[3];
            int cupos = Integer.parseInt(partes[4]);
            String correo = partes[5];

            return new Ruta(origen, paradas, destino, hora, cupos, correo);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Error al parsear línea de ruta: " + linea);
            return null;
        }
    }


    public List<Ruta> obtenerTodasLasRutas() {
        return obtenerRutasPorDestino(null);
    }


    public List<Ruta> obtenerRutasPorDestino(String filtroDestino) {

        List<Ruta> rutasFiltradas = new ArrayList<>();


        String filtro = (filtroDestino == null) ? "" : filtroDestino.trim().toLowerCase();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Ruta r = parseRuta(linea);
                if (r != null) {

                    if (filtro.isEmpty() || r.getDestino().toLowerCase().contains(filtro)) {
                        rutasFiltradas.add(r);
                    }
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.err.println("Error al leer rutas para el filtro: " + e.getMessage());
        }
        return rutasFiltradas;
    }

    public List<Ruta> buscarRutas(String filtro, UsuariosRepositorio usuariosRepo) {
    List<Ruta> resultado = new ArrayList<>();
    if (filtro == null) filtro = "";
    filtro = quitarAcentos(filtro.toLowerCase());

    try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            Ruta r = parseRuta(linea);
            if (r == null) continue;

            Usuario conductor = usuariosRepo.buscarPorCorreo(r.getCorreoConductor());
            String carrera = (conductor != null) ? conductor.getCarrera() : "";

            // Concatenamos todos los campos para buscar
            String texto = (r.getDestino() + " " +
                r.getOrigen() + " " +
                r.getParadas() + " " +
                r.getHora() + " " +
                r.getCupos() + " " +           // ← NUEVO
                r.getCorreoConductor() + " " +
                carrera).toLowerCase();
            texto = quitarAcentos(texto);

            if (texto.contains(filtro)) {
                resultado.add(r);
            }
        }
    } catch (Exception e) {
        System.out.println("Error al buscar rutas: " + e.getMessage());
    }

    return resultado;
}

// ----- eliminar acentos -----
private String quitarAcentos(String texto) {
    return java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD)
            .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
}
}