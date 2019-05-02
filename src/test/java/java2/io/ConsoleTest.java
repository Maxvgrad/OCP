package java2.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Console;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ConsoleTest {

    private Console console;
    private Map<String, Method> methodMap;

    @BeforeEach
    void setUp() {
        console = System.console();
        methodMap = Arrays.stream(Console.class.getDeclaredMethods())
                          .collect(Collectors.toMap(Method::getName, Function.identity(), (m1, m2) -> m2));
    }

    @Test // Tty not supported :(
    void istty() throws Exception {
        Method isTtyMethod = methodMap.get("istty");
        isTtyMethod.setAccessible(true);
        assertFalse((Boolean) isTtyMethod.invoke(null));
    }

    @Test
    void consoleInterface() {
        if (console != null) {
            console.flush();
            PrintWriter writer = console.writer();
            Readable readable = console.reader();
            console.format("Argumer %s", "arg");
            console.printf("Argumer %s", "arg");
            console.readLine();
            console.readLine("Format", "arg");
            char[] pass =console.readPassword();
            pass = console.readPassword("Format", "arg");
        }
    }


    @Test
    void loginTest() {
        String id = console.readLine("%s", "Enter UserId:");
        System.out.println("userid is " + id);
        char[] pwd = console.readPassword("%s", "Enter Password :");
        System.out.println("password is " + pwd);
    }
}
