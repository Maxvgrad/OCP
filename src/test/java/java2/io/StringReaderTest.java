package java2.io;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringReaderTest {

    private StringReader reader = new StringReader("Hello world");

    @Test
    void read() throws Exception {

        char letter = (char) reader.read();
        assertEquals('H', letter);
    }
}
