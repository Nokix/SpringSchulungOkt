package gmbh.conteco.SpringSchulungOkt.aop;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Profile("aop")
public class ShoppingCartRunner implements CommandLineRunner {

    private ShoppingCart shoppingCart;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Los gehts.");
    }
}
