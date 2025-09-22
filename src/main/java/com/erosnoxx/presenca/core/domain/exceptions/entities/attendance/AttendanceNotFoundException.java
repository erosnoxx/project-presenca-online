package com.erosnoxx.presenca.core.domain.exceptions.entities.attendance;

import com.erosnoxx.presenca.core.domain.exceptions.common.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AttendanceNotFoundException extends DomainException {
    private final String detail;

    public AttendanceNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("attendance not found");
        pb.setDetail(detail);

        return pb;
    }
}
