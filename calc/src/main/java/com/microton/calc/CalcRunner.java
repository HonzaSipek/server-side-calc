package com.microton.calc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * Runs calculator service.
 *
 * @author Jan Šípek
 */
@SpringBootApplication
@ImportResource("classpath:calc-context.xml")
public class CalcRunner {

    private static final Logger LOG = LogManager.getLogger(
            CalcRunner.class.getName());

    /**
     * Main method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {

        LOG.info("Starting Calc service");
        ApplicationContext ctx = SpringApplication.run(
                CalcRunner.class, args);

    }

}
