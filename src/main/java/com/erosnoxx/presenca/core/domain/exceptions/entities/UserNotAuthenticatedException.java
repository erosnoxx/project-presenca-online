package com.erosnoxx.presenca.core.domain.exceptions.entities;

import com.erosnoxx.presenca.core.domain.exceptions.common.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotAuthenticatedException extends DomainException {
    private final String detail;

    public UserNotAuthenticatedException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        pb.setTitle("access denied");
        pb.setDetail(detail);

        return pb;
    }
}
