package gmbh.conteco.SpringSchulungOkt.service;

import gmbh.conteco.SpringSchulungOkt.jpa.Student;
import gmbh.conteco.SpringSchulungOkt.jpa.StudentRepository;
import gmbh.conteco.SpringSchulungOkt.rest.RandomNameGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private RandomNameGenerator randomNameGenerator;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public List<Student> saveMultipleStudent(int amount) {
        return Stream.generate(Student::new)
                .limit(amount)
                .map(s -> {
                    s.setName(randomNameGenerator.getName());
                    return s;
                })
                .map(s -> {
                    s.setEmail("random@mail.de");
                    return s;
                })
                .map(studentRepository::save)
                .toList();
    }
}
