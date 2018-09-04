package com.demo.tdd.controller;

import com.demo.tdd.exception.CarNotFoundException;
import com.demo.tdd.service.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

  @Autowired
  private CarService carService;

  /*@Autowired
  public CarController(CarService carService) {
    this.carService = carService;
  }*/

  @GetMapping("/cars/{name}")
  private ResponseEntity<String> getCart(@PathVariable("name") String carName)
      throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString(carService.getCarssDetails(carName));

    // JSONObject json = new JSONObject(carService.getCarssDetails(carName));
    // return JSONObject.quote(carService.getCarssDetails(carName));

    return new ResponseEntity<>(jsonString, HttpStatus.OK);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  private void carNotFoundHandler(CarNotFoundException ex) {
  }


}
