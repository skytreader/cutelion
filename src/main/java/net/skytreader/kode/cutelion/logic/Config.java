package net.skytreader.kode.cutelion.logic;

import net.skytreader.kode.cutelion.data.entity.InstallationConfig;
import net.skytreader.kode.cutelion.data.repository.InstallationConfigRepository;
import net.skytreader.kode.cutelion.exceptions.ConfigConstraintException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Config {

    public static final String KEY_DEFAULT_LOCALE = "default-locale";

    private Map<String, String> cfg;

    public Config(Map<String, String> cfg) {
        this.cfg = cfg;
    }

    public Config(InstallationConfigRepository icr) {
        this.cfg = new HashMap<String, String>();
        Collection<String> keys = Arrays.asList(Config.KEY_DEFAULT_LOCALE);
        for (InstallationConfig configRecord : icr.fetchConfig(keys)){
            this.cfg.put(configRecord.getKey(), configRecord.getValue());
        }
    }

    public String fetchDefaultLocale() throws ConfigConstraintException {
        return "TODO";
    }
}
