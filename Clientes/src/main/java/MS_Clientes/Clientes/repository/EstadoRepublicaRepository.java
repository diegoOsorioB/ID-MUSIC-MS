package MS_Clientes.Clientes.repository;

import MS_Clientes.Clientes.entity.EstadoRepublica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepublicaRepository extends JpaRepository<EstadoRepublica, Integer> {
}
