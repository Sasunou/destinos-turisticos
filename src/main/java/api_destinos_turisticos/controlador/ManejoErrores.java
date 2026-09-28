package api_destinos_turisticos.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ManejoErrores {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public Map<String, String> manejarErrorExterno(RuntimeException e) {
        return Map.of(
                "error", "No fue posible consultar el servicio externo",
                "detalle", e.getMessage()
        );
    }
}