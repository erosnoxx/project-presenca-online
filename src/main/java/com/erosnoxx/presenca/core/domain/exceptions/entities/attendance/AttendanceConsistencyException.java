package com.erosnoxx.presenca.core.domain.exceptions.entities.attendance;

import com.erosnoxx.presenca.core.domain.exceptions.common.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AttendanceConsistencyException extends DomainException {
    private final String detail;

    public AttendanceConsistencyException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("attendance consistency error");
        pb.setDetail(detail);

        return pb;
    }
}
