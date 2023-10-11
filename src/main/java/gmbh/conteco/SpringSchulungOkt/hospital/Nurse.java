package gmbh.conteco.SpringSchulungOkt.hospital;

import org.springframework.stereotype.Component;

@Component
public class Nurse implements Helper {

    @Override
    public String assist() {
        return "Nurse is assisting.";
    }
}
