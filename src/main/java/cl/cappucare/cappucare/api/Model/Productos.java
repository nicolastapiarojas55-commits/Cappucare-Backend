package cl.cappucare.cappucare.api.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @NotNull
    private Long id;            // lo asigna la app, igual que en Room

    @NotBlank
    private String nombre;
    private String descripcion = "";
    @NotBlank
    private String categoria;
    @Min(0)
    private int stockDisponible;
    @NotBlank
    private String unidadMedida;
    @Min(0)
    private int stockMinimo;
    private String lugarAlmacenamiento = "";
    private boolean activo = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public int getStockDisponible() { return stockDisponible; }
    public void setStockDisponible(int s) { this.stockDisponible = s; }
    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String u) { this.unidadMedida = u; }
    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int s) { this.stockMinimo = s; }
    public String getLugarAlmacenamiento() { return lugarAlmacenamiento; }
    public void setLugarAlmacenamiento(String l) { this.lugarAlmacenamiento = l; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}