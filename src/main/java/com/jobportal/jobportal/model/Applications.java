package com.jobportal.jobportal.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Applications")
public class Applications {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JPA_ID_GENERATOR")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id" )
    private List<Postings> postId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id" )
    //@Column(name = "user_id")
    private List<User> userId;

    @Column(name = "date_applied")
    private Date dateApplied;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "work_experience")
    private String workExperience;

    @Column(name = "education")
    private String education;
}
