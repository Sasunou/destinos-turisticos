package api_destinos_turisticos.repositorio;

import api_destinos_turisticos.modelo.Destino;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinoRepositorio extends JpaRepository<Destino, Long> {

    List<Destino> findByPaisNombreIgnoreCase(String nombre);

}