package net.skytreader.kode.cutelion.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.annotation.Nullable;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="projects")
public class Project extends CreativeAwesomeModel {
    @NotBlank
    private String name;
    @Nullable
    private String defaultLanguage;
    @Column(name="last_entry_added_at", nullable = true, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonSerialize(using= ZonedDateTimeSerializer.class)
    private ZonedDateTime lastEntryAddedAt;

    @OneToMany(mappedBy = "project", fetch=FetchType.EAGER)
    @JsonManagedReference
    private List<Translation> translations = new LinkedList<>();

    protected Project() {

    }

    public Project(String name, String defaultLanguage) {
        super();
        this.name = name;
        this.defaultLanguage = defaultLanguage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(@Nullable String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public ZonedDateTime getLastEntryAddedAt() {
        return lastEntryAddedAt;
    }

    public void setLastEntryAddedAt(ZonedDateTime lastEntryAddedAt) {
        this.lastEntryAddedAt = lastEntryAddedAt;
    }
}
