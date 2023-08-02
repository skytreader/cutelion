package net.skytreader.kode.cutelion.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="translations")
public class Translation extends CreativeAwesomeModel {

    @NotBlank
    private String key;
    @NotNull
    private String value;
    @ManyToOne
    @JoinColumn(name="project_id")
    @NotNull
    @JsonBackReference
    private Project project;

    protected Translation() {

    }

    public Translation(String key, String value, Project project) {
        this.key = key;
        this.value = value;
        this.project = project;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
