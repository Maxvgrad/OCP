package org.apache.commons.lang3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("just for test")
class AnnotationUtilsTest {

    @Test
    void hashCodeTest() {
        assertNotNull(AnnotationUtils.hashCode(this.getClass().getAnnotation(DisplayName.class)));
    }

    @Test
    void isValidAnnotationMemberTypeTest() {
        assertFalse(AnnotationUtils.isValidAnnotationMemberType(this.getClass()));
        assertTrue(AnnotationUtils.isValidAnnotationMemberType(Class.class));
    }
}
