package com.demo.tdd.repository;

import com.demo.tdd.TddApplication;
import com.demo.tdd.domain.Car;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
//By default, @DataJpaTest will configure an in-memory embedded database,
// scan for @Entity classes and configure Spring Data JPA repositories.
// It is also transactional and rollback at the end of each test
public class CarRepositoryTest {

    @Autowired
    private CarRepository repository;

    //We can also inject a TestEntityManager bean specifically designed for
    // tests which is an alternative to the JPA EntityManager.


    @Test
    public void findByName() {


        // if were to use jpa save method, then you are just testing on the cache but not the actual persistence
        // return list of types with name = priyus and test
      List<Car> car = repository.findByName("prius");
      Assertions.assertThat(car.size()).isGreaterThan(1);

    //    Assertions.assertThat(car.getName()).isEqualTo("prius");
    }


}