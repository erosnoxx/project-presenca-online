package com.erosnoxx.presenca.core.domain.exceptions.entities.student;

import com.erosnoxx.presenca.core.domain.exceptions.common.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class StudentNotFoundException extends DomainException {
    private final String detail;

    public StudentNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("student not found");
        pb.setDetail(detail);

        return pb;
    }
}
