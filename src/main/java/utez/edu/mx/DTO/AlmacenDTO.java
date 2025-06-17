package utez.edu.mx.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AlmacenDTO {
    private Long id;
    private String claveAlmacen;
    private Long cedeId;
    private LocalDate fechaRegistro;
    private BigDecimal precioVenta;
    private BigDecimal precioRenta;
    private String tamaño;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClaveAlmacen() { return claveAlmacen; }
    public void setClaveAlmacen(String claveAlmacen) { this.claveAlmacen = claveAlmacen; }

    public Long getCedeId() { return cedeId; }
    public void setCedeId(Long cedeId) { this.cedeId = cedeId; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }

    public BigDecimal getPrecioRenta() { return precioRenta; }
    public void setPrecioRenta(BigDecimal precioRenta) { this.precioRenta = precioRenta; }

    public String getTamaño() { return tamaño; }
    public void setTamaño(String tamaño) { this.tamaño = tamaño; }
}
