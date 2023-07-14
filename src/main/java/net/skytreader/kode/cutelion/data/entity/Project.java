package net.skytreader.kode.cutelion.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="projects")
public class Project extends CreativeAwesomeModel {
    @NotBlank
    private String name;
    @Nullable
    private String defaultLanguage;

    @OneToMany(mappedBy = "project")
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
}
