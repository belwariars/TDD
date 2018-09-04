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

  @Autowired
  private CarRepository carRepository;

  @Autowired
  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }


  //The getCarDetails() call will first check the cache "cars" before actually invoking the method and then caching the result.
  // It caches the result
 /* @Cacheable("cars")
  public Car getCarDetails(String carName) {
    Car car = carRepository.findByName(carName);
    if (car == null) {
      throw new CarNotFoundException();
    }
    return car;
  } */

  @Cacheable("cars")
  public List<Car> getCarssDetails(String carName) {
    List<Car> car = carRepository.findByName(carName);
    if (car.size() == 0) {
      throw new CarNotFoundException();
    }
    return car;
  }


}
