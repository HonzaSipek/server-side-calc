package com.microton.calc.service;

import com.microton.calc.dto.MathRequest;
import com.microton.calc.dto.MathResponse;

/**
 * Interface of math service.
 *
 * @author Jan Šípek
 */
public interface MathService {

    /**
     * Initializes communication session.
     *
     * @return UUID
     */
    public String initSession();

    /**
     * Handles math request data transfer object.
     *
     * @param request math request
     * @return math response data transfer object
     */
    public MathResponse computeMathRequest(MathRequest request);
}
