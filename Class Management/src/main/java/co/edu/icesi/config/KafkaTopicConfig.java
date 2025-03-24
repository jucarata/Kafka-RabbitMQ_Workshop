package co.edu.icesi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic(){
        Map<String, String> configs = new HashMap<>();
        configs.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); // delete (borra mensaje) - compact (Mantiene el mas actual)
        configs.put(TopicConfig.RETENTION_MS_CONFIG, "600000"); // Tiempo de retencion de mensajes, defecto -1 que significa que nunca se vencen (600000 ms = 10 minutos)
        configs.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // Tamaño maximo del segmento - defecto 1073741824 bytes - 1GB
        configs.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); // Tamaño maximo de cada mensaje - defecto 1000000 - 1 MB

        return TopicBuilder.name("Ocupacion-Clases")
                .partitions(1)
                .replicas(1)
                .configs(configs)
                .build();
    }

}
