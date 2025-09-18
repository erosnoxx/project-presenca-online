package com.erosnoxx.presenca.core.domain.exceptions.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DomainException extends RuntimeException {
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Business rule error");
        return pb;
    }
}
