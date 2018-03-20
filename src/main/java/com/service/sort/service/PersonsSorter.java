package com.service.sort.service;

import com.service.sort.entities.Person;

import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class PersonsSorter {

    private static final Map<String, Comparator<Person>> COMPARATOR_MAP = new HashMap<>();
    private static final String FALSE = "false";
    private static final String TRUE = "true";
    private static final String KEY_DELIMITER = "#";
    private static final String SSN = "ssn";
    private static final String DATEOFBIRTH = "dateofbirth";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String HEIGHTIN = "heightin";
    private static final String WEIGHTLB = "weightlb";
    private static final String DEFAULT_SORT_KEY = SSN + KEY_DELIMITER + TRUE;

    static {
        COMPARATOR_MAP.put(DEFAULT_SORT_KEY, Comparator.comparing(Person::getSsn));
        COMPARATOR_MAP.put(SSN + KEY_DELIMITER + FALSE, Comparator.comparing(Person::getSsn).reversed());
        COMPARATOR_MAP.put(DATEOFBIRTH + KEY_DELIMITER + TRUE, Comparator.comparing(Person::getDateOfBirth));
        COMPARATOR_MAP.put(DATEOFBIRTH + KEY_DELIMITER + FALSE, Comparator.comparing(Person::getDateOfBirth).reversed());
        COMPARATOR_MAP.put(FIRSTNAME + KEY_DELIMITER + TRUE, Comparator.comparing(Person::getFirstName));
        COMPARATOR_MAP.put(FIRSTNAME + KEY_DELIMITER + FALSE, Comparator.comparing(Person::getFirstName).reversed());
        COMPARATOR_MAP.put(LASTNAME + KEY_DELIMITER + TRUE, Comparator.comparing(Person::getLastName));
        COMPARATOR_MAP.put(LASTNAME + KEY_DELIMITER + FALSE, Comparator.comparing(Person::getLastName).reversed());
        COMPARATOR_MAP.put(HEIGHTIN + KEY_DELIMITER + TRUE, Comparator.comparing(Person::getHeightIn));
        COMPARATOR_MAP.put(HEIGHTIN + KEY_DELIMITER + FALSE, Comparator.comparing(Person::getHeightIn).reversed());
        COMPARATOR_MAP.put(WEIGHTLB + KEY_DELIMITER + TRUE, Comparator.comparing(Person::getWeightLb));
        COMPARATOR_MAP.put(WEIGHTLB + KEY_DELIMITER + FALSE, Comparator.comparing(Person::getWeightLb).reversed());
    }

    private PersonsSorter() {

    }


    public static List<Person> sort(Iterable<Person> people, String sortField, String ascending) {

        if (people == null) {
            return new ArrayList<>();
        }

        Comparator comparator = COMPARATOR_MAP.get((sortField + KEY_DELIMITER + ascending).toLowerCase());
        if (comparator == null) {
            comparator = COMPARATOR_MAP.get(DEFAULT_SORT_KEY);
        }

        List<Person> personList;

        personList = StreamSupport.stream(people.spliterator(), false).collect(toList());
        personList.sort(comparator);

        return personList;
    }
}
