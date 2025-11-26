package com.example.LogicaNegocio;

import com.example.modelo.Ruta;
import com.example.modelo.RutasRepositorio;
import com.example.modelo.UsuariosRepositorio;

import java.util.List;

public class RutaService {

    private final RutasRepositorio rutasRepo = new RutasRepositorio();
    private final UsuariosRepositorio usuariosRepo = new UsuariosRepositorio();

    // ---------------- VALIDACIONES ----------------

    public String validarDatosRuta(String origen, String paradas, String destino,
                                   String hora, String cuposStr) {

        if (origen.isEmpty() || destino.isEmpty() || hora.isEmpty() || cuposStr.isEmpty()) {
            return "Completa origen, destino, hora y cupos.";
        }

        if (origen.equalsIgnoreCase(destino)) {
            return "Origen y destino deben ser diferentes.";
        }

        int cupos;
        try {
            cupos = Integer.parseInt(cuposStr);
            if (cupos <= 0) {
                return "Los cupos deben ser mayores que 0.";
            }
        } catch (NumberFormatException e) {
            return "Los cupos deben ser un número.";
        }

        if (!hora.matches("[0-9]{1,2}:[0-9]{2}(\\s?(AM|PM|am|pm))?")) {
            return "La hora debe ser válida (ej. 7:30 AM).";
        }

        return "OK";
    }

    // ---------------- CREAR RUTA ----------------

    public String crearRuta(String origen, String paradas, String destino,
                            String hora, String cuposStr, String correoConductor) {

        String validacion = validarDatosRuta(origen, paradas, destino, hora, cuposStr);
        if (!validacion.equals("OK"))
            return validacion;

        int cupos = Integer.parseInt(cuposStr);

        Ruta nueva = new Ruta(origen, paradas, destino, hora, cupos, correoConductor);
        rutasRepo.guardarRuta(nueva);

        return "Ruta publicada con éxito.";
    }

    // ---------------- BÚSQUEDAS ----------------

    public List<Ruta> obtenerTodas() {
        return rutasRepo.obtenerTodasLasRutas();
    }

    public List<Ruta> buscarPorDestino(String filtro) {
        return rutasRepo.obtenerRutasPorDestino(filtro);
    }

    public List<Ruta> buscarRutasCompletas(String filtro) {
        return rutasRepo.buscarRutas(filtro, usuariosRepo);
    }
}
