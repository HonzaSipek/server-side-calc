package com.microton.calc.controller;

import com.microton.calc.dto.MathRequest;
import com.microton.calc.dto.MathResponse;
import com.microton.calc.service.MathService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for math operation.
 *
 * @author Jan Šípek
 */
@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    private MathService mathService;

    private static final Logger LOG = LogManager.getLogger(
            MathController.class.getName());

    /**
     * Initializes calculation session.
     *
     * @return UUID
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ResponseEntity<String> initSession() {
        String uuid = mathService.initSession();
        LOG.info("Init communication session UUID: {}", uuid);
        return new ResponseEntity(uuid, HttpStatus.OK);
    }

    /**
     * Computes math request with key code and UUID.
     *
     * @param mathRequest math request data transfer object
     * @return response math response data transfer object
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<MathResponse> computeMathRequest(
            @RequestBody MathRequest mathRequest) {
        LOG.info("Handling math request: {}", mathRequest);
        MathResponse response = mathService.computeMathRequest(mathRequest);
        LOG.info("Math response: {}", response);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
