package com.erosnoxx.presenca.infrastructure.exceptions;

import com.erosnoxx.presenca.core.domain.exceptions.common.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InternalServerErrorException extends DomainException {
    private final String detail;

    public InternalServerErrorException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setTitle("internal server error");
        pb.setDetail(detail);
        return pb;
    }
}
