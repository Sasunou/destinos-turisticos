package api_destinos_turisticos.controlador;

import api_destinos_turisticos.dto.TasaCambioRespuesta;
import api_destinos_turisticos.servicio.TasaCambioServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TasaCambioControlador {

    private final TasaCambioServicio tasaCambioServicio;

    public TasaCambioControlador(TasaCambioServicio tasaCambioServicio) {
        this.tasaCambioServicio = tasaCambioServicio;
    }

    @GetMapping("/api/tasas")
    public TasaCambioRespuesta obtenerTasa(
            @RequestParam String base,
            @RequestParam String quote) {

        return tasaCambioServicio.obtenerTasa(base, quote);
    }
}