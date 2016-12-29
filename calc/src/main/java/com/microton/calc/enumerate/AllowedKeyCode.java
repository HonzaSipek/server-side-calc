package com.microton.calc.enumerate;

import com.microton.calc.exception.InvalidKeyException;
import java.awt.event.KeyEvent;

/**
 * Enumerate of allowed key codes.
 *
 * @author Jan Šípek
 */
public class AllowedKeyCode {

    private final KeyCode keyCode;

    /**
     * Creates instance of allowed key code.
     *
     * @param keyCode key code
     */
    public AllowedKeyCode(KeyCode keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * Checks numeric type.
     *
     * @return check status
     */
    public boolean isNumeric() {
        return keyCode instanceof Numeric;
    }

    /**
     * Checks operator type.
     *
     * @return true if key code is operator
     */
    public boolean isOperator() {
        return keyCode instanceof Operator;
    }

    /**
     * Checks action type.
     *
     * @return true if key code is action
     */
    public boolean isAction() {
        return keyCode instanceof Action;
    }

    /**
     * Gets key code.
     *
     * @return key code
     */
    public int getKeyCode() {

        return keyCode.getKeyCode();
    }

    /**
     * Gets key code enumerate.
     *
     * @return key code enumerate
     */
    public KeyCode getKeyCodeEnum() {
        return keyCode;
    }

    /**
     * Get allowed key code object from key code.
     *
     * @param keyCode key code
     * @return allowed key code
     * @throws InvalidKeyException if key is invalid or empty
     */
    public static AllowedKeyCode valueOf(Integer keyCode)
            throws InvalidKeyException {

        if (keyCode == null) {
            throw new InvalidKeyException("Empty key code");
        }

        for (final Numeric item : Numeric.values()) {
            if (item.getKeyCode() == keyCode) {
                return new AllowedKeyCode(item);
            }
        }

        for (final Operator item : Operator.values()) {
            if (item.getKeyCode() == keyCode) {
                return new AllowedKeyCode(item);
            }
        }

        for (final Action item : Action.values()) {
            if (item.getKeyCode() == keyCode) {
                return new AllowedKeyCode(item);
            }
        }
        throw new InvalidKeyException(
                String.format("Invalid key: %s with code: %d",
                        KeyEvent.getKeyText(keyCode), keyCode));
    }

    /**
     * Interface of key code enumerate.
     */
    public interface KeyCode {

        /**
         * Gets key code.
         *
         * @return key code
         */
        public int getKeyCode();

        /**
         * Gets key symbol.
         *
         * @return key symbol
         */
        public String getKeySymbol();
    }

    /**
     * Enumerate of numeric key codes.
     */
    public enum Numeric implements KeyCode {

        NUM_ZERO(48, "0"),
        NUM_ONE(49, "1"),
        NUM_TWO(50, "2"),
        NUM_THREE(51, "3"),
        NUM_FOUR(52, "4"),
        NUM_FIVE(53, "5"),
        NUM_SIX(54, "6"),
        NUM_SEVEN(55, "7"),
        NUM_EIGHT(56, "8"),
        NUM_NINE(57, "9"),
        NUM_NUMPAD_ZERO(96, "0"),
        NUM_NUMPAD_ONE(97, "1"),
        NUM_NUMPAD_TWO(98, "2"),
        NUM_NUMPAD_THREE(99, "3"),
        NUM_NUMPAD_FOUR(100, "4"),
        NUM_NUMPAD_FIVE(101, "5"),
        NUM_NUMPAD_SIX(102, "6"),
        NUM_NUMPAD_SEVEN(103, "7"),
        NUM_NUMPAD_EIGHT(104, "8"),
        NUM_NUMPAD_NINE(105, "9"),
        DELIMETER_POINT(110, "."),
        DELIMETER_COMMA(188, "."),
        DELIMETER_PERIOD(190, ".");

        private final int keyCode;
        private final String keySymbol;

        /**
         * Creates instance of Numeric enumerate.
         *
         * @param keyCode key code
         * @param keySymbol key symbol
         */
        private Numeric(int keyCode, String keySymbol) {
            this.keyCode = keyCode;
            this.keySymbol = keySymbol;
        }

        @Override
        public int getKeyCode() {
            return keyCode;
        }

        @Override
        public String getKeySymbol() {
            return keySymbol;
        }
    }

    /**
     * Enumerate of operator key codes.
     */
    public enum Operator implements KeyCode {

        NUM_ADD(107, "+"),
        NUM_SUBTRACT(109, "-"),
        NUM_MILTIPLY(106, "*"),
        NUM_DIVIDE(111, "/");

        private final int keyCode;
        private final String keySymbol;

        /**
         * Creates instance of Operator enumerate.
         *
         * @param keyCode key code
         * @param keySymbol key symbol
         */
        private Operator(int keyCode, String keySymbol) {
            this.keyCode = keyCode;
            this.keySymbol = keySymbol;
        }

        @Override
        public int getKeyCode() {
            return keyCode;
        }

        @Override
        public String getKeySymbol() {
            return keySymbol;
        }
    }

    /**
     * Enumerate of action key codes.
     */
    public enum Action implements KeyCode {

        ACTION_ESC(27, "ESC"),
        ACTION_DEL(46, "DEL"),
        ACTION_ENTER(13, "ENTER");

        private final int keyCode;
        private final String keySymbol;

        /**
         * Creates instance of Action enumerate.
         *
         * @param keyCode key code
         * @param keySymbol key symbol
         */
        private Action(int keyCode, String keySymbol) {
            this.keyCode = keyCode;
            this.keySymbol = keySymbol;
        }

        @Override
        public int getKeyCode() {
            return keyCode;
        }
        
        @Override
        public String getKeySymbol() {
            return keySymbol;
        }
    }
}
