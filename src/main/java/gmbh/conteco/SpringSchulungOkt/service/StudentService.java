package gmbh.conteco.SpringSchulungOkt.service;

import gmbh.conteco.SpringSchulungOkt.jpa.Student;
import gmbh.conteco.SpringSchulungOkt.jpa.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
