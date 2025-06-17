package utez.edu.mx.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.Models.Cede;

@Repository
public interface CedeRepository extends JpaRepository<Cede, Long> {
}
