package utez.edu.mx.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {
    private Long id;
    private String nombreCompleto;
    private String telefono;
    private String correo;
}
