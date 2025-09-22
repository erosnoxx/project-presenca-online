package com.erosnoxx.presenca.infrastructure.exceptions;

import com.erosnoxx.presenca.core.domain.exceptions.common.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EntityCannotBeMappedException extends DomainException {
    private final String detail;

    public EntityCannotBeMappedException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("entity cannot be mapped");
        pb.setDetail(detail);
        return pb;
    }
}
