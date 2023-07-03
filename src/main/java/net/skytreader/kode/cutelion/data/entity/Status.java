package net.skytreader.kode.cutelion.data.entity;

import jakarta.persistence.Entity;

@Entity
public class Status extends CreativeAwesomeModel {
    private String name;

    public Status() {

    }

    public Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
