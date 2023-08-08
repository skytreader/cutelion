package net.skytreader.kode.cutelion.data.transfer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import java.time.ZonedDateTime;

public class PlainProjectDTO {
    private Long id;
    private String name;
    private String defaultLanguage;
    @JsonSerialize(using= ZonedDateTimeSerializer.class)
    private ZonedDateTime lastEntryAddedAt;
    @JsonSerialize(using= ZonedDateTimeSerializer.class)
    private ZonedDateTime modifiedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public ZonedDateTime getLastEntryAddedAt() {
        return lastEntryAddedAt;
    }

    public void setLastEntryAddedAt(ZonedDateTime lastEntryAddedAt) {
        this.lastEntryAddedAt = lastEntryAddedAt;
    }

    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(ZonedDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
