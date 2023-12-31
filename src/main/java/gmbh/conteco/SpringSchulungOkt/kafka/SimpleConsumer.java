package gmbh.conteco.SpringSchulungOkt.kafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
public class SimpleConsumer {

    @KafkaListener(id = "Viktor", topics = "test")
    public void listen(String value) {
        System.out.println(value);
    }
}
