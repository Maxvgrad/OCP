package java2.io;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileTest {

    private File userHome;
    private File file;
    private File file2;

    @BeforeEach
    void setUp() {
        String userHome = System.getProperty("user.home");
        this.userHome = new File(userHome);
        file = new File(userHome, "car.txt");
        file2 = new File(userHome, "car2.txt");
    }

    @AfterEach
    void tearDown() {
        file.delete();
        file2.delete();
    }

    @Test
    void isFile() {
        assertFalse(userHome.isFile());
        assertTrue(userHome.exists());
        assertTrue(userHome.isAbsolute());
        assertTrue(userHome.isDirectory());
        assertFalse(userHome.isHidden());
    }

    @Test
    void createNewFile() throws Exception {
        assertFalse(file.exists());
        assertTrue(file.createNewFile());
        assertTrue(file.isFile());
    }

    @Test
    void renameTo() throws Exception {
        assertFalse(file2.exists());
        assertTrue(file.createNewFile());
        assertTrue(file.exists());
        assertTrue(file.renameTo(file2));

        assertTrue(file2.exists());
        assertFalse(file.exists());
    }

    @Test
    void listFiles() {
        Arrays.stream(userHome.listFiles())
              .forEach(f -> System.out.println(f.getName() + " : " + (f.isHidden() ? "Hidden" : "Visible")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"F:/projects", "F:///projects", "F:\\projects", "F:\\\\projects", "\\\\F:\\\\projects", "///F:\\\\projects"})
    void filePathRepresentationValid(String path) {
        File file = new File(path);
        assertTrue(file.exists());
    }
}
