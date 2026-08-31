package api_destinos_turisticos.dto;

public record DestinoSolicitud(
        String ciudad,
        String pais,
        String descripcion,
        boolean visitado
) {
}