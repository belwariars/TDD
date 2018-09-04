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
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class) // same as JUNIT
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CarServiceTest {

  @Autowired
  private CarRepository carRepository;

  @Mock
  private CarService carService;

  @Before
  public void setUp() throws Exception {
    carService = new CarService(carRepository);
  }

  @Test
  public void getCarDetails_returnCarInfo() {



    List<Car> car2 = carService.getCarssDetails("prius");

    Assertions.assertThat(car2.size()).isGreaterThan(1);

  }



}