package org.oca.classplayground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OuterClass {

    @Test
    void getInnerClassPrivateField() {
        Node node = new Node();
        assertEquals(0, node.hash);
    }

    @Test
    void createInnerClass() {
        OuterClass outerClass = new OuterClass();
        Node node = outerClass.new Node();

        new OuterClass().new Node();
    }

    private class Node {
        private int hash;
    }
}
