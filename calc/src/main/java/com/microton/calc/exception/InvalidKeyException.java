package com.microton.calc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an error causes the key is invalid.
 *
 * @author Jan Šípek
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST,
        reason = "Invalid key")
public class InvalidKeyException extends RuntimeException {

    public InvalidKeyException(String message) {
        super(message);
    }
}
