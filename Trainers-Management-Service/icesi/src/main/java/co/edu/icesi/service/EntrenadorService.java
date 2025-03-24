package co.edu.icesi.service;

import co.edu.icesi.config.RabbitMQConfig;
import co.edu.icesi.dto.ClassesDTO;
import co.edu.icesi.model.Entrenador;
import co.edu.icesi.repository.EntrenadorRepository;
import co.edu.icesi.dto.NotificacionDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    private final String CLASSES_TOPIC_NAME = "Ocupacion-Clases";


    @Autowired
    private RabbitTemplate rabbitTemplate; // Para enviar notificaciones

    public EntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public List<Entrenador> listarEntrenadores() {
        return entrenadorRepository.findAll();
    }

    public Entrenador agregarEntrenador(Entrenador entrenador) {
        Entrenador nuevoEntrenador = entrenadorRepository.save(entrenador);

        // Enviar notificación (ejemplo)
        NotificacionDTO notificacion = new NotificacionDTO(
                "admin",
                "Se ha agregado el entrenador: " + (nuevoEntrenador.getNombre() != null ? nuevoEntrenador.getNombre() : "")
        );
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.NOTIFICATION_ROUTING_KEY,
                notificacion
        );

        return nuevoEntrenador;
    }

    public Entrenador obtenerEntrenador(Long id) {
        return entrenadorRepository.findById(id).orElse(null);
    }


    @KafkaListener(topics = {CLASSES_TOPIC_NAME}, groupId = "classes-streaming")
    public void notificacionClasesTiempoReal(ClassesDTO clase){
        System.out.println("Han registrado una nueva clase:  " + clase.toString());
    }

}
