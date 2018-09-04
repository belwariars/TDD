package com.demo.tdd;

import com.demo.tdd.domain.Car;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;
import static java.lang.System.out;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;

@RunWith(SpringRunner.class) // same as JUNIT
@SpringBootTest(webEnvironment = RANDOM_PORT) // for integration testing we use this
public class TddApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate; // with @springboottest, we get TestRestTemplate

	@Test
	public void getCar_returnsCarDetails() throws Exception {
		// arrange

		// act
		ResponseEntity<List<Car>> response = restTemplate.exchange(
				"http://localhost:8081/cars/prius",
				HttpMethod.GET,
				null,new ParameterizedTypeReference<List<Car>>(){});

		List<Car> cars = response.getBody();

		Assertions.assertThat(cars.size()).isGreaterThan(1);

		//Assertions.assertThat(string1,string2);

		Assertions.assertThat((cars.get(0)).getType()).isEqualTo("hybrid");
		Assertions.assertThat((cars.get(1)).getType()).isEqualTo("hybrid1");
		Assertions.assertThat((cars.get(2)).getType()).isEqualTo("hybrid2");





		//Using assertJ liberary which comes with @springboottest assertThat is one of the JUnit methods from the Assert object that can be used to check if a specific value match to an expected one
		// assert
		//assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		//assertThat(response.getBody().getName()).isEqualTo("prius");
		//assertThat(response.getBody().getType()).isEqualTo("hybrid");

	}

}
