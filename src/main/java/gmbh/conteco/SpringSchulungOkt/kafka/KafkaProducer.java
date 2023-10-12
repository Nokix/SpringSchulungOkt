package gmbh.conteco.SpringSchulungOkt.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("kafka")
public class KafkaProducer {

    @Autowired
    KafkaTemplate<String, String> template;

    @Value("test")
    String topic;

    @GetMapping("kafka")
    public String sendToCluster(@RequestParam String v) {
        template.send(topic, v);
        return v + " was send successfully.";
    }
}
