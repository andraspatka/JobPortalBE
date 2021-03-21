package com.jobportal.jobportal.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * User entity class.
 *
 * @since 13.03.2021
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UC_USER_EMAIL", columnNames = {"EMAIL"})
        })
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Company")
    private List<Company> company;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
