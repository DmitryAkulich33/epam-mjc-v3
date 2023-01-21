package com.epam.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;
}
