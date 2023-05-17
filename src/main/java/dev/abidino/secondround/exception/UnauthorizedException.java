package dev.abidino.secondround.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends AbstractException {
    public UnauthorizedException(String message) {
        super(message);
    }

    @Override
    HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
