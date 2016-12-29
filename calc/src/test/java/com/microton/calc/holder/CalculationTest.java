package com.microton.calc.holder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for Calculation {@link Calculation}
 *
 * @author Jan Šípek
 */
public class CalculationTest {

    @Test
    public void createInstance_test() {
        Long lastUpdate = System.currentTimeMillis();
        Calculation calc = new Calculation(lastUpdate,
                new ArrayList<>(), "", new BigDecimal(10), 50,
                new ArrayList<>(), new BigDecimal(0));
        Assert.assertEquals((long) lastUpdate, calc.getLastUpdate());
        Assert.assertEquals(new ArrayList<>(), calc.getHistory());
        Assert.assertEquals("", calc.getCalculation());
        Assert.assertEquals(new BigDecimal(10), calc.getResult());
        Assert.assertEquals(new Integer(50), calc.getOperator());
        Assert.assertEquals(new ArrayList<>(), calc.getNumber());
        Assert.assertEquals(new BigDecimal(0), calc.getDisplayedNumber());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createInstanceWithNullLastUpdate_test() {
        Calculation calc = new Calculation(null, new ArrayList<>(), "",
                new BigDecimal(10), 50, new ArrayList<>(), new BigDecimal(0));
    }

    @Test
    public void setLastUpdate_test() {
        Long lastUpdate = System.currentTimeMillis();
        Calculation calc = new Calculation(lastUpdate,
                new ArrayList<>(), "", new BigDecimal(10), 50,
                new ArrayList<>(), new BigDecimal(0));
        lastUpdate = System.currentTimeMillis();
        calc.setLastUpdate(lastUpdate);
        Assert.assertEquals((long) lastUpdate, calc.getLastUpdate());
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void setNullLastUpdate_test() {
        Long lastUpdate = System.currentTimeMillis();
        Calculation calc = new Calculation(lastUpdate,
                new ArrayList<>(), "", new BigDecimal(10), 50,
                new ArrayList<>(), new BigDecimal(0));
        calc.setLastUpdate(null);
    }

    @Test
    public void setHistory_test() {
        Long lastUpdate = System.currentTimeMillis();
        Calculation calc = new Calculation(lastUpdate,
                new ArrayList<>(), "", new BigDecimal(10), 50,
                new ArrayList<>(), new BigDecimal(0));
        List<String> history = new ArrayList<>();
        history.add("3 + 3 = 6");
        calc.setHistory(history);
        Assert.assertEquals(history, calc.getHistory());
    }

    @Test
    public void setCalculation_test() {
        Long lastUpdate = System.currentTimeMillis();
        Calculation calc = new Calculation(lastUpdate,
                new ArrayList<>(), "", new BigDecimal(10), 50,
                new ArrayList<>(), new BigDecimal(0));
        String calculation = "3 + 3 = 6";
        calc.setCalculation(calculation);
        Assert.assertEquals(calculation, calc.getCalculation());
    }

    @Test
    public void setResult_test() {
        Long lastUpdate = System.currentTimeMillis();
        Calculation calc = new Calculation(lastUpdate,
                new ArrayList<>(), "", new BigDecimal(10), 50,
                new ArrayList<>(), new BigDecimal(0));
        BigDecimal result = new BigDecimal(20);
        calc.setResult(result);
        Assert.assertEquals(result, calc.getResult());
    }

    @Test
    public void setOperator_test() {
        Long lastUpdate = System.currentTimeMillis();
        Calculation calc = new Calculation(lastUpdate,
                new ArrayList<>(), "", new BigDecimal(10), 50,
                new ArrayList<>(), new BigDecimal(0));
        Integer operator = 40;
        calc.setOperator(operator);
        Assert.assertEquals(operator, calc.getOperator());
    }

    @Test
    public void setNumber_test() {
        Long lastUpdate = System.currentTimeMillis();
        Calculation calc = new Calculation(lastUpdate,
                new ArrayList<>(), "", new BigDecimal(10), 50,
                new ArrayList<>(), new BigDecimal(0));
        List<Integer> number = Arrays.asList(new Integer[]{50, 51});
        calc.setNumber(number);
        Assert.assertEquals(number, calc.getNumber());
    }

    @Test
    public void setDisplayedNumber_test() {
        Long lastUpdate = System.currentTimeMillis();
        Calculation calc = new Calculation(lastUpdate,
                new ArrayList<>(), "", new BigDecimal(10), 50,
                new ArrayList<>(), new BigDecimal(0));
        BigDecimal displayedNumber = new BigDecimal(1);
        calc.setDisplayedNumber(displayedNumber);
        Assert.assertEquals(displayedNumber, calc.getDisplayedNumber());
    }
}
