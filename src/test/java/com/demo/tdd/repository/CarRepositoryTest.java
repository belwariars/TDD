package com.demo.tdd.repository;

import com.demo.tdd.domain.Car;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByName() {

        // This will get the test entity manager to insert the object to the database
        // and get recreate it.
        // if were to use jpa save method, then you are just testing on the cache but not the actual persistence
        // return list of types with name = priyus and test
        Car car = repository.findByName("prius");

        Assertions.assertThat(car.getName()).isEqualTo("prius");
    }

    @Test
    public void findByName2() {
        Car car1 = new Car("prius", "hybrid1");
        entityManager.persist(car1);

        Car car2 = new Car("prius", "hybrid2");
        entityManager.persist(car2);

       // Car car = repository.findByName("prius");

       // Assertions.assertThat(car.getName()).isEqualTo("prius1");

        List<String> cars = repository.findByNameCars("prius");


        System.out.println(cars.size());

        Assertions.assertThat(cars.size()).isGreaterThan(1);

    }
}