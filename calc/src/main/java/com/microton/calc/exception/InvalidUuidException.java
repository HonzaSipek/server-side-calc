/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
