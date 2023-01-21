package com.epam.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(unique = true)
    private String name;
}
