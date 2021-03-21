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
@Table(name = "Postings")
public class Postings {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id" )
    private List<User> postedBy;

    @Column(name = "posted_at")
    private Date postedAt;

    @Column(name = "deadline")
    private Date  deadline;

    @Column(name = "number_of_views")
    private long numberOfViews;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id" )
    private List<Categories> categoryId;

    @Column(name = "requirements")
    private String requirements;
}
