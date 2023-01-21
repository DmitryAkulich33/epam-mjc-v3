package com.mjc.school.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "tblTag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(name = "name_tag", unique = true)
    private String name;
}
