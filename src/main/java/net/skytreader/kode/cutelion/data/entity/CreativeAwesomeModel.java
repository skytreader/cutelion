package net.skytreader.kode.cutelion.data.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class CreativeAwesomeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_at", nullable = false, columnDefinition =
            "TIMESTAMP WITH TIME ZONE")
    @JsonSerialize(using=ZonedDateTimeSerializer.class)
    private ZonedDateTime createdAt;

    @Column(name="modified_at", nullable = false, columnDefinition =
            "TIMESTAMP WITH TIME ZONE")
    @JsonSerialize(using=ZonedDateTimeSerializer.class)
    private ZonedDateTime modifiedAt;

    public CreativeAwesomeModel() {
        ZonedDateTime now = ZonedDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(ZonedDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CreativeAwesomeModel that)) {
            return false; // null or not an AbstractEntity class
        }
        if (getId() != null) {
            return getId().equals(that.getId());
        }
        return super.equals(that);
    }
}
