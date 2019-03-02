package org.ocp.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CopyOnWriteArrayListTest {

    private CopyOnWriteArrayList<Integer> copyOnWriteArrayList;

    @BeforeEach
    void setUp() {
        copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    }

    @Test
    void add() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        copyOnWriteArrayList.addAll(list);
        assertEquals(3, copyOnWriteArrayList.size());

        for (Integer elem: copyOnWriteArrayList) {
            copyOnWriteArrayList.add(4);
        }
        assertEquals(6, copyOnWriteArrayList.size());
    }



}
