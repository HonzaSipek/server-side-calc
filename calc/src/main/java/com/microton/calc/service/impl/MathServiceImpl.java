package com.microton.calc.service.impl;

import com.microton.calc.dto.ImmutableMathResponse;
import com.microton.calc.dto.MathRequest;
import com.microton.calc.dto.MathResponse;
import com.microton.calc.enumerate.AllowedKeyCode;
import com.microton.calc.exception.DivisionByZeroException;
import com.microton.calc.exception.InvalidUuidException;
import com.microton.calc.holder.Calculation;
import com.microton.calc.holder.Holder;
import com.microton.calc.service.MathService;
import com.microton.calc.util.NumberUtil;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Implementation of math operation service.
 *
 * @author Jan Šípek
 */
@Service
public class MathServiceImpl implements MathService {

    private Holder holder;

    private static final Logger LOG = LogManager.getLogger(
            MathServiceImpl.class.getName());

    /**
     * Sets calculation holder.
     *
     * @param holder calculation holder
     */
    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    @Override
    public String initSession() {
        String uuid = UUID.randomUUID().toString();
        Calculation calc = new Calculation(
                System.currentTimeMillis(), new ArrayList<>(), "",
                null, null, new ArrayList<>(), new BigDecimal(0));
        holder.updateCalculatin(uuid, calc);
        return uuid;
    }

    @Override
    public MathResponse computeMathRequest(MathRequest request) {
        if (!holder.checkUuid(request.getUuid())) {
            LOG.warn("Invaid UUID: {}", request.getUuid());
            throw new InvalidUuidException("Invalid UUID");
        } else {
            Calculation calc = holder.getCalculation(request.getUuid());
            LOG.info("Calc before math request: {}", calc);
            calc.setLastUpdate(System.currentTimeMillis());
            AllowedKeyCode code = AllowedKeyCode.valueOf(request.getKeyCode());

            if (code.isNumeric()) {
                // Add number to array (number builder)
                calc = addNumberToArray(calc, code);
            } else if (code.isOperator()) {

                if (calc.getNumber().isEmpty()
                        && calc.getResult() != null) {
                    // Update actual operator
                    calc = updateOperator(calc, code);
                } else if (!calc.getNumber().isEmpty()
                        && calc.getResult() == null) {
                    // Init result after first complete number and add operator
                    calc = initResult(calc);
                    calc = updateOperator(calc, code);
                } else if (calc.getOperator() != null) {
                    // Compute result after adding other operator
                    calc = computeResult(calc, code);
                }

            } else if (code.isAction()) {
                switch ((AllowedKeyCode.Action) code.getKeyCodeEnum()) {
                    case ACTION_DEL:
                        calc = clearNumberArray(calc);
                        break;
                    case ACTION_ESC:
                        calc = escapeCalculation(calc);
                        break;
                    case ACTION_ENTER:
                        calc = computeCompleteResult(calc);
                        break;
                }
            }
            LOG.info("Calc after math request: {}", calc);
            holder.updateCalculatin(request.getUuid(), calc);
            return ImmutableMathResponse.builder()
                    .history(calc.getHistory())
                    .number(calc.getDisplayedNumber())
                    .build();
        }
    }

    /**
     * Add number of key code to array.
     *
     * @param calc actual calculation
     * @param keyCode allowed numeric key code
     * @return updated calculation
     */
    private Calculation addNumberToArray(Calculation calc,
            AllowedKeyCode keyCode) {
        List<Integer> numberArray = calc.getNumber();
        numberArray.add(keyCode.getKeyCode());
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(numberArray);
        calc.setNumber(numberArray);
        calc.setDisplayedNumber(number);
        return calc;
    }

    /**
     * Clears number array.
     *
     * @param calc actual calculation
     * @return updated calculation
     */
    private Calculation clearNumberArray(Calculation calc) {
        calc.setNumber(new ArrayList<>());
        calc.setDisplayedNumber(new BigDecimal(0));
        return calc;
    }

    /**
     * Initializes result.
     *
     * @param calc actual calculation
     * @return update calculation
     */
    private Calculation initResult(Calculation calc) {
        List<Integer> numberArray = calc.getNumber();
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(numberArray);
        calc.setResult(number);
        calc.setNumber(new ArrayList<>());
        StringBuilder calculation = new StringBuilder();
        calculation.append(number);
        calculation.append(" ");
        calc.setCalculation(calculation.toString());
        return calc;
    }

