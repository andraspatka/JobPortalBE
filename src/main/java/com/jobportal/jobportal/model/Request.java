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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_by", referencedColumnName = "id", nullable = false)
    private User requestedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_APPROVEDBY_USER"), name = "approved_by") //todo
    private User approvedBy;

    @Column(name = "approved_on")
    private Date approvedOn;
}
