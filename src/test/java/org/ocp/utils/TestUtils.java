package org.ocp.utils;

import org.ocp.dto.PersonDto;

public class TestUtils {

    private TestUtils() {}

    public static PersonDto buildPerson() {
        return PersonDto.builder().interest("test interest").build();
    }
}
