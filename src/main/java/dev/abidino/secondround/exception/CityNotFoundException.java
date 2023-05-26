package dev.abidino.secondround.exception;

import org.springframework.http.HttpStatus;

public class CityNotFoundException extends AbstractException {
    public CityNotFoundException(String message) {
        super(message);
    }

    @Override
    HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
