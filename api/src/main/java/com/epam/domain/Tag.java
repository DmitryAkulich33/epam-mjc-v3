package com.epam.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "tag")
public class Tag extends BaseEntity {
    @Column(unique = true)
    private String name;
}
