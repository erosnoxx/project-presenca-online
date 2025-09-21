package com.erosnoxx.presenca.core.domain.exceptions.entities.student;

import com.erosnoxx.presenca.core.domain.exceptions.common.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class StudentAlreadyExistsInClassRoomException extends DomainException {
    private final String detail;

    public StudentAlreadyExistsInClassRoomException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("classroom consistency error");
        pb.setDetail(detail);

        return pb;
    }
}
