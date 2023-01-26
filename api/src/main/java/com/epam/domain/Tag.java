package com.epam.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseEntity {
    @Column(unique = true)
    private String name;
}
