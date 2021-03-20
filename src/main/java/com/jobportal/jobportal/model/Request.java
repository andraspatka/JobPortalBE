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
@Table(name = "Request")
public class Request {

    @Id
    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JPA_ID_GENERATOR")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<User> requestedBy;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id")
    private List<User> approvedBy ;


    @Column(name = "approved_on ")
    private Date approvedOn;



}
