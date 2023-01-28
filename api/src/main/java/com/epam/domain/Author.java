package com.epam.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity(name = "author")
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity {
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<News> news;
}
