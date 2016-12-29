package com.microton.calc.util;

import com.microton.calc.enumerate.AllowedKeyCode;
import com.microton.calc.exception.InvalidKeyException;
import com.microton.calc.exception.InvalidNumberFormatException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Number utilities.
 *
 * @author Jan Šípek
 */
public class NumberUtil {

    /**
     * Gets number from array of key codes.
     *
     * @param keyCodes array of key codes
     * @return number
     * @throws InvalidKeyException if key is invalid or empty
     * @throws InvalidNumberFormatException if number format is invalid
     */
    public static BigDecimal getNumberFromKeyCodes(List<Integer> keyCodes)
            throws InvalidKeyException, InvalidNumberFormatException {
        BigDecimal number = null;
        StringBuilder stringNumber = new StringBuilder();
        for (Integer keyCode : keyCodes) {
            AllowedKeyCode code = AllowedKeyCode.valueOf(keyCode);
            if (code.isNumeric()) {
                stringNumber.append(code.getKeyCodeEnum().getKeySymbol());
            } else {
                throw new InvalidKeyException(
                        String.format("Invalid key: %s",
                                code.getKeyCodeEnum().getKeySymbol()));
            }
        }
        try {
            number = new BigDecimal(stringNumber.toString());
        } catch (NumberFormatException ex) {
            throw new InvalidNumberFormatException(
                    String.format("Invalid number format: %s",
                            stringNumber.toString()));
        }
        return number;
    }
}
