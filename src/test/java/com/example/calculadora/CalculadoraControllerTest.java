package com.example.calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = CalculadoraController.class)
class CalculadoraControllerTest {


    @Autowired
    private CalculadoraController calculadoraController;

    @Autowired
    private MockMvc mockmvc;

    private String n1;
    private String n2;


    @BeforeEach
    public void setUp() {
        standaloneSetup(this.calculadoraController);

    }

    @Test
    void sum() throws Exception {

        n1 = "5";
        n2 = "2";
        double expected = Long.parseLong(n1) + Long.parseLong(n2);

        mockmvc.perform(MockMvcRequestBuilders
                        .get("/sum/{numberOne}/{numberTwo}", n1, n2)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }

    @Test
    void divide() throws Exception {

        n1 = "5";
        n2 = "2";
        double expected = Double.parseDouble(n1) / Double.parseDouble(n2);

        mockmvc.perform(MockMvcRequestBuilders
                        .get("/divide/{numberOne}/{numberTwo}", n1, n2)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }

    @Test
    void multiplication() throws Exception {
        n1 = "5";
        n2 = "2";
        double expected = Double.parseDouble(n1) * Double.parseDouble(n2);

        mockmvc.perform(MockMvcRequestBuilders
                        .get("/multiplication/{numberOne}/{numberTwo}", n1, n2)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));

    }

    @Test
    void subtraction() throws Exception {

        n1 = "5";
        n2 = "2";
        double expected = Long.parseLong(n1) - Long.parseLong(n2);

        mockmvc.perform(MockMvcRequestBuilders
                        .get("/subtraction/{numberOne}/{numberTwo}", n1, n2)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));

    }

    @Test
    void source() throws Exception {

        n1 = "5";
        n2 = "2";
        double expected = Math.sqrt(Double.parseDouble(n1));

        mockmvc.perform(MockMvcRequestBuilders
                        .get("/source/{numberOne}", n1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));

    }

    @Test
    void unsuccessfullSource() throws Exception {

        n1 = "a";

        mockmvc.perform(MockMvcRequestBuilders
                        .get("/source/{numberOne}", n1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedOperationException))
                .andExpect(result -> assertEquals("Please set a numeric value!", result.getResolvedException().getMessage()));;


    }

    @Test
    void unsuccessfullSum() throws Exception {

        n1 = "a";
        n2 = "b";

        mockmvc.perform(MockMvcRequestBuilders
                        .get("/sum/{numberOne}/{numberTwo}", n1 , n2)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedOperationException))
                .andExpect(result -> assertEquals("Please set a numeric value!", result.getResolvedException().getMessage()));;


    }


    @Test
    void unsuccessfullDivide() throws Exception {

        n1 = "a";
        n2 = "b";

        mockmvc.perform(MockMvcRequestBuilders
                        .get("/divide/{numberOne}/{numberTwo}", n1 , n2)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedOperationException))
                .andExpect(result -> assertEquals("Please set a numeric value!", result.getResolvedException().getMessage()));;


    }

    @Test
    void unsuccessfullMultiplication() throws Exception {

        n1 = "a";
        n2 = "b";

        mockmvc.perform(MockMvcRequestBuilders
                        .get("/multiplication/{numberOne}/{numberTwo}", n1 , n2)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedOperationException))
                .andExpect(result -> assertEquals("Please set a numeric value!", result.getResolvedException().getMessage()));;


    }

    @Test
    void unsuccessfullSubtraction() throws Exception {

        n1 = "a";
        n2 = "b";

        mockmvc.perform(MockMvcRequestBuilders
                        .get("/subtraction/{numberOne}/{numberTwo}", n1 , n2)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnsupportedOperationException))
                .andExpect(result -> assertEquals("Please set a numeric value!", result.getResolvedException().getMessage()));;


    }
    @Test
    void isNumeric() {
        boolean numeric = calculadoraController.isNumeric("1");
        assertTrue(numeric);


    }
    @Test
    void unsuccessfullIsNumeric() {
        boolean numeric = calculadoraController.isNumeric("a");
        assertFalse(numeric);


    }
}