package utez.edu.mx.Models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "almacenes")
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String claveAlmacen; // [claveCede]-A[id]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cede_id")
    private Cede cede;

    private LocalDate fechaRegistro;

    private BigDecimal precioVenta;

    private BigDecimal precioRenta;

    private String tamaño; // G, M, P

    @PrePersist
    public void setFechaRegistro() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDate.now();
        }
    }
}
