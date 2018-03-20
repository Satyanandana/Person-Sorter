package com.service.sort.controllers;

import com.service.sort.dao.PersonRepository;
import com.service.sort.entities.Person;
import com.service.sort.service.PersonsSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping(value = "sorter")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;


    @GetMapping(value = "/people")
    private Collection<Person> getUsers(@RequestParam(name = "sortField", defaultValue = "ssn") String sortField, @RequestParam(name = "ascending", defaultValue = "true") String ascending) {
        List<Person> persons = personRepository.findAll();
        return PersonsSorter.sort(persons, sortField, ascending);
    }
}
