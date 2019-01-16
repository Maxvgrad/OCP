package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilsTest {

    @Test
    void abbreviation2ArgsTest() {
        String abbreviation = StringUtils
                .abbreviate("Roses are red lemons are sour. Open your legs and give me an hour.", 16);
        assertEquals("Roses are red...", abbreviation);
    }

    @Test
    void abbreviation3ArgsWithOffsetTest() {
        String abbreviation = StringUtils
                .abbreviate("Roses are red violets are violet. Here is my number why don’t you dial it?", 6, 21);
        assertEquals("...are red violets...", abbreviation);
    }

    @Test
    void abbreviation3ArgsWithMarkerTest() {
        String abbreviation = StringUtils
                .abbreviate("Roses are red violets are violet. Here is my number why don’t you dial it?", "~~~", 24);
        assertEquals("Roses are red violets~~~", abbreviation);
    }

    @Test
    void truncate2ArgsTest() {
        String truncated = StringUtils
                .truncate("Roses are red violets are blue. One ripped condom that caused you.", 31);
        assertEquals("Roses are red violets are blue.", truncated);
    }

    @Test
    void truncate3ArgsWithOffsetTest() {
        String truncated = StringUtils
                .truncate("Roses are red that much is true. But violets are purple not freaking blue.",33, 41);
        assertEquals("But violets are purple not freaking blue.", truncated);
    }

    @Test
    void stripCompareWithTrimTest() {
        String testStr = "  Roses ";
        assertEquals(testStr.trim(), StringUtils.strip(testStr));
    }

    @Test
    void t() {
        //StringUtils.
    }
}
