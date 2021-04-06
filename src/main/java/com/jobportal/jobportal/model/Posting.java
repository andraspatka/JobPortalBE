package com.jobportal.jobportal.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Posting")
public class Posting {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_POSTEDBY_USER"), name = "posted_by")
    private User postedBy;

    @Column(name = "posted_at")
    private LocalDate postedAt;

    @Column(name = "deadline")
    private LocalDate  deadline;

    @Column(name = "number_of_views")
    private long numberOfViews;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_CATEGORYID_CATEGORIES"), name = "category_id")
    private Category category;

    @Column(name = "requirements")
    private String requirements;
}
