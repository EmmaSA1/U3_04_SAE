package utez.edu.mx.Models;

import jakarta.persistence.*;
import lombok.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cedes")
public class Cede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String claveCede; // C[id]-[ddMMyyyy]-[4digitos]

    private String estado;

    private String municipio;

    // Genera clave luego de persistir porque id se genera después
    public void generarClave() {
        String fecha = new SimpleDateFormat("ddMMyyyy").format(new Date());
        int aleatorio = (int)(Math.random() * 9000) + 1000;
        this.claveCede = "C" + this.id + "-" + fecha + "-" + aleatorio;
    }
}
