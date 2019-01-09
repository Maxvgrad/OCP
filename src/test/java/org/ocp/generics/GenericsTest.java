package org.ocp.generics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GenericsTest {

    @Test
    @Tag("ch3")
    void incompatibleTypesTest() {
        List list = new ArrayList();
        list.add("one");
        list.add("two");
        list.add(1);

        //for (String item : list) //not compile
        //System.out.println(item);
    }

    @Test
    @Tag("ch3")
    void compilationTest() {
        //HashSet<Number> hashSet = new HashSet<Integer>(); //incompatibleTypes
        //HashSet<? extends ClassCastException> set = new HashSet<Exception>();
        List<String> list = new Vector<String>();
        //List<Object> values = new HashSet<Object>();
        //List<Object> objects = new ArrayList<? extends Object>(); //wildcard can not be instantiated directly

        //Upper Bound
        Map<String, ? extends Number> hashMap = new HashMap<String, Integer>();
    }

    @Test
    @Tag("ch3")
    void rawTypeTest() {
        List<String> stringBox = new ArrayList<>();
        List rawBox = stringBox;
        rawBox.add(8);  // warning: unchecked invocation to set(T)
        assertEquals(1, stringBox.size());
        assertThrows(ClassCastException.class, () -> stringBox.get(0).toLowerCase());
    }

    @Test
    @Tag("ch3")
    void inheritanceTest() {
        //processNumbers(new ArrayList<Integer>());
        processSuperNumbers(new ArrayList<Integer>());
        processNumbers(Collections.emptyList());
    }

    private void processNumbers(List<Number> numbers) {}

    private void processSuperNumbers(List<? extends Number> numbers) {}

    @Test
    @Tag("ch3")
    @DisplayName("Unbounded Wildcards")
    void unboundedWildCard() {
        List<?> list = new ArrayList<>();
        //list.add(new Object());
        list.add(null);
        assertEquals(1, list.size());
    }

    @Test
    @Tag("ch3")
    @DisplayName("Lower Bound")
    void lowerBoundTest() {
        List<? super Integer> list = new ArrayList<>();
        list.add(3);
        list.add(null);
        //list.add(new Object());
        assertEquals(2, list.size());
    }

    @Test
    @Tag("ch3")
    @DisplayName("Lower Bound")
    void lowerBoundMethodArgTest() {
        List<Object> listOfObjects = Stream.of(1, 2, 4, 5).collect(Collectors.toList());
        lowerBoundListProcessing(listOfObjects);
        assertEquals(5, listOfObjects.size());
    }

    private void lowerBoundListProcessing(List<? super Integer> list) {
        list.add(3);
    }

    @Test
    @Tag("ch3")
    @DisplayName("Lower Bound")
    void wildCardAndSubtypingTest() {
        List<Integer> intExactTypeList = Stream.of(1, 2, 4, 5).collect(Collectors.toList());
        List<? extends Integer> intUpperBoundList = Stream.of(1, 2, 4, 5).collect(Collectors.toList());
        List<? extends Number> numberList = intUpperBoundList;
        numberList = intExactTypeList;
        assertEquals(4, numberList.size());
        assertEquals(4, intUpperBoundList.size());
    }

    @Test
    @Tag("ch3")
    @DisplayName("WildCard capture")
    void wildCardCaptureTest() {

    }

    void wildCardError(List<?> list) {
        //list.set(0, list.get(0));
    }
}
