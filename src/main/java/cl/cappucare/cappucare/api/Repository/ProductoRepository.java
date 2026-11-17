package cl.cappucare.cappucare.api.Repository;

import cl.cappucare.cappucare.api.Model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Productos, Long> {
}