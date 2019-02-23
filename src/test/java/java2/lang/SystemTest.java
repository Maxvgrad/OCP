package java2.lang;

import org.junit.jupiter.api.Test;

class SystemTest {


    @Test
    void identityHashCode() {
        System.out.println(System.identityHashCode(new SystemTest()));
    }
}
