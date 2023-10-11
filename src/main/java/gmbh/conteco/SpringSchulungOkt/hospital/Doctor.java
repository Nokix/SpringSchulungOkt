package gmbh.conteco.SpringSchulungOkt.hospital;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Setter
@AllArgsConstructor
public class Doctor {
    private String name;
    private Helper helper;

//        public Doctor(
//            // Spring Expression Language (SPEL)
//            //@Value("${doctor.name}")
//            @Value("#{hospitalConfig.abc()}")
//            String name,
//            @Qualifier("nurse") Helper helper
//    ) {
//        this.name = name;
//        this.helper = helper;
//    }

    public String assist() {
        return "Dr. " + this.name + " is helping. "
                + this.helper.assist();
    }
}
