package gmbh.conteco.SpringSchulungOkt.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gmbh.conteco.SpringSchulungOkt.jpa.Student;
import gmbh.conteco.SpringSchulungOkt.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    StudentService studentService;

    Student s1 = new Student().setName("Hanna").setEmail("h@mail.de");

    @BeforeEach
    void setUp() {
        Mockito.when(studentService.saveStudent(Mockito.any()))
                .thenReturn(s1);
    }

    @Test
    void saveStudent() throws Exception {
        String json = objectMapper.writeValueAsString(s1);

        MockHttpServletRequestBuilder post =
                MockMvcRequestBuilders.post("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(post)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(s1.getName()))
                .andExpect(jsonPath("$.email").value(s1.getEmail()));
    }
}