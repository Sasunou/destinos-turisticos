package api_destinos_turisticos.dto;

public record DestinoSolicitud(
        String ciudad,
        Long paisId,
        String descripcion,
        boolean visitado
) {
}