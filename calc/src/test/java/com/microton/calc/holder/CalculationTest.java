package com.microton.calc.holder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Unit tests for Calculation {@link Calculation}
 *
 * @author Jan Šípek
 */
public class CalculationTest {

    private static Long LAST_UPDATE;
    private static List<String> HISTORY;
    private static final String CALCULATION = "1 + 2";
    private static BigDecimal RESULT;
    private static final Integer OPERATOR = 106;
    private static List<Integer> NUMBER;
    private static BigDecimal DISPLAYED_NUMBER;
    private static boolean COMPLETE_STATUS;

    @BeforeClass
    public void setUp() {
        LAST_UPDATE = System.currentTimeMillis();
        HISTORY = Arrays.asList(new String[]{"1 + 1 = 2"});
        RESULT = new BigDecimal(100);
        NUMBER = Arrays.asList(new Integer[]{52, 53});
        DISPLAYED_NUMBER = new BigDecimal(0);
        COMPLETE_STATUS = true;
    }

    @Test
    public void createInstance_test() {
        Calculation calc = new Calculation(LAST_UPDATE,
                HISTORY, CALCULATION, RESULT, OPERATOR,
                NUMBER, DISPLAYED_NUMBER, COMPLETE_STATUS);
        Assert.assertEquals(LAST_UPDATE, new Long(calc.getLastUpdate()));
        Assert.assertEquals(HISTORY, calc.getHistory());
        Assert.assertEquals(CALCULATION, calc.getCalculation());
        Assert.assertEquals(RESULT, calc.getResult());
        Assert.assertEquals(OPERATOR, calc.getOperator());
        Assert.assertEquals(NUMBER, calc.getNumber());
        Assert.assertEquals(DISPLAYED_NUMBER, calc.getDisplayedNumber());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createInstanceWithNullLastUpdate_test() {
        Calculation calc = new Calculation(null, HISTORY, CALCULATION,
                RESULT, OPERATOR, NUMBER, DISPLAYED_NUMBER, COMPLETE_STATUS);
    }

    @Test
    public void setLastUpdate_test() {
        Calculation calc = new Calculation(LAST_UPDATE,
                HISTORY, CALCULATION, RESULT, OPERATOR,
                NUMBER, DISPLAYED_NUMBER, COMPLETE_STATUS);
        Long lastUpdate = System.currentTimeMillis();
        calc.setLastUpdate(lastUpdate);
        Assert.assertEquals(lastUpdate, new Long(calc.getLastUpdate()));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void setNullLastUpdate_test() {
        Calculation calc = new Calculation(LAST_UPDATE,
                HISTORY, CALCULATION, RESULT, OPERATOR,
                NUMBER, DISPLAYED_NUMBER, COMPLETE_STATUS);
        calc.setLastUpdate(null);
    }

    @Test
    public void setHistory_test() {
        Calculation calc = new Calculation(LAST_UPDATE,
                HISTORY, CALCULATION, RESULT, OPERATOR,
                NUMBER, DISPLAYED_NUMBER, COMPLETE_STATUS);
        List<String> history = new ArrayList<>();
        history.add("3 + 3 = 6");
        calc.setHistory(history);
        Assert.assertEquals(history, calc.getHistory());
    }

    @Test
    public void setCalculation_test() {
        Calculation calc = new Calculation(LAST_UPDATE,
                HISTORY, CALCULATION, RESULT, OPERATOR,
                NUMBER, DISPLAYED_NUMBER, COMPLETE_STATUS);
        String calculation = "3 + 3 = 6";
        calc.setCalculation(calculation);
        Assert.assertEquals(calculation, calc.getCalculation());
    }

    @Test
    public void setResult_test() {
        Calculation calc = new Calculation(LAST_UPDATE,
                HISTORY, CALCULATION, RESULT, OPERATOR,
                NUMBER, DISPLAYED_NUMBER, COMPLETE_STATUS);
        BigDecimal result = new BigDecimal(20);
        calc.setResult(result);
        Assert.assertEquals(result, calc.getResult());
    }

    @Test
    public void setOperator_test() {
        Calculation calc = new Calculation(LAST_UPDATE,
                HISTORY, CALCULATION, RESULT, OPERATOR,
                NUMBER, DISPLAYED_NUMBER, COMPLETE_STATUS);
        Integer operator = 107;
        calc.setOperator(operator);
        Assert.assertEquals(operator, calc.getOperator());
    }

    @Test
    public void setNumber_test() {
        Calculation calc = new Calculation(LAST_UPDATE,
                HISTORY, CALCULATION, RESULT, OPERATOR,
                NUMBER, DISPLAYED_NUMBER, COMPLETE_STATUS);
        List<Integer> number = Arrays.asList(new Integer[]{50, 51});
        calc.setNumber(number);
        Assert.assertEquals(number, calc.getNumber());
    }

    @Test
    public void setDisplayedNumber_test() {
        Calculation calc = new Calculation(LAST_UPDATE,
                HISTORY, CALCULATION, RESULT, OPERATOR,
                NUMBER, DISPLAYED_NUMBER, COMPLETE_STATUS);
        BigDecimal displayedNumber = new BigDecimal(1);
        calc.setDisplayedNumber(displayedNumber);
        Assert.assertEquals(displayedNumber, calc.getDisplayedNumber());
    }
}
