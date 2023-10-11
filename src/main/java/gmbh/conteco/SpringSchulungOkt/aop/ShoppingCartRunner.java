package gmbh.conteco.SpringSchulungOkt.aop;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShoppingCartRunner implements CommandLineRunner {

    private ShoppingCart shoppingCart;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Los gehts.");
    }
}
