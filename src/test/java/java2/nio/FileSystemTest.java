package java2.nio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

class FileSystemTest {

    private FileSystem fileSystem;
    private Path path;

    @BeforeEach
    void setUp() {
        fileSystem = FileSystems.getDefault();
    }

    @Test
    void getPath() {
        path = fileSystem.getPath(System.getProperty("user.home"));

    }
}
