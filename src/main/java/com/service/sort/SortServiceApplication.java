package com.service.sort;


import com.service.sort.dao.PersonRepository;
import com.service.sort.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import java.util.stream.Stream;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.service.sort.dao"})
public class SortServiceApplication {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SortServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner getRunner(PersonRepository personRepository) {
        return strings -> {
            Stream.of(new Person("999999999", new Date(), "Satyanandana", "Vadapalli", 5.8, 120.05),
                    new Person("888888888", new Date(), "Bhavya", "Sri", 3.2, 28.05),
                    new Person("777777777", new Date(), "Nandhu", "Sagiraju", 5.8, 120.05),
                    new Person("666666666", new Date(), "Harsha", "Puli", 5.7, 140.05),
                    new Person("555555555", new Date(), "Raja", "Reddy", 5.10, 130.05)).forEach(item -> personRepository.save(item));
        };
    }


}
