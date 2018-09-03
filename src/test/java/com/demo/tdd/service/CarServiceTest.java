package com.demo.tdd.service;

import com.demo.tdd.exception.CarNotFoundException;
import com.demo.tdd.domain.Car;
import com.demo.tdd.repository.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class) // not using springRunner, so springboot will not start
public class CarServiceTest {

    @Mock // it is different from @MockBean as there is no spring involved in @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Before
    public void setUp() throws Exception {
        carService = new CarService(carRepository);
    }

    @Test
    public void getCarDetails_returnCarInfo() {
       given(carRepository.findByName("prius")).willReturn( new Car("prius","hybrid"));

       Car car = carService.getCarDetails("prius");

       assertThat(car.getName()).isEqualTo("prius");
       assertThat(car.getType()).isEqualTo("hybrid");
    }

    @Test(expected = CarNotFoundException.class) // have to write in cheatsheet if remove under bracket, will start throwing exception and giving error
    public void getCarDetails_whenCarNotFound() throws Exception{
        given(carRepository.findByName("prius")).willReturn(null);

        Car car = carService.getCarDetails("prius");
      Assertions.assertThat(car).isEqualTo(null);
    }

}
