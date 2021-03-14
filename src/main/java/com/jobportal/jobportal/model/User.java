package com.jobportal.jobportal.model;

import lombok.*;

import javax.persistence.*;

/**
 * User entity class.
 *
 * @since 13.03.2021
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JPA_ID_GENERATOR")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "company")
    private String company;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private Role role;
}
