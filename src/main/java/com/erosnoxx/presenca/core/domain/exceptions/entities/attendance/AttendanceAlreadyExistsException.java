package com.erosnoxx.presenca.core.domain.exceptions.entities.attendance;

import com.erosnoxx.presenca.core.domain.exceptions.common.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AttendanceAlreadyExistsException extends DomainException {
    private final String detail;

    public AttendanceAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pb.setTitle("attendance already exists");
        pb.setDetail(detail);

        return pb;
    }
}
