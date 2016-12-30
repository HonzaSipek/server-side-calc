package com.microton.calc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an error causes the UUID is invalid.
 *
 * @author Jan Šípek
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST,
        reason = "Invalid UUID")
public class InvalidUuidException extends RuntimeException {

    public InvalidUuidException(String message) {
        super(message);
    }
}
