package com.demo.tdd.repository;

import com.demo.tdd.domain.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

  //Car findByName(String carName);

  @Query(
      value = "select * from car where name = :name"
      , nativeQuery = true)
  List<Car> findByName(@Param("name") String name);

  @Override
  List<Car> findAll();
}
