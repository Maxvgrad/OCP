package java2.io;


import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class IoHelper {

    public static File createTmpFile() {
        try {
            return File.createTempFile("file_" + new Random(42).nextInt(), "tmp");
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static void fill(String content, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }


}
