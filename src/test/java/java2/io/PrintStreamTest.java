package java2.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrintStreamTest {

    private File tmpFile;
    private PrintStream printStream;
    private BufferedReader reader;
    private InputStreamReader inputStreamReader;
    private DataInputStream dataInputStream;

    @BeforeEach
    void setUp() throws Exception {
        tmpFile = File.createTempFile("data", "tmp");
        printStream = new PrintStream(tmpFile);
        reader = new BufferedReader(new FileReader(tmpFile));
        inputStreamReader = new InputStreamReader(new BufferedInputStream(new FileInputStream(tmpFile)));
        dataInputStream = new DataInputStream(new FileInputStream(tmpFile));
    }

    @AfterEach
    void tearDown() throws Exception {
        printStream.close();
        reader.close();
        inputStreamReader.close();
        dataInputStream.close();

        tmpFile.delete();
    }

    @Test
    void setOutFile() throws Exception {
        System.setOut(printStream);
        System.out.println(4);
        System.out.flush();

        assertTrue(tmpFile.length() > 0);

        String line;
        int length = (line = reader.readLine()) != null ? line.length() : 0;
        assertEquals(1, length);
        assertEquals("4", line);
    }


    @Test
    void dataInputStreamTest() throws Exception {
        System.setOut(printStream);
        System.out.print(4);
        System.out.flush();

        assertTrue(tmpFile.length() > 0);

        assertEquals(1, "4".getBytes().length);

        assertEquals("4".getBytes()[0], dataInputStream.read());
    }
}
