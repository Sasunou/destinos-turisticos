package api_destinos_turisticos.controlador;

import api_destinos_turisticos.dto.DestinoSolicitud;
import api_destinos_turisticos.modelo.Destino;
import api_destinos_turisticos.repositorio.DestinoRepositorio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoControlador {

    private final DestinoRepositorio destinoRepositorio;

    public DestinoControlador(DestinoRepositorio destinoRepositorio) {
        this.destinoRepositorio = destinoRepositorio;
    }

    // GET: consultamos todos los destinos guardados
    @GetMapping
    public ResponseEntity<List<Destino>> obtenerDestinos() {

        List<Destino> destinos = destinoRepositorio.findAll();

        return ResponseEntity.ok(destinos);
    }

    // GET con PathVariable: consultamos algun destino por ID
    @GetMapping("/{id}")
    public ResponseEntity<Destino> obtenerDestinoPorId(
            @PathVariable Long id) {

        return destinoRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET con RequestParam: buscamos los destinos por país
    @GetMapping("/buscar")
    public ResponseEntity<List<Destino>> buscarPorPais(
            @RequestParam String pais) {

        List<Destino> resultados =
                destinoRepositorio.findByPaisIgnoreCase(pais);

        return ResponseEntity.ok(resultados);
    }

    // POST: Para crear un nuevo destino
    @PostMapping
    public ResponseEntity<Destino> crearDestino(
            @RequestBody DestinoSolicitud solicitud) {

        Destino nuevoDestino = new Destino(
                null,
                solicitud.ciudad(),
                solicitud.pais(),
                solicitud.descripcion(),
                solicitud.visitado()
        );

        Destino destinoGuardado =
                destinoRepositorio.save(nuevoDestino);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(destinoGuardado);
    }

    // PUT: Se usa para actualizar un destino existente
    @PutMapping("/{id}")
    public ResponseEntity<Destino> actualizarDestino(
            @PathVariable Long id,
            @RequestBody DestinoSolicitud solicitud) {

        return destinoRepositorio.findById(id)
                .map(destino -> {

                    destino.setCiudad(solicitud.ciudad());
                    destino.setPais(solicitud.pais());
                    destino.setDescripcion(solicitud.descripcion());
                    destino.setVisitado(solicitud.visitado());

                    Destino destinoActualizado =
                            destinoRepositorio.save(destino);

                    return ResponseEntity.ok(destinoActualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: elimina un destino por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDestino(
            @PathVariable Long id) {

        if (!destinoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        destinoRepositorio.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}