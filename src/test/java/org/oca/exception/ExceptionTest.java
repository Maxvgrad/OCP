package org.oca.exception;

import org.junit.jupiter.api.Test;

import java.io.IOException;

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

    @Test
    void waitMethodOfObject() {
        try {
            new Object().wait();
        } catch (InterruptedException ex) {

        }
    }

    @Test
    void tryCatch() {
        StringBuilder accumulator = new StringBuilder();
        try (Device d = new Device(accumulator)) {
            d.open();
            d.read();
            d.writeHeader("TEST");
        } catch (IOException e) {
            accumulator.append("#catch");
        }
        assertEquals("#open#read#close#catch", accumulator.toString());
    }

    @Test
    void tryCatchFinally() {
        StringBuilder accumulator = new StringBuilder();

        try (Device d = new Device(accumulator)) {
            d.open();
            d.read();
            d.writeHeader("TEST");
        } catch (IOException e) {
            accumulator.append("#catch");
        } finally {
            accumulator.append("#finally");
        }
        assertEquals("#open#read#close#catch#finally", accumulator.toString());
    }

    @Test
    void tryCatchFinally2() {
        StringBuilder accumulator = new StringBuilder();

        try (Device d = new Device(accumulator)) {
            d.open();
            d.writeHeader("TEST");
        } catch (IOException e) {
            accumulator.append("#catch");
        } finally {
            accumulator.append("#finally");
        }
        assertEquals("#open#writeHeader#close#finally", accumulator.toString());
    }

    private class ParentException extends Exception {}

    private class ChildException extends ParentException {}

    public class Device implements AutoCloseable {
        String header = null;

        private StringBuilder sb;

        public Device(StringBuilder sb) {
            this.sb = sb;
        }

        public void open() {
            sb.append("#open");
        }

        public String read() throws IOException {
            sb.append("#read");
            throw new IOException("Unknown");
        }

        public void writeHeader(String str) throws IOException {
            sb.append("#writeHeader");
        }

        public void close() {
            sb.append("#close");
        }
    }
}
