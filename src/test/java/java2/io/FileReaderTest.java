package java2.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

class FileReaderTest {

    private FileReader fileReader;
    private File tmpFile;

    @BeforeEach
    void setUp() throws Exception {
        tmpFile = File.createTempFile("tmp", "data");
        fileReader = new FileReader(tmpFile);
        fillFile(tmpFile);
    }

    @AfterEach
    void tearDown() throws Exception {
        fileReader.close();
    }

    @Test
    void newBufferedReaderEveryIteration() throws Exception {

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        do {
            bufferedReader = new BufferedReader(fileReader); // wtf?
            line = bufferedReader.readLine();
            System.out.println(line);
        } while (line != null);
    }


    private void fillFile(File file) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("--");
            bufferedWriter.newLine();
            bufferedWriter.write("--");
            bufferedWriter.newLine();
            bufferedWriter.write("--");
            bufferedWriter.flush();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
