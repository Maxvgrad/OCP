package java2.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ocp.io.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PrintWriterTest {

    private PrintWriter printWriter;
    private File tmpFile;
    private BufferedReader bufferedReader;
    private Person person;

    @BeforeEach
    void setUp() throws Exception {
        person = new Person("Max", 26);
        tmpFile = File.createTempFile("tmp", "tmp");
        printWriter = new PrintWriter(tmpFile);
        bufferedReader = new BufferedReader(new FileReader(tmpFile));
    }

    @AfterEach
    void tearDown() throws Exception {
        printWriter.close();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(tmpFile))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            tmpFile.delete();
        }
    }

    @Test
    void appendChar() {
        printWriter.append('A').append("3").flush();
    }

    @Test
    void appendCharSequence() {
        printWriter.append("Abc");
    }

    @Test
    void appendCharSequenceWithBoundary() {
        printWriter.append("Abcde", 1, 3);
    }

    @Test
    void printInt() {
        printWriter.print(1);
    }

    @Test
    void printChar() {
        printWriter.print('r');
    }

    @Test
    void printCharArray() {
        printWriter.print(new char[] {'a', 'b'});
    }

    @Test
    void printBoolean() {
        printWriter.print(true);
    }

    @Test
    void printObject() {
        printWriter.print(person);
    }

    @Test
    void printDoubleTimes() {
        printWriter.print(person);
        printWriter.print(person);
    }

    @Test
    void printlnDoubleTimes() {
        printWriter.println(person);
        printWriter.println(person);
    }

    @Test
    void checkError() {
        assertFalse(printWriter.checkError());
    }

    @Test
    void format() {
        printWriter.format("Format: %s", "arg").println();
    }

    @Test
    void printf() {
        printWriter.printf("Format: %s", "arg").flush();
    }

    @Test
    void write() {
        printWriter.write(3234);
    }
}
