package net.skytreader.kode.cutelion.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.annotation.Nullable;
import net.skytreader.kode.cutelion.logic.Utils;
import org.hibernate.annotations.Type;

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

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Translation> translations = new LinkedList<>();

    @Type(ListArrayType.class)
    @Column(name="locales")
    private List<String> locales;

    protected Project() {

    }

    public Project(String name, String defaultLanguage, List<String> locales) {
        super();
        this.name = name;
        this.defaultLanguage = Utils.toCanonlocaleForm(defaultLanguage);
        if (!locales.contains(this.defaultLanguage)){
            locales.add(this.defaultLanguage);
        }
        this.locales = locales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getDefaultLanguage() {
        return Utils.toCanonlocaleForm(defaultLanguage);
    }

    public void setDefaultLanguage(@Nullable String defaultLanguage) {
        this.defaultLanguage = Utils.toCanonlocaleForm(defaultLanguage);
    }

    public ZonedDateTime getLastEntryAddedAt() {
        return lastEntryAddedAt;
    }

    public void setLastEntryAddedAt(ZonedDateTime lastEntryAddedAt) {
        if (lastEntryAddedAt.isAfter(this.lastEntryAddedAt)) {
            this.lastEntryAddedAt = lastEntryAddedAt;
        }
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public List<String> getLocales() {
        return locales;
    }

    public void setLocales(List<String> locales) {
        this.locales = locales;
    }

    public void addLocale(String l) {
        this.locales.add(Utils.toCanonlocaleForm(l));
    }
}
