package org.ocp.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocaleTest {

    @Test
    void getDefaultLocale() {
        assertEquals("ru_RU", Locale.getDefault().toString());
    }

    @Test
    void getAvailableLocales() {
        assertEquals(748L, Arrays.stream(Locale.getAvailableLocales()).peek(System.out::println).count());
    }

    @Test
    void localConstructorConventionTwoArg() {
        Locale locale = new Locale("hi", "HI");
        assertEquals("hi_HI", locale.toString());
    }

    @Test
    void localConstructorConventionOneArg() {
        Locale locale = new Locale("hi");
        assertEquals("hi", locale.toString());
    }
}
