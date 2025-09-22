package com.erosnoxx.presenca.core.domain.exceptions.value_objects;

import com.erosnoxx.presenca.core.domain.exceptions.common.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidNameException extends DomainException {
    private final String detail;

    public InvalidNameException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Invalid name");
        pb.setDetail(detail);

        return pb;
    }
}
