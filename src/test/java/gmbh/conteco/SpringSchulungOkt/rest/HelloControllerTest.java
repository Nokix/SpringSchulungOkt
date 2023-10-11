package gmbh.conteco.SpringSchulungOkt.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloController.class)
//@ComponentScan
class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RandomNameGenerator generator;

    @Autowired
    HelloController helloController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRandom2() throws Exception{
        String name1 = "Ulrike Degenhardt";
        String name2 = "Steffen Schöwitz";
        String name3 = "Christoph Heyder";
        Mockito.when(generator.getName())
                .thenReturn(name1).thenReturn(name2).thenReturn(name3);

        String expectedJson = objectMapper.writeValueAsString(
                List.of(name1, name2, name3));

        MockHttpServletRequestBuilder getRequest =
                MockMvcRequestBuilders.get("/random")
                        .param("n", "3");

        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testRandom1() throws Exception {
        String name = "Ulrike Degenhardt";
        Mockito.when(generator.getName()).thenReturn(name);

        MockHttpServletRequestBuilder getRequest =
                MockMvcRequestBuilders.get("/random");

        String expectedJson = objectMapper.writeValueAsString(List.of(name));
        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testSayHello() throws Exception {
        MockHttpServletRequestBuilder getRequest =
                MockMvcRequestBuilders.get("/hallo");

        mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("Hallo"));
    }
}