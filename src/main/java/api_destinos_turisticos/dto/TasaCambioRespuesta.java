package api_destinos_turisticos.dto;

public record TasaCambioRespuesta(
        String date,
        String base,
        String quote,
        double rate
) {
}