package dev.abidino.secondround.exception;

import org.springframework.http.HttpStatus;

public class DistrictNotFoundException extends AbstractException{

    public DistrictNotFoundException(String message) {
        super(message);
    }

    @Override
    HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