    /**
     * Updates actual operator.
     *
     * @param calc actual calculation
     * @param code allowed operator key code
     * @return updated calculation
     */
    private Calculation updateOperator(Calculation calc, AllowedKeyCode code) {
        calc.setOperator(code.getKeyCode());
        return calc;
    }

    /**
     * Computes result.
     *
     * @param calc actual calculation
     * @param code allowed numeric key code
     * @return updated calculation
     */
    private Calculation computeResult(Calculation calc, AllowedKeyCode code) {
        BigDecimal number = NumberUtil.getNumberFromKeyCodes(
                calc.getNumber());
        BigDecimal actualResult = calc.getResult();
        StringBuilder calculation = new StringBuilder(
                calc.getCalculation());
        AllowedKeyCode operatorCode = AllowedKeyCode.valueOf(calc.getOperator());
        switch ((AllowedKeyCode.Operator) operatorCode.getKeyCodeEnum()) {
            case NUM_ADD:
                actualResult = add(actualResult, number);
                break;
            case NUM_SUBTRACT:
                actualResult = subtract(actualResult, number);
                break;
            case NUM_MILTIPLY:
                actualResult = multiply(actualResult, number);
                break;
            case NUM_DIVIDE:
                actualResult = divide(actualResult, number);
                break;
        }
        calculation.append(operatorCode.getKeyCodeEnum().getKeySymbol());
        calculation.append(" ");
        calculation.append(number);
        calculation.append(" ");
        calc.setCalculation(calculation.toString());
        calc.setNumber(new ArrayList<>());
        calc.setResult(actualResult);
        calc.setDisplayedNumber(actualResult);
        calc.setOperator(code.getKeyCode());
        return calc;
    }

    /**
     * Computes complete result.
     *
     * @param calc actual calculation
     * @return updated calculation
     */
    private Calculation computeCompleteResult(Calculation calc) {

        if (!calc.getNumber().isEmpty() && calc.getOperator() != null) {
            AllowedKeyCode operatorCode = AllowedKeyCode.valueOf(
                    calc.getOperator());
            calc = computeResult(calc, operatorCode);
        } else if (calc.getResult() == null) {
            calc = initResult(calc);
        } else {
            return calc;
        }

        StringBuilder calculation = new StringBuilder(calc.getCalculation());
        calculation.append("= ");
        calculation.append(calc.getResult());
        List<String> history = calc.getHistory();
        history.add(calculation.toString());
        calc.setNumber(new ArrayList<>());
        calc.setHistory(history);
        calc.setCalculation("");
        calc.setDisplayedNumber(calc.getResult());
        calc.setResult(null);
        calc.setOperator(null);
        return calc;
    }

    /**
     * Escapes actual calculation.
     *
     * @param calc actual calculation
     * @return update calculation
     */
    private Calculation escapeCalculation(Calculation calc) {
        calc.setNumber(new ArrayList<>());
        calc.setOperator(null);
        calc.setResult(null);
        calc.setDisplayedNumber(new BigDecimal(0));
        calc.setCalculation("");
        return calc;
    }

    /**
     * Adds number to actual result.
     *
     * @param actualResult actual result
     * @param number number
     * @return result
     */
    private BigDecimal add(BigDecimal actualResult, BigDecimal number) {
        return actualResult.add(number);
    }

    /**
     * Subtracts number from actual result.
     *
     * @param actualResult actual result
     * @param number number
     * @return result
     */
    private BigDecimal subtract(BigDecimal actualResult, BigDecimal number) {
        return actualResult.subtract(number);
    }

    /**
     * Multiplies number with actual result.
     *
     * @param actualResult actual result
     * @param number number
     * @return result
     */
    private BigDecimal multiply(BigDecimal actualResult, BigDecimal number) {
        return actualResult.multiply(number);
    }

    /**
     * Divides number of actual result.
     *
     * @param actualResult actual result
     * @param number number
     * @return result
     * @throws DivisionByZeroException if number is zero
     */
    private BigDecimal divide(BigDecimal actualResult, BigDecimal number)
            throws DivisionByZeroException {
        try {
            return actualResult.divide(number, MathContext.DECIMAL128);
        } catch (ArithmeticException ex) {
            LOG.warn("Division by zero");
            throw new DivisionByZeroException(ex);
        }
    }
}
