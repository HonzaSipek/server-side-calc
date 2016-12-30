package com.microton.calc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an error causes the key is invalid.
 *
 * @author Jan Šípek
 */

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE,
        reason = "Division by zero. Enter a different number.")
public class DivisionByZeroException extends RuntimeException {
    
    public DivisionByZeroException(String message) {
        super(message);
    }
    
    public DivisionByZeroException(Throwable cause) {
        super(cause);
    }
}
