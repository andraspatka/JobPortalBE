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
@Table(name = "Company")
public class Company {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    @Builder.Default
    private Set<User> employees = new HashSet<>();

    @Column(name = "name")
    private String name;

    public void addUser(User user) {
        this.employees.add(user);
        user.setCompany(this);
    }
}
