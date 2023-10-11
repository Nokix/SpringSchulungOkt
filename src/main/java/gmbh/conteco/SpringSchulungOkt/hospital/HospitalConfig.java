package gmbh.conteco.SpringSchulungOkt.hospital;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class HospitalConfig {

    @Bean
    public String abc() {
        return "Christoph";
    }

    @Bean
    @Primary
    public String cdf() {
        return "Sebastian";
    }
}
