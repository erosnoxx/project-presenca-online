package com.erosnoxx.presenca.core.domain.exceptions.entities.classroom;

import com.erosnoxx.presenca.core.domain.exceptions.common.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ClassroomAlreadyExistsException extends DomainException {
    private final String detail;

    public ClassroomAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pb.setTitle("classroom already exists");
        pb.setDetail(detail);

        return pb;
    }
}
