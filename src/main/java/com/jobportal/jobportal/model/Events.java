package com.jobportal.jobportal.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Events")
public class Events {
    @Id
    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JPA_ID_GENERATOR")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "name")
    private String name;
}
