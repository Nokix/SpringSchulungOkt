package gmbh.conteco.SpringSchulungOkt.rest;

import gmbh.conteco.SpringSchulungOkt.jpa.Student;
import gmbh.conteco.SpringSchulungOkt.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("all")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }
}
