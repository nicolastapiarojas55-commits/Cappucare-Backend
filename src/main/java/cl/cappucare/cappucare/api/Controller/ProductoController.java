package cl.cappucare.cappucare.api.Controller;
import cl.cappucare.cappucare.api.Model.Productos;
import cl.cappucare.cappucare.api.Service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Productos> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Productos obtener(@PathVariable Long id) { return service.obtener(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Productos crear(@Valid @RequestBody Productos p) { return service.crear(p); }

    @PutMapping("/{id}")
    public Productos actualizar(@PathVariable Long id, @Valid @RequestBody Productos p) {
        return service.actualizar(id, p);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}