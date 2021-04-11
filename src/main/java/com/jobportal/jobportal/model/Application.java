package com.jobportal.jobportal.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Application entity class.
 *
 * @since 20.03.2021
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Applications")
public class Application {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_applied")
    private Date dateApplied;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "work_experience")
    private String workExperience;

    @Column(name = "education")
    private String education;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_APPLICATIONS_USER"), name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_APPLICATIONS_POSTING"), name = "posting_id")
    private Posting posting;

    public void addUser(@NonNull User user) {
        this.setUser(user);
        user.getApplications().add(this);
    }

    public void removeUser(@NonNull User user) {
        this.setUser(null);
        user.getApplications().remove(this);
    }

    public void addPosting(@NonNull Posting posting) {
        this.setPosting(posting);
        posting.getApplications().add(this);
    }

    public void removePosting(@NonNull Posting posting) {
        this.setPosting(null);
        posting.getApplications().remove(this);
    }

}
