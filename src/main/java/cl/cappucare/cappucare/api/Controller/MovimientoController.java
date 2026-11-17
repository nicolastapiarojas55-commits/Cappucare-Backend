package cl.cappucare.cappucare.api.Controller;

import cl.cappucare.cappucare.api.Model.Movimiento;
import cl.cappucare.cappucare.api.Service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService service;

    public MovimientoController(MovimientoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Movimiento> listar() { return service.listar(); }

    @GetMapping("/producto/{productoId}")
    public List<Movimiento> porProducto(@PathVariable Long productoId) {
        return service.listarPorProducto(productoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movimiento registrar(@Valid @RequestBody Movimiento m) { return service.registrar(m); }
}
