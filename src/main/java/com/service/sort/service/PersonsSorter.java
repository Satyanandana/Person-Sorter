package com.service.sort.service;

import com.service.sort.entities.Person;

import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class PersonsSorter {

    private static final Map<String, Comparator<Person>> comparatorMap = new HashMap<>();
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
        comparatorMap.put(DEFAULT_SORT_KEY, Comparator.comparing(Person::getSsn));
        comparatorMap.put(SSN + KEY_DELIMITER + FALSE, Comparator.comparing(Person::getSsn).reversed());
        comparatorMap.put(DATEOFBIRTH + KEY_DELIMITER + TRUE, Comparator.comparing(Person::getDateOfBirth));
        comparatorMap.put(DATEOFBIRTH + KEY_DELIMITER + FALSE, Comparator.comparing(Person::getDateOfBirth).reversed());
        comparatorMap.put(FIRSTNAME + KEY_DELIMITER + TRUE, Comparator.comparing(Person::getFirstName));
        comparatorMap.put(FIRSTNAME + KEY_DELIMITER + FALSE, Comparator.comparing(Person::getFirstName).reversed());
        comparatorMap.put(LASTNAME + KEY_DELIMITER + TRUE, Comparator.comparing(Person::getLastName));
        comparatorMap.put(LASTNAME + KEY_DELIMITER + FALSE, Comparator.comparing(Person::getLastName).reversed());
        comparatorMap.put(HEIGHTIN + KEY_DELIMITER + TRUE, Comparator.comparing(Person::getHeightIn));
        comparatorMap.put(HEIGHTIN + KEY_DELIMITER + FALSE, Comparator.comparing(Person::getHeightIn).reversed());
        comparatorMap.put(WEIGHTLB + KEY_DELIMITER + TRUE, Comparator.comparing(Person::getWeightLb));
        comparatorMap.put(WEIGHTLB + KEY_DELIMITER + FALSE, Comparator.comparing(Person::getWeightLb).reversed());
    }

    private PersonsSorter() {

    }


    public static List<Person> sort(Iterable<Person> people, String sortField, String ascending) {
        List<Person> personList = new ArrayList<>();
        if (people == null) {
            return personList;
        }

        Comparator comparator = comparatorMap.get((sortField + KEY_DELIMITER + ascending).toLowerCase());
        if (comparator == null) {
            comparator = comparatorMap.get(DEFAULT_SORT_KEY);
        }

        personList = StreamSupport.stream(people.spliterator(), false).collect(toList());
        personList.sort(comparator);

        return personList;
    }
}
