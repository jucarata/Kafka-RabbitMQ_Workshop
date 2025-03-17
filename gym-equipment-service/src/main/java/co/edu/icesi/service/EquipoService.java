package co.edu.icesi.service;

import co.edu.icesi.model.Equipo;
import co.edu.icesi.repository.EquipoRepository;
import co.edu.icesi.dto.NotificacionDTO; // si usas la misma clase de notificación
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    // ✅ Agregar un equipo
    public Equipo agregarEquipo(Equipo equipo) {
        Equipo equipoGuardado = equipoRepository.save(equipo);

        // Ejemplo: notificar asíncronamente que se agregó un equipo
        NotificacionDTO notificacion = new NotificacionDTO(
                "admin",
                "Se ha agregado el equipo: " + equipo.getNombre()
        );
        rabbitTemplate.convertAndSend(
                "notificacion.exchange",
                "notificacion.routingkey",
                notificacion
        );

        return equipoGuardado;
    }

    // ✅ Obtener todos los equipos
    public List<Equipo> obtenerTodosEquipos() {
        return equipoRepository.findAll();
    }
}
