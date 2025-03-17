package co.analisys.biblioteca.service;
import co.analisys.biblioteca.dto.NotificacionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class NotificacionConsumer {

    @Autowired
    private NotificacionService notificacionService;

    @RabbitListener(queues = "notificacion.queue")
    public void recibirNotificacion(NotificacionDTO notificacion) {
        // Log para confirmar
        System.out.println("[NotificacionConsumer] Mensaje recibido: " + notificacion.getMensaje());

        // Lógica para manejar la notificación
        notificacionService.enviarNotificacion(notificacion);
    }
}

