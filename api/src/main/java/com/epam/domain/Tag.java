package com.epam.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "tag")
public class Tag extends BaseEntity {
    @Column(unique = true)
    private String name;
}
