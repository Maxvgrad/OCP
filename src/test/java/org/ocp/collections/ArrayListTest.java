package org.ocp.collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ocp.dto.PersonDto;
import org.ocp.testutils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTest {

    private List<PersonDto> persons = new ArrayList<>();

    @Test
    @DisplayName("Here, no Comparator is supplied to the sorted() method. " +
                         "Therefore, at run time, " +
                         "when the sorted method tries to cast Book object to Comparable, it will fail.")
    void sortedExpectClassCastException() {
        persons.add(TestUtils.buildPerson());
        persons.add(TestUtils.buildPerson());
        assertThrows(ClassCastException.class,
                     () -> persons.stream().sorted().forEach(person -> System.out.println(person.getId())));
    }


    @Test
    @DisplayName("Ideally, when you add the first element," +
                         "since there is nothing to compare this element to, there should be no exception.")
    void sortedPersonIsNotComparableButSizeEqOne() {
        persons.add(TestUtils.buildPerson());
        persons.stream().sorted().forEach(person -> System.out.println(person.getId()));
    }
}
