package api_destinos_turisticos.controlador;

import api_destinos_turisticos.dto.DestinoSolicitud;
import api_destinos_turisticos.modelo.Destino;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoControlador {

    private final List<Destino> destinos = new ArrayList<>();

    private Long siguienteId = 9L;

    public DestinoControlador() {

        destinos.add(new Destino(
                1L,
                "Osaka",
                "Japón",
                "Ciudad conocida por su gastronomía, vida nocturna y ambiente urbano.",
                true
        ));

        destinos.add(new Destino(
                2L,
                "Tokio",
                "Japón",
                "Gran ciudad japonesa caracterizada por su tecnología, cultura y diversidad.",
                true
        ));

        destinos.add(new Destino(
                3L,
                "Bali",
                "Indonesia",
                "Destino conocido por sus playas, templos, paisajes naturales y cultura.",
                true
        ));

        destinos.add(new Destino(
                4L,
                "Bangkok",
                "Tailandia",
                "Capital de Tailandia reconocida por sus templos, mercados y gastronomía.",
                true
        ));

        destinos.add(new Destino(
                5L,
                "Hanoi",
                "Vietnam",
                "Capital de Vietnam con una combinación de historia, arquitectura y gastronomía.",
                true
        ));

        destinos.add(new Destino(
                6L,
                "Sapa",
                "Vietnam",
                "Destino montañoso conocido por sus paisajes, arrozales y comunidades locales.",
                true
        ));

        destinos.add(new Destino(
                7L,
                "Ninh Binh",
                "Vietnam",
                "Región conocida por sus paisajes de montañas, ríos y formaciones naturales.",
                true
        ));

        destinos.add(new Destino(
                8L,
                "Estambul",
                "Turquía",
                "Ciudad ubicada entre Europa y Asia, reconocida por su historia y patrimonio cultural.",
                true
        ));
    }

    @GetMapping
    public ResponseEntity<List<Destino>> obtenerDestinos() {
        return ResponseEntity.ok(destinos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> obtenerDestinoPorId(@PathVariable Long id) {

        for (Destino destino : destinos) {

            if (destino.getId().equals(id)) {
                return ResponseEntity.ok(destino);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Destino>> buscarPorPais(
            @RequestParam String pais) {

        List<Destino> resultados = new ArrayList<>();

        for (Destino destino : destinos) {

            if (destino.getPais().equalsIgnoreCase(pais)) {
                resultados.add(destino);
            }
        }

        return ResponseEntity.ok(resultados);
    }

    @PostMapping
    public ResponseEntity<Destino> crearDestino(
            @RequestBody DestinoSolicitud solicitud) {

        Destino nuevoDestino = new Destino(
                siguienteId,
                solicitud.ciudad(),
                solicitud.pais(),
                solicitud.descripcion(),
                solicitud.visitado()
        );

        destinos.add(nuevoDestino);

        siguienteId++;

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nuevoDestino);
    }
}