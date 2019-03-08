package java2.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectInputStreamTest {

    private static final String PATH_FILE_CAR_TXT = "/car.txt";

    @Test
    void readObject() throws Exception {
        File file = new File(this.getClass().getResource(PATH_FILE_CAR_TXT).toURI());
        assertTrue(file.exists());

        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            System.out.println(ois.readObject());
        }
    }
}
