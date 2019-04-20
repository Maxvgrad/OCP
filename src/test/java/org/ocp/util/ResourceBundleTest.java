package org.ocp.util;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResourceBundleTest {

    private static final String BUNDLE_NAME_GREETING = "greeting";

    @Test
    void defaultLocaleBundle() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME_GREETING);
        printContent(resourceBundle);
    }

    @Test
    void javaClassBundle() {
        Locale locale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        assertEquals("en_US", locale.toString());
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME_GREETING, locale);
        printContent(resourceBundle);
    }

    @Test
    void bundleByOnlyLanguage() {
        Locale.setDefault(new Locale.Builder().setLanguage("fr").build());
        printContent(ResourceBundle.getBundle(BUNDLE_NAME_GREETING));
    }

    @Test
    void checkWitchOneIsStronger() {
        Locale.setDefault(new Locale.Builder().setLanguage("en").build());
        assertEquals("en", Locale.getDefault().toString());
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME_GREETING,
                                              new Locale.Builder().setLanguage("fr").setRegion("FR").build());

        assertEquals("Scotland", resourceBundle.getString("province"));
        assertEquals("Chelsea", resourceBundle.getString("club"));
    }

    @Test
    void getMsg() {
        Locale.setDefault(new Locale("fr", "CA"));
        Locale l = new Locale("jp", "JP");
        ResourceBundle rb = ResourceBundle.getBundle("appmessages", l);
        String msg = rb.getString("key_1");
        assertEquals("jp_JP", msg);

        msg = rb.getString("key_2");
        assertEquals("jp", msg);

        msg = rb.getString("key_3");
        assertEquals("none", msg);

        assertThrows(MissingResourceException.class, () -> rb.getString("key_4"));
    }

    @Test
    void getMsgDefault() {
        Locale.setDefault(new Locale("fr", "CA"));

        ResourceBundle rb = ResourceBundle.getBundle("appmessages");
        String msg = rb.getString("key_1");
        assertEquals("fr_CA", msg);

        msg = rb.getString("key_4");
        assertEquals("fr_CA", msg);
    }

    private void printContent(ResourceBundle bundle) {
        bundle.keySet().stream()
                      .forEach(key -> System.out.println(key + " = " + bundle.getString(key)));
    }
}
