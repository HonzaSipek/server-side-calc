package com.microton.calc.util;

import com.microton.calc.exception.InvalidKeyException;
import com.microton.calc.exception.InvalidNumberFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Unit test for number utilities.
 *
 * @author Jan Šípek
 */
public class NumberUtilTest {

    @Test
    public void getNumberFromKeyCodes_fromNumKeys_Test() {

        // 123
        Integer[] numberArray = new Integer[]{49, 50, 51};
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(
                Arrays.asList(numberArray));
        Assert.assertEquals(number, new BigDecimal("123"));
    }

    @Test
    public void getNumberFromKeyCodes_fromNumpadKeys_Test() {

        // 123
        Integer[] numberArray = new Integer[]{97, 98, 99};
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(
                Arrays.asList(numberArray));
        Assert.assertEquals(number, new BigDecimal("123"));
    }

    @Test
    public void getNumberFromKeyCodes_withZerosAtStart_Test() {

        // 000123
        Integer[] numberArray = new Integer[]{48, 48, 48, 49, 50, 51};
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(
                Arrays.asList(numberArray));
        Assert.assertEquals(number, new BigDecimal("123"));
    }

    @Test
    public void getNumberFromKeyCodes_longNumber_Test() {

        // 1 000 000 000 000 000 000 000 000 000 000 (more than long)
        Integer[] numberArray = new Integer[]{49, 48, 48, 48, 48, 48, 48, 48,
            48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48,
            48, 48, 48, 48, 48, 48};
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(
                Arrays.asList(numberArray));
        Assert.assertEquals(number,
                new BigDecimal("1000000000000000000000000000000"));
    }

    @Test
    public void getNumberFromKeyCodes_withComma_Test() {

        // 12,3
        Integer[] numberArray = new Integer[]{49, 50, 188, 51};
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(
                Arrays.asList(numberArray));
        Assert.assertEquals(number, new BigDecimal("12.3"));
    }

    @Test
    public void getNumberFromKeyCodes_withCommaAtTheEnd_Test() {

        // 12,
        Integer[] numberArray = new Integer[]{49, 50, 188};
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(
                Arrays.asList(numberArray));
        Assert.assertEquals(number, new BigDecimal("12"));
    }

    @Test
    public void getNumberFromKeyCodes_withCommaAtTheStart_Test() {

        // ,12
        Integer[] numberArray = new Integer[]{188, 49, 50};
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(
                Arrays.asList(numberArray));
        Assert.assertEquals(number, new BigDecimal("0.12"));
    }

    @Test
    public void getNumberFromKeyCodes_withDecimalPoint_Test() {

        // 12.3
        Integer[] numberArray = new Integer[]{49, 50, 110, 51};
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(
                Arrays.asList(numberArray));
        Assert.assertEquals(number, new BigDecimal("12.3"));
    }

    @Test
    public void getNumberFromKeyCodes_withDecimalPointAtTheEnd_Test() {

        // 12.
        Integer[] numberArray = new Integer[]{49, 50, 110};
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(
                Arrays.asList(numberArray));
        Assert.assertEquals(number, new BigDecimal("12"));
    }

    @Test(expectedExceptions = InvalidKeyException.class)
    public void getNumberFromKeyCodes_withInvalidChars_Test() {

        // 12s
        Integer[] numberArray = new Integer[]{49, 50, 83};
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(
                Arrays.asList(numberArray));
    }

    @Test(expectedExceptions = InvalidNumberFormatException.class)
    public void getNumberFromKeyCodes_withTwoComma_Test() {

        // 12,3,0
        Integer[] numberArray = new Integer[]{49, 50, 188, 51, 188, 48};
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(
                Arrays.asList(numberArray));
    }
}
