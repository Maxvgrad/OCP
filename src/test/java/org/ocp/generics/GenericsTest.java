package org.ocp.generics;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

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
    }

    private void processNumbers(List<Number> numbers) {}

    private void processSuperNumbers(List<? extends Number> numbers) {}
}
