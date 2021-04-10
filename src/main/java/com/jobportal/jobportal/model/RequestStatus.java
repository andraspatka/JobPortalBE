package com.jobportal.jobportal.model;

import lombok.Getter;

/**
 * Available statuses for a {@link Request}
 *
 * @since 10.04.2021
 */
public enum RequestStatus {
    PENDING(0),
    APPROVED(1),
    DECLINED(2);

    @Getter
    private int status;

    RequestStatus(int status) {
        this.status = status;
    }
}
