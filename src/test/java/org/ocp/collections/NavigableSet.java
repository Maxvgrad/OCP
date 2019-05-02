package org.ocp.collections;

import org.junit.jupiter.api.Test;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NavigableSet {

    @Test
    void keyOutOfRange() {

        TreeSet<Integer> s = new TreeSet<>();
        TreeSet<Integer> subs;

        for (int i = 324; i <= 328; i++) {
            s.add(i);
        }

        subs = (TreeSet) s.subSet(326, true, 328, true);
        assertThrows(IllegalArgumentException.class, () -> subs.add(329));
    }

}
