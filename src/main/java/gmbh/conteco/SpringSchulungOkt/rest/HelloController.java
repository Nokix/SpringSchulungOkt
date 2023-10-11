package gmbh.conteco.SpringSchulungOkt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class HelloController {

    @Autowired
    RandomNameGenerator generator;

    @GetMapping("/hallo")
    public String sayHello() {
        return "Hallo";
    }

    @GetMapping("/bye/{name}/{nachname}")
    public String sayByeTo(
            @PathVariable String name,
            @PathVariable String nachname
    ) {
        return "Tschöö " + name + " " + nachname;
    }

    @GetMapping("/login")
    public String welcomeLogin(@RequestParam String name) {
        return "Willkommen " + name;
    }

    @GetMapping("/random")
    public List<String> getRandomName(
            @RequestParam(value = "n", defaultValue = "1") int amount) {
        return Stream.generate(generator::getName)
                .limit(amount)
                .toList();
    }
}
