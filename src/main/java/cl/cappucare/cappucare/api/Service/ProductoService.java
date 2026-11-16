mport cl.cappucare.cappucare.api.model.Producto;
import cl.cappucare.cappucare.api.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto obtener(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto " + id + " no existe"));
    }

    public Producto crear(Producto p) {
        if (repo.existsById(p.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe el ID " + p.getId());
        }
        return repo.save(p);
    }

    public Producto actualizar(Long id, Producto datos) {
        Producto actual = obtener(id);
        actual.setNombre(datos.getNombre());
        actual.setDescripcion(datos.getDescripcion());
        actual.setCategoria(datos.getCategoria());
        actual.setUnidadMedida(datos.getUnidadMedida());
        actual.setStockMinimo(datos.getStockMinimo());
        actual.setLugarAlmacenamiento(datos.getLugarAlmacenamiento());
        actual.setActivo(datos.isActivo());
        return repo.save(actual);   // el stock solo cambia con movimientos
    }

    public void eliminar(Long id) {
        obtener(id);
        repo.deleteById(id);
    }
}