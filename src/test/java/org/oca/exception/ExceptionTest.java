package org.oca.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionTest {


    @Test
    void reassignException() {

        try {
            throw new ParentException();
        } catch (ParentException ex) {
            assertEquals(ParentException.class, ex.getClass());
            //ex = new Exception();
            ex = new ChildException();
            assertEquals(ChildException.class, ex.getClass());
            //ex = new RuntimeException();
        }

    }

    @Test
    void reassignExceptionMultiCatch() {

        try {
            throw new ParentException();
        } catch (ParentException | RuntimeException ex) {
            assertEquals(ParentException.class, ex.getClass());
        }

    }


    private class ParentException extends Exception {}

    private class ChildException extends ParentException {}
}
