package com.jobportal.jobportal.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Request", uniqueConstraints = {
        @UniqueConstraint(
                name = "UC_REQUEST_APPROVED", columnNames =
                {Request.REQUEST_BY_COLUMN_NAME, Request.APPROVED_BY_COLUMN_NAME})
})
public class Request {

    static final String REQUEST_BY_COLUMN_NAME = "request_by";
    static final String APPROVED_BY_COLUMN_NAME = "approved_by";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = REQUEST_BY_COLUMN_NAME, referencedColumnName = "id", nullable = false)
    private User requestedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_APPROVEDBY_USER"), name = APPROVED_BY_COLUMN_NAME)
    private User approvedBy;

    @Column(name = "approved_on")
    private Date approvedOn;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private RequestStatus status;
}
