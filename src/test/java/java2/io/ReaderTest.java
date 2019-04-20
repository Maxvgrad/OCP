package java2.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;

class ReaderTest {

    private BufferedInputStream reader = new BufferedInputStream(System.in);

    @AfterEach
    void tearDown() throws Exception {
        reader.close();
    }

    @Test
    void read() throws Exception {
        reader.read();
    }
}
