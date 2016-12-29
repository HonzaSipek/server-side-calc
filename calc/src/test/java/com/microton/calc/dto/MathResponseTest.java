/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microton.calc.dto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Unit tests for math response data transfer object {@link MathResult}
 *
 * @author Jan Šípek
 */
public class MathResponseTest {
    
    private static List<String> HISTORY;
    private static BigDecimal NUMBER;
    
    @BeforeClass
    public void setUp(){
        HISTORY = Arrays.asList(new String[]{"1 + 1 = 2"});
        NUMBER = new BigDecimal(0);
    }

    @Test
    public void createInstance_test() {
        MathResponse response = ImmutableMathResponse.builder()
                .history(HISTORY)
                .number(NUMBER)
                .build();
        Assert.assertEquals(HISTORY, response.getHistory());
        Assert.assertEquals(NUMBER, response.getNumber());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createInstanceWihtNullNumber_test() {
        MathResponse response = ImmutableMathResponse.builder()
                .history(HISTORY)
                .number(null)
                .build();
    }

    @Test
    public void getHistory_test() {
        MathResponse response = ImmutableMathResponse.builder()
                .history(HISTORY)
                .number(NUMBER)
                .build();
        Assert.assertEquals(HISTORY, response.getHistory());
    }

    @Test
    public void getNumber_test() {
        MathResponse response = ImmutableMathResponse.builder()
                .history(HISTORY)
                .number(NUMBER)
                .build();
        Assert.assertEquals(NUMBER, response.getNumber());
    }
}
