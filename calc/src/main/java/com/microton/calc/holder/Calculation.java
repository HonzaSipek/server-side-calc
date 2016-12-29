package com.microton.calc.holder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Calculation object for holding user calculations.
 *
 * @author Jan Šípek
 */
public class Calculation {

    private long lastUpdate;
    private List<String> history;
    private String calculation;
    private BigDecimal result;
    private Integer operator;
    private List<Integer> number;
    private BigDecimal displayedNumber;

    /**
     * Creates instance of calculation object
     *
     * @param lastUpdate last update
     * @param history calculation history
     * @param calculation actual calculation
     * @param result actual result
     * @param operator operator
     * @param number number
     * @param displayedNumber displayed number
     * @throws NullPointerException if last update is null
     */
    public Calculation(Long lastUpdate, List<String> history,
            String calculation, BigDecimal result, Integer operator,
            List<Integer> number, BigDecimal displayedNumber)
            throws NullPointerException {
        this.lastUpdate = Objects.requireNonNull(lastUpdate,
                "Last update cannot be null");
        this.history = history;
        this.calculation = calculation;
        this.result = result;
        this.operator = operator;
        this.number = number;
        this.displayedNumber = displayedNumber;
    }

    /**
     * Get last update.
     *
     * @return last update
     */
    public long getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Set last update.
     *
     * @param lastUpdate last update
     * @throws NullPointerException if last update is null
     */
    public void setLastUpdate(Long lastUpdate) throws NullPointerException {
        this.lastUpdate = Objects.requireNonNull(lastUpdate,
                "Last update cannot be null");
    }

    /**
     * Get calculation history.
     *
     * @return calculation history
     */
    public List<String> getHistory() {
        return history;
    }

    /**
     * Set calculation history.
     *
     * @param history calculation history
     */
    public void setHistory(List<String> history) {
        this.history = history;
    }

    /**
     * Get actual calculation.
     *
     * @return actual calculation
     */
    public String getCalculation() {
        return calculation;
    }

    /**
     * Set actual calculation.
     *
     * @param calculation actual calculation
     */
    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }

    /**
     * Get actual result.
     *
     * @return actual result
     */
    public BigDecimal getResult() {
        return result;
    }

    /**
     * Set actual result.
     *
     * @param result actual result
     */
    public void setResult(BigDecimal result) {
        this.result = result;
    }

    /**
     * Get operator.
     *
     * @return operator
     */
    public Integer getOperator() {
        return operator;
    }

    /**
     * Set operator.
     *
     * @param operator operator
     */
    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    /**
     * Get number.
     *
     * @return number
     */
    public List<Integer> getNumber() {
        return number;
    }

    /**
     * Set number.
     *
     * @param number number
     */
    public void setNumber(List<Integer> number) {
        this.number = number;
    }

    /**
     * Gets displayed number.
     *
     * @return displayed number
     */
    public BigDecimal getDisplayedNumber() {
        return displayedNumber;
    }

    /**
     * Sets displayed number.
     *
     * @param displayedNumber displayed number
     */
    public void setDisplayedNumber(BigDecimal displayedNumber) {
        this.displayedNumber = displayedNumber;
    }

}
