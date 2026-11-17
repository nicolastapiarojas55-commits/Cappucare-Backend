package cl.cappucare.cappucare.api.Repository;

import cl.cappucare.cappucare.api.Model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    List<Movimiento> findAllByOrderByFechaDesc();

    List<Movimiento> findByProductoIdOrderByFechaDesc(Long productoId);
}