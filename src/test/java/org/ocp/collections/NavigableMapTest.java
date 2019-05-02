package org.ocp.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ocp.dto.PersonDto;
import org.ocp.testutils.TestUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NavigableMapTest {

    private static final int INIT_MAP_SIZE = 10;
    private static final int INDEX_GREATER_THEN_MAP_SIZE = 14;

    private NavigableMap<Integer, PersonDto> navigableMap;

    @BeforeEach
    void init() {
        navigableMap = new TreeMap<>();

        for (int i = 0; i < INIT_MAP_SIZE; i++) {
            PersonDto personDto = TestUtils.buildPerson();
            navigableMap.put(personDto.getId(), personDto);
        }

    }

    @Test
    void checkClassesNavigableMapExtending() {

        // NavigableMap -> SortedMap -> Map

        SortedMap<Integer, PersonDto> sortedMap = navigableMap;
        Map<Integer, PersonDto> map = sortedMap;
        assertEquals(INIT_MAP_SIZE, map.size());
    }

    @Test
    void checkClassesImplementingNavigableMap() {
        navigableMap = new TreeMap<>();
        navigableMap = Collections.checkedNavigableMap(navigableMap, Integer.class, PersonDto.class);
    }

    //ceilingEntry

    @Test
    void ceilingEntryLeastGrater() {
        //given
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "10");
        map.put(11, "11");
        //when
        Entry<Integer, String> ceilingEntry = map.ceilingEntry(9);
        //then
        assertEquals("10", ceilingEntry.getValue());
    }

    @Test
    void ceilingEntryKeqEquals() {
        //given
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "10");
        map.put(11, "11");
        //when
        Entry<Integer, String> ceilingEntry = map.ceilingEntry(10);
        //then
        assertEquals("10", ceilingEntry.getValue());
    }

    @Test
    void ceilingEntryNoGratedOrEqualsKey() {
        //given
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "10");
        map.put(11, "11");
        //when
        Entry<Integer, String> ceilingEntry = map.ceilingEntry(12);
        //then
        assertNull(ceilingEntry);
    }

    //higher (strictly grater)

    @Test
    void higherEntryLeastGreatestEntry() {
        //given
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "10");
        map.put(11, "11");
        //when
        Entry<Integer, String> ceilingEntry = map.higherEntry(10);
        //then
        assertEquals("11", ceilingEntry.getValue());
    }

    @Test
    void higherEntryLeastNoGreaterKey() {
        //given
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "10");
        map.put(11, "11");
        //when
        Entry<Integer, String> ceilingEntry = map.higherEntry(11);
        //then
        assertNull(ceilingEntry);
    }

    //lowerEntry

    @Test
    void lowerEntryGreatestKeyLessThenSpecifiedKeyArg() {
        //given
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "10");
        map.put(11, "11");
        //when
        Entry<Integer, String> ceilingEntry = map.lowerEntry(11);
        //then
        assertEquals("10", ceilingEntry.getValue());
    }

    @Test
    void lowerEntryGreatestKeyLessThenSpecifiedKeyArgIsNull() {
        //given
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "10");
        map.put(11, "11");
        //when
        Entry<Integer, String> ceilingEntry = map.lowerEntry(10);
        //then
        assertNull(ceilingEntry);
    }

    // floorEntry

    @Test
    void floorEntryKeyEquals() {
        //given
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "10");
        map.put(11, "11");
        //when
        Entry<Integer, String> ceilingEntry = map.floorEntry(10);
        //then
        assertEquals("10", ceilingEntry.getValue());
    }

    @Test
    void floorEntryTheGreatestKeyLessThenSpecifiedArg() {
        //given
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "10");
        map.put(11, "11");
        //when
        Entry<Integer, String> ceilingEntry = map.floorEntry(12);
        //then
        assertEquals("11", ceilingEntry.getValue());
    }

    @Test
    void floorEntryIsNull() {
        //given
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "10");
        map.put(11, "11");
        //when
        Entry<Integer, String> ceilingEntry = map.floorEntry(9);
        //then
        assertNull(ceilingEntry);
    }
}
