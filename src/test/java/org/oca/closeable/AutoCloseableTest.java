package org.oca.closeable;

import org.junit.jupiter.api.Test;

import java.io.Closeable;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoCloseableTest {

    private StringBuilder accumulator = new StringBuilder();

    @Test
    void closeableCage() {

        try (CloseableCage cage = new CloseableCage()) {
            accumulator.append("Try");
            throw new IOException();
        } catch (IOException ex) {
            accumulator.append("Catch");
        } finally {
            accumulator.append("Finally");
        }

        assertEquals("TryCloseCatchFinally", accumulator.toString());
    }

    @Test
    void autoCloseableCage() {

        try (AutoCloseable cage = new AutoClosableCage()) {
            accumulator.append("Try");
            throw new IOException();
        } catch (Exception ex) {
            accumulator.append("Catch");
        } finally {
            accumulator.append("Finally");
        }

        assertEquals("TryACloseCatchFinally", accumulator.toString());
    }

    @Test
    void safeAutoCloseableCage() {

        try (SafeAutoClosableCage cage = new SafeAutoClosableCage()) {
            accumulator.append("Try");
        }

        assertEquals("TrySafeAClose", accumulator.toString());
    }

    @Test
    void superClassRefNotSafeAutoCloseableCage() {

        try (AutoClosableCage cage = new SafeAutoClosableCage()) {
            accumulator.append("Try");
        } catch (Exception ex) {

        }

        assertEquals("TrySafeAClose", accumulator.toString());
    }

    @Test
    void superClassRefNotSafeButThrowsAutoCloseableCage() throws Exception {

        try (AutoClosableCage cage = new SafeAutoClosableCage()) {
            accumulator.append("Try");
        }

        assertEquals("TrySafeAClose", accumulator.toString());
    }

    private class CloseableCage implements Closeable {
        @Override
        public void close() throws IOException {
            accumulator.append("Close");
        }
    }


    @Test
    void question5FlowControl() {

        try (AutoCloseable autoCage = new AutoClosableCage(); Closeable cage = new CloseableCage()) {
            accumulator.append("Try");
            throw new IOException();
        } catch (IOException ex) {
            accumulator.append("CatchIOException");
        } catch (Exception ex) {
            accumulator.append("CatchException");
        } finally {
            accumulator.append("Finally");
        }

        assertEquals("TryCloseACloseCatchIOExceptionFinally", accumulator.toString());
    }

    private class AutoClosableCage implements AutoCloseable {
        @Override
        public void close() throws Exception {
            accumulator.append("AClose");
        }
    }

    private class SafeAutoClosableCage extends AutoClosableCage {
        @Override
        public void close() {
            accumulator.append("SafeAClose");
        }
    }

}
