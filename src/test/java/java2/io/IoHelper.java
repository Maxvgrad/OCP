package java2.io;


import java.io.File;
import java.io.FileWriter;
import java.util.Random;

class IoHelper {

    static File createTmpFile() {
        try {
            return File.createTempFile("file_" + new Random(42).nextInt(), "tmp");
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    static void fill(String content, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }


}
