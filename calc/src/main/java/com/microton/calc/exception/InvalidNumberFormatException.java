package com.microton.calc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an error causes the number format is invalid.
 *
 * @author Jan Šípek
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST,
        reason = "Invalid number format")
public class InvalidNumberFormatException extends RuntimeException {

    public InvalidNumberFormatException(String message) {
        super(message);
    }
}
