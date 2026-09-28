package api_destinos_turisticos.repositorio;

import api_destinos_turisticos.modelo.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepositorio extends JpaRepository<Pais, Long> {
}