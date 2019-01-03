package org.ocp.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ocp.dto.PersonDto;
import org.ocp.utils.TestUtils;

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
    @SuppressWarnings("unchecked")
    void init() throws Exception {
        navigableMap = populateWithPerson(TreeMap.class);
    }

    @Test
    void checkClassesNavigableMapExtending() {
        SortedMap<Integer, PersonDto> sortedMap = navigableMap;
        Map<Integer, PersonDto> map = sortedMap;
        assertEquals(INIT_MAP_SIZE, map.size());
    }

    @Test
    void checkClassesImplementingNavigableMap() {
        navigableMap = new TreeMap<>();
        navigableMap = Collections.checkedNavigableMap(navigableMap, Integer.class, PersonDto.class);
    }

    @Test
    void checkCeilingEntry() {
        navigableMap.remove(9);
        Entry<Integer, PersonDto> ceilingEntry = navigableMap.ceilingEntry(9);
        assertEquals(INIT_MAP_SIZE, ceilingEntry.getKey().intValue());
    }

    @Test
    void checkFirstEntry() {
        Entry<Integer, PersonDto> firstEntry = navigableMap.firstEntry();
        assertEquals(1, firstEntry.getKey().intValue());
    }

    @Test
    void checkFloorEntry() {
        Entry<Integer, PersonDto> foundEntry = navigableMap.floorEntry(INDEX_GREATER_THEN_MAP_SIZE);
        assertEquals(INIT_MAP_SIZE, foundEntry.getKey().intValue());
    }

    @Test
    void checkHigherEntryOutOfBound() {
        Entry<Integer, PersonDto> foundEntry = navigableMap.higherEntry(INDEX_GREATER_THEN_MAP_SIZE);
        assertNull(foundEntry);
    }


    @Test
    void checkHigherEntry() {
        Entry<Integer, PersonDto> foundEntry = navigableMap.higherEntry(9);
        assertEquals(INIT_MAP_SIZE, foundEntry.getKey().intValue());
    }

    @Test
    void checkLowerEntry() {
        Entry<Integer, PersonDto> foundEntry = navigableMap.lowerEntry(INDEX_GREATER_THEN_MAP_SIZE);
        assertEquals(INIT_MAP_SIZE, foundEntry.getKey().intValue());
    }


    private <T extends NavigableMap<Integer, PersonDto>> NavigableMap<Integer, PersonDto> populateWithPerson(Class<T> mapClass) throws Exception {
        T result = mapClass.newInstance();

        for (int i = 0; i < INIT_MAP_SIZE; i++) {
            PersonDto personDto = TestUtils.buildPerson();
            result.put(personDto.getId(), personDto);
        }

        return result;
    }
}
