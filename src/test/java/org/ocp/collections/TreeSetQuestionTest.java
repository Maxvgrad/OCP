package org.ocp.collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TreeSetQuestionTest {


    private static TreeSetQuestion treeSetQuestion;

    @BeforeAll
    static void init() {
        treeSetQuestion = new TreeSetQuestion();
    }


    /**
     * Note that TreeSet is an ordered set that keeps its elements in a sorted fashion.
     * When you call the add() method, it immediately compares the element to be added to the existing
     * elements and puts the new element in its appropriate place.
     * Thus, the foremost requirement of a TreeSet is that the elements must either implement Comparable interface
     * (which has the compareTo(Object) method) and they must also be mutually
     * comparable or the TreeSet must be created with by passing a Comparator
     * (which has a compare(Object, Object)) method). For example, you might have
     * two classes \\\A\\\ and \\\B\\\ both implementing Comparable interface.
     * But if their compareTo() method does not work with both the types, you cannot add both type of
     * elements in the same TreeSet.  In this question, Person class does not implement
     * Comparable interface. Ideally, when you add the first element,
     * since there is nothing to compare this element to, there should be no exception.
     * But when you add the second element, TreeSet tries to compare it with the existing element, thereby throwing
     * ClassCastException because they don't implement Comparable interface.
     * However, this behavior was changed in the TreeSet implementation recently and it throws a
     * ClassCastException when you add the first element itself.
     * The compiler knows nothing about this requirement of TreeSet since it is an application
     * level requirement and not a language level requirement. So the program compiles fine without any warning.
     * */
    @Test
    @Tag("ch3")
    void addPersonNotImplementComparable() {
        assertThrows(ClassCastException.class, () -> treeSetQuestion.add(new Person("MATH")));
    }

    @Test
    @DisplayName("#ceiling(): Returns the least key greater than or equal to the given key")
    @Tag("ch3")
    void ceilingTest() {
        TreeSet<String> tree = new TreeSet<>();
        tree.add("one");
        tree.add("One");
        tree.add("ONE");
        assertEquals("One", tree.ceiling("One"));
    }

}