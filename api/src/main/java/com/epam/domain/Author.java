package com.epam.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity(name = "author")
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity {
    @Column(unique = true)
    private String name;
}
