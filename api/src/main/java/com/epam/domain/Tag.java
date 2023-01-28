package com.epam.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseEntity {
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<News> news = new ArrayList<>();

}
