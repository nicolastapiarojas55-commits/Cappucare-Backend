package cl.cappucare.cappucare.api.Service;
import cl.cappucare.cappucare.api.Model.Movimiento;
import cl.cappucare.cappucare.api.Model.Productos;
import cl.cappucare.cappucare.api.Repository.MovimientoRepository;
import cl.cappucare.cappucare.api.Repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientos;
    private final ProductoRepository productos;

    public MovimientoService(MovimientoRepository movimientos, ProductoRepository productos) {
        this.movimientos = movimientos;
        this.productos = productos;
    }

    public List<Movimiento> listar() {
        return movimientos.findAllByOrderByFechaDesc();
    }

    public List<Movimiento> listarPorProducto(Long productoId) {
        return movimientos.findByProductoIdOrderByFechaDesc(productoId);
    }

    @Transactional
    public Movimiento registrar(Movimiento mov) {
        Productos producto = productos.findById(mov.getProductoId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Producto " + mov.getProductoId() + " no existe"));

        String tipo = mov.getTipo().toUpperCase();
        int nuevoStock;
        if (tipo.equals("INGRESO")) {
            nuevoStock = producto.getStockDisponible() + mov.getCantidad();
        } else if (tipo.equals("SALIDA")) {
            nuevoStock = producto.getStockDisponible() - mov.getCantidad();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo debe ser INGRESO o SALIDA");
        }

        if (nuevoStock < 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "La salida supera el stock disponible (" + producto.getStockDisponible() + ")");
        }

        producto.setStockDisponible(nuevoStock);
        productos.save(producto);

        mov.setId(null);
        mov.setTipo(tipo);
        mov.setFecha(System.currentTimeMillis());
        return movimientos.save(mov);
    }
}