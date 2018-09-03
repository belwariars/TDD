package com.demo.tdd.service;

import com.demo.tdd.domain.Car;
import com.demo.tdd.exception.CarNotFoundException;
import com.demo.tdd.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CarService {


  private CarRepository carRepository;

  @Autowired
  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }


  //The getCarDetails() call will first check the cache "cars" before actually invoking the method and then caching the result.
  // It caches the result
  @Cacheable("cars")
  public Car getCarDetails(String carName) {
    Car car = carRepository.findByName(carName);
    if (car == null) {
      throw new CarNotFoundException();
    }
    return car;
  }

  public List<String> getCarssDetails(String carName) {
    List<String> car = carRepository.findByNameCars(carName);
    if (car == null) {
      throw new CarNotFoundException();
    }
    return car;
  }


}
