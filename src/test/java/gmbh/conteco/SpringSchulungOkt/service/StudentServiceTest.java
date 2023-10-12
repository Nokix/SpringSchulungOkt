package gmbh.conteco.SpringSchulungOkt.service;

import gmbh.conteco.SpringSchulungOkt.jpa.Student;
import gmbh.conteco.SpringSchulungOkt.jpa.StudentRepository;
import gmbh.conteco.SpringSchulungOkt.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @MockBean
    StudentRepository studentRepository;

    Student s1 = new Student().setName("Hanna").setEmail("h@mail.de");
    Student s2 = new Student().setName("Anton").setEmail("a@mail.de");
    Student s3 = new Student().setName("Bernd").setEmail("b@mail.de");

    @BeforeEach
    void setup() {
        Mockito.when(studentRepository.findAll())
                .thenReturn(List.of(s1, s2, s3));
    }

    @Test
    void testFindAll() {
        List<Student> all = studentService.findAll();
        assertEquals(3, all.size());
        assertTrue(all.containsAll(List.of(s1, s2, s3)));
    }
}