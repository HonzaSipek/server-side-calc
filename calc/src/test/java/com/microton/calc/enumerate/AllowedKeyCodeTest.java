/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microton.calc.enumerate;

import com.microton.calc.exception.InvalidKeyException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for allowed key code enumerate {@link AllowedKeyCode}
 *
 * @author Jan Šípek
 */
public class AllowedKeyCodeTest {

    @Test
    public void getNumericByNumTwo_test() {
        AllowedKeyCode keyCode = AllowedKeyCode.valueOf(50);
        Assert.assertTrue(keyCode.isNumeric());
        Assert.assertTrue(!keyCode.isOperator());
        Assert.assertTrue(!keyCode.isAction());
        Assert.assertEquals("2", keyCode.getKeyCodeEnum().getKeySymbol());
    }

    @Test
    public void getNumericByNumPadTwo_test() {
        AllowedKeyCode keyCode = AllowedKeyCode.valueOf(98);
        Assert.assertTrue(keyCode.isNumeric());
        Assert.assertTrue(!keyCode.isOperator());
        Assert.assertTrue(!keyCode.isAction());
        Assert.assertEquals("2", keyCode.getKeyCodeEnum().getKeySymbol());
    }

    @Test
    public void getNumericByNumComma_test() {
        AllowedKeyCode keyCode = AllowedKeyCode.valueOf(188);
        Assert.assertTrue(keyCode.isNumeric());
        Assert.assertTrue(!keyCode.isOperator());
        Assert.assertTrue(!keyCode.isAction());
        Assert.assertEquals(".", keyCode.getKeyCodeEnum().getKeySymbol());
    }

    @Test
    public void getOperatorByAdd_test() {
        AllowedKeyCode keyCode = AllowedKeyCode.valueOf(107);
        Assert.assertTrue(!keyCode.isNumeric());
        Assert.assertTrue(keyCode.isOperator());
        Assert.assertTrue(!keyCode.isAction());
        Assert.assertEquals("+", keyCode.getKeyCodeEnum().getKeySymbol());
    }

    @Test
    public void getActionByDel_test() {
        AllowedKeyCode keyCode = AllowedKeyCode.valueOf(46);
        Assert.assertTrue(!keyCode.isNumeric());
        Assert.assertTrue(!keyCode.isOperator());
        Assert.assertTrue(keyCode.isAction());
        Assert.assertEquals("DEL", keyCode.getKeyCodeEnum().getKeySymbol());
    }

    @Test(expectedExceptions = InvalidKeyException.class)
    public void forbidden_test() {
        AllowedKeyCode.valueOf(30);
    }
}
