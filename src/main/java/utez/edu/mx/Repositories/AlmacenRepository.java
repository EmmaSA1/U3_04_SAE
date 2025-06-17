package utez.edu.mx.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.Models.Almacen;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
}
