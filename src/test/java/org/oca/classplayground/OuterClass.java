package org.oca.classplayground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OuterClass {

    @Test
    void getInnerClassPrivateField() {
        Node node = new Node();
        assertEquals(0, node.hash);
    }

    private class Node {
        private int hash;
    }
}
