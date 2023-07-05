package net.skytreader.kode.cutelion.logic;

import java.util.IllformedLocaleException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.function.Function;

public class Utils {

    public static boolean isValidLocaleString(String l) {
        String[] parse = l.split("-");
        if (parse.length == 0 || parse.length >= 4) {
            return false;
        } else {
            Locale.Builder builder = new Locale.Builder();
            Function<String, Locale.Builder>[] partsProgression = new Function[]{
                    s -> builder.setLanguage((String) s),
                    s -> builder.setRegion((String) s),
                    s -> builder.setVariant((String) s)
            };

            for (int i = 0; i < parse.length; i++) {
                try {
                    partsProgression[i].apply(parse[i]);
                } catch (IllformedLocaleException ile) {
                    return false;
                }
            }

            Locale loc = builder.build();

            try {
                return loc.getISO3Language() != null && loc.getISO3Country() != null;
            } catch (MissingResourceException mre) {
                return false;
            }
        }
    }
}
