package net.skytreader.kode.cutelion.logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
    void testValidLocales() {
        System.out.println("Valid locales test");
        String[] testCases = new String[]{
                "en-us", "en-US", "EN-US", " en-us ", "en"
        };
        for (String test : testCases) {
            System.out.println("Testing '" + test + "'");
            assertTrue(Utils.isValidLocaleString(test));
        }
    }

    @Test
    void testInvalidLocale() {
        System.out.println("Invalid locales test");
        String[] testCases = new String[]{
                "en_us", "", "-", "en--US", "-US", "en-"
        };
        for (String test: testCases) {
            System.out.println("Testing '" + test + "'");
            assertFalse(Utils.isValidLocaleString(test));
        }
    }
}
