package com.demo.tdd.controller;

import com.demo.tdd.domain.Car;
import com.demo.tdd.exception.CarNotFoundException;
import com.demo.tdd.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)// Will just wireup things needed for mvc test. If many controllers, we can specify their controller.class also
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    // integration testing(TddApplicationTests) will start spring boot application but mockmvc will just test controller

    @MockBean
    private CarService carService; /* write that in carController you need @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    } */

    @Test
    public void getCar_ShouldReturnCar() throws Exception {

        // even if we didn't had CarService class, it would still test controller as it mocks
        given(carService.getCarDetails(anyString())).willReturn(new Car("prius","hybrid"));

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/prius"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("prius"))
                .andExpect(jsonPath("type").value("hybrid"));

    }

    @Test
    public void getCar_notFound() throws Exception{
        given(carService.getCarDetails(anyString())).willThrow(new CarNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/toyota")).andExpect(status().isNotFound());

    }

}