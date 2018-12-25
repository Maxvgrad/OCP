package org.ocp.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ocp.dto.PersonDto;
import org.ocp.utils.TestUtils;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeSetTest {

    private Set<PersonDto> persons;

    @BeforeEach
    void init() {
        persons = new TreeSet<>(Comparator.comparing(PersonDto::getInterest));
    }

    @Test
    void addTest() {
        persons.add(TestUtils.buildPerson());
        assertEquals(1, persons.size());
    }

}