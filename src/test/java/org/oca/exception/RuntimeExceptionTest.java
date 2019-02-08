package org.oca.exception;

import org.junit.jupiter.api.Test;

import java.util.MissingResourceException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RuntimeExceptionTest {


    @Test
    void missingResourceExceptionIsRuntime() throws Exception {
        assertRuntimeException(new MissingResourceException(null, null, null));
    }

    private void assertRuntimeException(Exception exception) throws Exception {
        try {
            throw exception;
        } catch (RuntimeException ex) {
            assertNotNull(ex);
        }
    }





}
