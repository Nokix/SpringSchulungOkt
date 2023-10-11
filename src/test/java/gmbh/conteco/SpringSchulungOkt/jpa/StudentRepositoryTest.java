package gmbh.conteco.SpringSchulungOkt.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    StudentRepository studentRepository;

    Student s1 = new Student().setName("Hanna").setEmail("h@mail.de");
    Student s2 = new Student().setName("Anton").setEmail("a@mail.de");
    Student s3 = new Student().setName("Bernd").setEmail("b@mail.de");

    @BeforeEach
    void setup() {
        entityManager.persist(s1);
        entityManager.persist(s2);
        entityManager.persist(s3);
    }

    @Test
    void testCountByName() {
        long count = studentRepository.countByName("Hanna");
        assertEquals(1, count);
    }

    @Test
    void test_FindAnywhere() {
        List<Student> students = studentRepository.findAnywhere(".de");
        assertTrue(students.containsAll(List.of(s2, s1, s3)));
    }

    @Test
    void test_findAllPages() {
        Sort sorting = Sort.by("name").ascending();

        PageRequest first2 = PageRequest.of(0, 2, sorting);

        Iterable<Student> all = studentRepository.findAll(first2);

        all.forEach(System.out::println);
    }

    @Test
    void test_findByNamePages() {
        PageRequest first2 = PageRequest.of(0, 2);

        Iterable<Student> all = studentRepository.findByName("n", first2);

        all.forEach(System.out::println);

        List<Student> list =
                StreamSupport.stream(all.spliterator(), false).toList();

    }
}