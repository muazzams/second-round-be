package dev.abidino.secondround.exception;

import org.springframework.http.HttpStatus;

public final class BulletBoxNotFoundException extends AbstractException {
    public BulletBoxNotFoundException(String message) {
        super(message);
    }

    @Override
    HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
