package sun.reflection;

import org.junit.jupiter.api.Test;
import sun.reflect.Reflection;

class ReflectionTest {


    @Test
    //@CallerSensitive
    void test() {
        System.out.println(Reflection.getCallerClass());
    }
}
