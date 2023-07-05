package net.skytreader.kode.cutelion.logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
    void testValidLocales() {
        assertTrue(Utils.isValidLocaleString("en-us"));
    }

    @Test
    void testInvalidLocale() {
        assertFalse(Utils.isValidLocaleString("en_us"));
    }
}
