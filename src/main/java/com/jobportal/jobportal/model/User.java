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

    @ManyToOne(cascade = CascadeType.ALL)
//    @Column(name = "company")
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_USERS_COMPANY"), name = "company")
    private Company company;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
