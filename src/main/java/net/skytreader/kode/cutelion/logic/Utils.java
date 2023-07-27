package net.skytreader.kode.cutelion.logic;

import java.util.regex.Pattern;

public class Utils {

    public static final boolean isValidLocaleString(String l) {
        String trimmed = l.trim();
        return Pattern.matches("[a-zA-Z]+(-[a-zA-Z0-9]+)*", trimmed);
    }
}
