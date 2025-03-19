package co.edu.icesi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionDTO implements Serializable {
    private String usuarioId;
    private String mensaje;
}