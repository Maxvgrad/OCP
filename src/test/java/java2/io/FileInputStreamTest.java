package java2.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileInputStreamTest {

    private InputStream fileInputStream;
    private File tmp = IoHelper.createTmpFile();


    @BeforeEach
    void setUp() throws Exception {
        fileInputStream = new BufferedInputStream(new FileInputStream(tmp));
        IoHelper.fill("XYZABC", tmp);
    }

    @Test
    void markSupported() {
        assertTrue(fileInputStream.markSupported());
    }

    @Test
    void markAndReset() throws Exception {
        fileInputStream.mark(3);
        int x = fileInputStream.read();
        int y = fileInputStream.read();
        int z = fileInputStream.read();
        fileInputStream.reset();
        assertEquals(x, fileInputStream.read());
        assertEquals(y, fileInputStream.read());
        assertEquals(z, fileInputStream.read());
    }

    @Test
    void markExceedReadLimit() throws Exception {
        fileInputStream.mark(1);
        int x = fileInputStream.read();
        int y = fileInputStream.read();
        int z = fileInputStream.read();
        int exceedingByte = fileInputStream.read();
        fileInputStream.reset();
        assertEquals(x, fileInputStream.read());
        assertEquals(y, fileInputStream.read());
        assertEquals(z, fileInputStream.read());
    }

    @Test
    void skipAndRead() throws Exception {

        try (FileInputStream fis = new FileInputStream(ClassLoader.getSystemResource("count.txt").getFile());
             InputStreamReader isr = new InputStreamReader(fis)) {
            while (isr.ready()) {
                isr.skip(1);
                int i = isr.read();
                char c = (char) i;
                System.out.print(c);
            }

        }
    }

    @Test
    void effectivelyFinalVariable() throws Exception {
        try (InputStream io = new FileInputStream("")) {
            //io = new FileInputStream("");
        }
    }

    @Test
    void ch8q1() throws Exception {
        InputStream is = new BufferedInputStream(new FileInputStream(""));

        //InputStream werapper = new FileInputStream(is); low level
    }
}
