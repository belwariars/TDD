package com.demo.tdd.repository;

import com.demo.tdd.domain.Car;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;


import java.util.Optional;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  Car findByName(String carName);

  @Query("select type from CarDB where name = :name")
  List<String> findByNameCars(@Param("name") String name);


}
