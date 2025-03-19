package co.analisys.biblioteca.service;
import co.analisys.biblioteca.dto.NotificacionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
@Service
public class NotificacionConsumer {

    @RabbitListener(queues = "notificacion.queue") // Debe coincidir con "notificacion.queue"
    public void recibirNotificacion(NotificacionDTO notificacion) {
        // Log para confirmar que recibimos el mensaje
        System.out.println("[NotificacionConsumer] Mensaje recibido: " + notificacion.getMensaje());
    }
}

