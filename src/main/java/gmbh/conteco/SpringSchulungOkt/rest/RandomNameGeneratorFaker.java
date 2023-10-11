package gmbh.conteco.SpringSchulungOkt.rest;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomNameGeneratorFaker implements RandomNameGenerator{
    @Autowired
    Faker faker;

    @Override
    public String getName() {
        return faker.name().fullName();
    }
}
