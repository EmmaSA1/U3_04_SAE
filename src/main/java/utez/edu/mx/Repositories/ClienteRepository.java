package utez.edu.mx.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.Models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
