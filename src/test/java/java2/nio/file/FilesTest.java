package java2.nio.file;

import java2.io.IoHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserPrincipal;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilesTest {

    private Path home;

    @BeforeEach
    void setUp() {
        home = Paths.get(System.getProperty("user.home"));
    }

    @Test
    void exists_true() {
        assertTrue(Files.exists(home));
    }

    @Test
    void exists_pwd_true() {
        Path path = Paths.get(".");
        assertTrue(Files.exists(path));
    }

    @Test
    void exists_false() {
        Path path = Paths.get("home");
        assertFalse(Files.exists(path));
    }

    @Test
    void is_same_file_same_objects() throws IOException {
        assertTrue(Files.isSameFile(home, home));
    }

    @Test
    void is_same_file_different_objects() throws IOException {
        Path path = Paths.get(System.getProperty("user.home"));
        assertTrue(Files.isSameFile(home, path));
    }

    @Test
    void is_same_file_no_such_file() throws IOException {
        Path path = Paths.get("home");
        assertThrows(NoSuchFileException.class, () -> Files.isSameFile(home, path));
    }

    @Test
    void is_same_file_different_files() throws IOException {
        Path path = Paths.get(".");
        assertFalse(Files.isSameFile(home, path));
    }

    @Test
    void create_directory() throws IOException {
        //given
        Path path = home.resolve(Paths.get("testdir"));
        assertFalse(Files.exists(path));

        //when
        Files.createDirectory(path);

        //then
        assertTrue(Files.exists(path));
        Files.delete(path);
    }

    @Test
    void create_directory_parent_does_not_exists() throws IOException {
        //given
        Path path = home.resolve(Paths.get("testdir\\testdir"));
        assertFalse(Files.exists(path));

        //when
        assertThrows(IOException.class, () -> Files.createDirectory(path));
    }

    @Test
    void create_directories() throws IOException {
        //given
        Path path = home.resolve(Paths.get("testdir\\testdir"));
        assertFalse(Files.exists(path));

        //when
        Files.createDirectories(path);

        //then
        assertTrue(Files.exists(path));
        Files.delete(path);
    }

    @Test
    void copy_path_path() {
        //given
        Path notExists1 = Paths.get("1");
        Path notExists2 = Paths.get("2");

        //when - then
        assertThrows(IOException.class, () -> Files.copy(notExists1, notExists2));
    }

    @Test
    void copy_path_output_stream() throws IOException {
        //given
        Path path = Paths.get(".").toRealPath();
        OutputStream os = new FileOutputStream(IoHelper.createTmpFile());

        //when
        Files.copy(path, os);
    }

    @Test
    void move() throws IOException {
        //given
        Path cur = Paths.get(".");
        Path pathOne = cur.resolve(Paths.get("zoo"));
        Files.createFile(pathOne);
        Path pathTwo = cur.resolve(Paths.get("moved-zoo"));
        pathTwo.toFile().deleteOnExit();

        //when
        Files.move(pathOne, pathTwo);

        //then
        assertFalse(Files.exists(pathOne));
        assertTrue(Files.exists(pathTwo));
    }

    @Test
    void delete_file() throws IOException {
        //given
        Path path = home.resolve(Paths.get("feature.txt"));
        Files.createFile(path);

        //when
        Files.delete(path);

        //then
        assertFalse(Files.exists(path));
    }

    @Test
    void delete_not_empty_directory() throws IOException {
        //given
        Path dir = home.resolve("folder");
        Path file = dir.resolve("file");
        try {
            Files.createDirectory(dir);
            Files.createFile(file);

            //when
            Files.delete(dir);
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Files.delete(file);
            Files.delete(dir);
        }
    }

    @Test
    void delete_if_exists() throws Exception {
        Path path = home.resolve("file");
        assertFalse(Files.deleteIfExists(path));
    }

    @Test
    void buffered_reader() {
        //given
        Path file = Paths.get(".").normalize().resolve("don_juan.txt");

        try (BufferedReader reader = Files.newBufferedReader(file, Charset.forName("UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Test
    void buffered_writer() {
        //given
        Path file = Paths.get(".").normalize().resolve("file");
        String line = "Don Juan";

        try (BufferedReader reader = Files.newBufferedReader(file, Charset.defaultCharset());
             BufferedWriter writer = Files.newBufferedWriter(file, Charset.defaultCharset())) {

            Files.createFile(file);

            //when
            writer.append(line);
            writer.flush();

            String lineFromFile = reader.readLine();

            //then
            assertEquals(line, lineFromFile);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                Files.deleteIfExists(file);
            } catch (IOException ex) {
                System.out.println("File is not deleted.");
            }
        }
    }

    @Test
    void read_all_lines() throws IOException {
        //given
        Path path = Paths.get(".").resolve("don_juan.txt");

        //when
        List<String> line = Files.readAllLines(path);

        //then
        assertFalse(line.isEmpty());
    }

    @Test
    void get_last_modified_time() throws IOException {
        System.out.println(Files.getLastModifiedTime(home));
    }

    @Test
    void set_last_modified_time() throws IOException {
        Path path = Paths.get(".").normalize().resolve("don_juan.txt");
        FileTime t1 = Files.getLastModifiedTime(path);

        Files.setLastModifiedTime(path, FileTime.from(Instant.now()));
        FileTime t2 = Files.getLastModifiedTime(path);

        assertNotEquals(t1, t2);
    }


    @Test
    void get_owner() throws IOException {
        Path path = Paths.get(".").normalize().resolve("don_juan.txt");
        UserPrincipal userPrincipal = Files.getOwner(path);
        System.out.println(userPrincipal.getName());
    }

    @Test
    void get_file_attribute_view_basic() {
        BasicFileAttributeView view = Files.getFileAttributeView(home, BasicFileAttributeView.class);
        assertEquals("basic", view.name());
    }

    @Test
    void get_file_attribute_view_doc() {
        DosFileAttributeView view = Files.getFileAttributeView(home, DosFileAttributeView.class);
        assertEquals("doc", view.name());
    }

    @Test
    void get_file_attribute_view_posix() {
        PosixFileAttributeView view = Files.getFileAttributeView(home, PosixFileAttributeView.class);
        assertEquals("posix", view.name());
    }

    @Test
    void read_attributes_posix() {
        assertThrows(UnsupportedOperationException.class, () -> Files.readAttributes(home, PosixFileAttributes.class));
    }

    @Test
    void read_attributes_dos() throws IOException {
        DosFileAttributes attributes = Files.readAttributes(home, DosFileAttributes.class);
        System.out.println("isArchive: " + attributes.isArchive());
        System.out.println("isHidden: " + attributes.isHidden());
        System.out.println("isReadOnly: " + attributes.isReadOnly());
        System.out.println("isSystem: " + attributes.isSystem());
        System.out.println("creationTime: " + attributes.creationTime());
        System.out.println("fileKey: " + attributes.fileKey());
        System.out.println("isDirectory: " + attributes.isDirectory());
        System.out.println("isOther: " + attributes.isOther());
        System.out.println("isRegularFile: " + attributes.isRegularFile());
        System.out.println("isSymbolicLink: " + attributes.isSymbolicLink());
        System.out.println("lastAccessTime: " + attributes.lastAccessTime());
        System.out.println("lastModifiedTime: " + attributes.lastModifiedTime());
        System.out.println("size: " + attributes.size());
    }

    @Test
    void read_attributes_basic() throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(home, BasicFileAttributes.class);

        // Is Not part of BasicFileAttributes
        //System.out.println("isArchive: " + attributes.isArchive());
        //System.out.println("isHidden: " + attributes.isHidden());
        //System.out.println("isReadOnly: " + attributes.isReadOnly());
        //System.out.println("isSystem: " + attributes.isSystem());

        System.out.println("creationTime: " + attributes.creationTime());
        System.out.println("fileKey: " + attributes.fileKey());
        System.out.println("isDirectory: " + attributes.isDirectory());
        System.out.println("isOther: " + attributes.isOther());
        System.out.println("isRegularFile: " + attributes.isRegularFile());
        System.out.println("isSymbolicLink: " + attributes.isSymbolicLink());
        System.out.println("lastAccessTime: " + attributes.lastAccessTime());
        System.out.println("lastModifiedTime: " + attributes.lastModifiedTime());
        System.out.println("size: " + attributes.size());
    }

    @Test
    void walk_java_classes() throws Exception {
        Path path = Paths.get(".").toRealPath();

        long count = Files.walk(path).filter(p -> p.toString().endsWith(".java")).count();
        assertTrue(count > 100);
    }

    @Test
    void find_heavy_classes() throws Exception {
        Path path = Paths.get(".").toRealPath();
        Files.find(path, 20, (p, v) -> v.size() > 10000).forEach(System.out::println);
    }

    @Test
    void list() throws Exception {
        Files.list(home).filter(p -> p.getName(2).toString().startsWith(".")).forEach(System.out::println);
    }

    @Test
    void createBufferedReader() throws IOException {
        assertThrows(NoSuchFileException.class, () -> {
            Files.newBufferedReader(Paths.get("UnExistedPath")).read();
        });
    }
}
