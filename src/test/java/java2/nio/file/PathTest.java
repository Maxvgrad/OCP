package java2.nio.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PathTest {

    private Path homePath;
    private Path relative;


    @BeforeEach
    void setUp() {
        homePath = Paths.get(System.getProperty("user.home"));
        relative = Paths.get("condor");
    }

    @Test
    void getRoot() {
        assertEquals("C:" + File.separator, homePath.getRoot().toString());
    }

    @Test
    void getRootParent() {
        assertNull(homePath.getRoot().getParent());
    }

    @Test
    void getRootDoubleTimes() {
        assertEquals("C:" + File.separator, homePath.getRoot().getRoot());
    }

    @Test
    void getParent() {
        assertEquals("C:" + File.separator + "Users", homePath.getParent().toString());
    }

    @Test
    void getFileName() {
        assertEquals("maksim", homePath.getFileName().toString());
    }

    @Test
    void isAbsoluteTrue() {
        assertTrue(homePath.isAbsolute());
    }

    @Test
    void isAbsoluteFalse() {
        assertFalse(relative.isAbsolute());
    }

    @Test
    void toAbsolutePath() {
        assertTrue(relative.toAbsolutePath().isAbsolute());
    }

    @Test // does not work ??
    void zeroIndexEqualsRoot() {
        assertEquals(homePath.getName(0), homePath.getRoot());
    }

    @Test
    void subpath() {
        Path path = Paths.get("C:", "home", "user", ".ssh");
        assertEquals(3, path.getNameCount());
        assertEquals("home" + File.separator + "user", path.subpath(0, 2).toString());
    }

    @Test
    void relativizeRelativePath() {
        Path p1 = Paths.get("../../f1");
        Path p2 = Paths.get("f2");

        assertEquals("..\\..\\..\\f2", p1.relativize(p2).toString());
        assertEquals("..\\..\\..\\f1", p2.relativize(p1).toString());
        assertEquals("", p1.relativize(p1).toString());
    }

    @Test
    void relativizeAbsoluteAndRelativePath() {
        assertThrows(IllegalArgumentException.class, () -> homePath.relativize(relative));
    }

    @Test
    void resolveAbsPlusRelative() {
        String resolveResult = homePath.resolve(relative).toString();
        assertEquals(homePath.toString() + File.separator + relative.toString(), resolveResult);
    }

    @Test
    void resolveRelativePlusAbs() {
        String resolveResult = relative.resolve(homePath).toString();
        assertEquals(homePath.toString(), resolveResult);
    }

    @Test
    void resolveRelativePlusRelative() {
        String relative2 = Paths.get("condor2").toString();
        assertEquals(relative + File.separator + relative2, relative.resolve(relative2).toString());
    }

    @Test
    void normalize() {
        Path unNormalizedPath = Paths.get("F:/projects/../projects");
        assertEquals("F:\\projects", unNormalizedPath.normalize().toString());
    }

    @Test
    void to_real_path_throw_io_exception() throws Exception {
        Path path = Paths.get(".").toRealPath().resolve(relative);
        assertThrows(IOException.class, path::toRealPath);
    }

    @Test
    void get_path_from_var_args() {
        Path path = Paths.get("F:", "projects", "max");
        assertEquals("F:\\projects\\max", path.toString());
    }

    @Test
    void ch9_q1() {
        Path path = Paths.get("F:", "projects", "max");
        //path.normalize().relativize("/login"); does not compile
    }

    @Test
    void t1_q35() {
        Path p1 = Paths.get("x\\y");
        Path p2 = Paths.get("z");
        Path p3 = p1.relativize(p2);
        assertEquals("..\\..\\z", p3.toString());
    }

    @Test
    void relativize_one_path_with_root() {
        Path p1 = Paths.get("\\x\\y");
        Path p2 = Paths.get("z");
        assertThrows(IllegalArgumentException.class, () -> p1.relativize(p2));
    }

    @Test
    void resolveSiblingsAbsolutePath() {
        //given
        Path p1 = Paths.get("F:/hello/input.txt");
        //when
        Path p2 = p1.resolveSibling("output.txt");
        //then
        assertEquals("F:\\hello\\output.txt", p2.toString());
    }

    @Test
    void resolveSiblingsRelativePath() {
        //given
        Path p1 = Paths.get("hello/input.txt");
        //when
        Path p2 = p1.resolveSibling("output.txt");
        //then
        assertEquals("hello\\output.txt", p2.toString());
    }

    @Test
    void resolveSiblingsRelativePath2() {
        //given
        Path p1 = Paths.get("hello/input.txt");
        //when
        Path p2 = p1.resolveSibling("\\output.txt");
        //then
        assertEquals("hello\\output.txt", p2.toString());
    }
}

