package com.erosnoxx.presenca.core.domain.exceptions.entities.classroom;

import com.erosnoxx.presenca.core.domain.exceptions.common.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ClassroomNotFoundException extends DomainException {
    private final String detail;

    public ClassroomNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("classroom not found");
        pb.setDetail(detail);

        return pb;
    }
}
