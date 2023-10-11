package gmbh.conteco.SpringSchulungOkt.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseRunner implements CommandLineRunner {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        Student student = new Student(null, "Fabian", "Drees@mail.de");
        Student student2 = new Student(null, "Steffen", "Schöwitz@mail.de");
        Student student3 = new Student(null, "Andreas", "Schöwitz@mail.de");
        studentRepository.save(student);
        studentRepository.save(student2);
        studentRepository.save(student3);

        studentRepository.findTop2ByEmailContainsOrderByNameAsc("@")
                .forEach(System.out::println);

//        List<Student> studs = studentRepository.findByName("Fabian");
//        studs.forEach(System.out::println);

//        studentRepository.findAll().forEach(System.out::println);

    }
}
