package java2.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.nio.file.Files;

class BufferedWriterTest {

    private BufferedWriter bufferedWriter;

    @BeforeEach
    void setUp() throws Exception {
        bufferedWriter = Files.newBufferedWriter(Files.createTempFile("tmp", "tmp"));
    }

    @AfterEach
    void tearDown() throws Exception {
        bufferedWriter.close();
    }

    @Test
    void interfaceMethods() throws Exception {
        bufferedWriter.write("Hello");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.flush();
    }
}
