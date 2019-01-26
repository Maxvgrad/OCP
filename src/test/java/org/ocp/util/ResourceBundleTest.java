package org.ocp.util;

import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

class ResourceBundleTest {

    @Test
    void defaultLocaleBundle() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("greeting");
        resourceBundle.keySet().stream()
                      .forEach(key -> System.out.println(key + " = " + resourceBundle.getString(key)));
    
    }
}
