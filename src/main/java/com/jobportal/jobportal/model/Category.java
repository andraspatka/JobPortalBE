package com.jobportal.jobportal.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Categories", uniqueConstraints = {
        @UniqueConstraint(
                name = "UC_CATEGORY_NAME", columnNames = {"NAME"})
})
public class Category {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @Builder.Default
    private Set<Posting> postings = new HashSet<>();
}
