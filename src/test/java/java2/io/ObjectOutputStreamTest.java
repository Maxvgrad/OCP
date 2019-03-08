package java2.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ocp.concurrent.Person;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectOutputStreamTest {

    private BufferedInputStream fileInputStream;
    private BufferedReader bufferedReader;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private File tmpFile;
    private Person person;
    private byte[] buffer;

    @BeforeEach
    void setUp() throws Exception {
        person = new Person("Max", 26);
        tmpFile = File.createTempFile("serialized-object", "data");
        objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(tmpFile)));
        fileInputStream = new BufferedInputStream(new FileInputStream(tmpFile));
        bufferedReader = new BufferedReader(new FileReader(tmpFile));
        buffer = new byte[32];
        //TODO
        objectInputStream = null; //new ObjectInputStream(new BufferedInputStream(new FileInputStream(tmpFile)));

//        tmpFile.deleteOnExit();
    }

    @AfterEach
    void tearDown() throws Exception {
        objectOutputStream.close();
        //tmpFile.delete();
    }

    @Test
    void writeSerializedObject() throws Exception {
        long fileSize = tmpFile.length();
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        assertTrue(tmpFile.length() > fileSize);
    }

    @Test
    void readSerializedObjectFromFileWithFileInputStream() throws Exception {
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();

        int lenRead;
        while ((lenRead = fileInputStream.read(buffer)) > 0) {
            System.out.println(Arrays.toString(Arrays.copyOf(buffer, lenRead)));
        }
    }

    @Test
    void readSerializedObjectFromFileWithFileReader() throws Exception {
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();

        bufferedReader.lines().forEach(System.out::println);
    }

    @Test
    void readSerializedObject() throws Exception {
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();

        Object object = objectInputStream.readObject();
        assertTrue(object instanceof Person);

        Person deserializePerson = (Person) object;
        assertEquals(deserializePerson.getName(), person.getName());
        assertEquals(deserializePerson.getAge(), person.getAge());
    }
}
