package com.workintech.jpa.fswebs18d1.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class BurgerErrorException extends RuntimeException{
    private HttpStatus httpStatus;

    public BurgerErrorException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
