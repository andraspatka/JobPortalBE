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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_APPLICATIONS_POSTING"), name = "posting_id")
    private Postings post;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_APPLICATIONS_USER"), name = "user_id")
    private User user;

    @Column(name = "date_applied")
    private Date dateApplied;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "work_experience")
    private String workExperience;

    @Column(name = "education")
    private String education;
}
