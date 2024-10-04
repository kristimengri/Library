package com.lib.Library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Basic {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id = UUID.randomUUID().toString();
    private String name = "basic";
    @Lob
    private String description;
    private OffsetDateTime dateCreated = OffsetDateTime.now();
    private OffsetDateTime dateUpdated = OffsetDateTime.now();
    private boolean softDelete = false;


    public void updateDate() {
        this.dateUpdated = OffsetDateTime.now();
    }


}
