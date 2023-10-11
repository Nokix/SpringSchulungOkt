package gmbh.conteco.SpringSchulungOkt.hospital;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Handyman implements Helper{
    @Override
    public String assist() {
        return "Handyman is handy.";
    }
}
