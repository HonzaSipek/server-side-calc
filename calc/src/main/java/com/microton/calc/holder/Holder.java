package com.microton.calc.holder;

import java.util.HashMap;
import java.util.Map;

/**
 * Calculation holder.
 *
 * @author Jan Šípek
 */
public class Holder {

    private Map<String, Calculation> calc;

    /**
     * Creates instance of calculation holder.
     */
    public Holder() {
        this.calc = new HashMap<>();
    }
    
    /**
     * Checks UUID.
     * @param uuid UUID
     * @return check result
     */
    public boolean checkUuid(String uuid){
        return this.calc.containsKey(uuid);
    }

    /**
     * Gets calculation by UUID.
     *
     * @param uuid UUID
     * @return calculation
     */
    public Calculation getCalculation(String uuid) {
        return this.calc.get(uuid);
    }

    /**
     * Updates calculation by UUID
     *
     * @param uuid UUID
     * @param calculation calculation
     */
    public void updateCalculatin(String uuid, Calculation calculation) {
        this.calc.put(uuid, calculation);
    }

    /**
     * Removes calculation by UUID
     *
     * @param uuid UUID
     */
    public void removeCalculation(String uuid) {
        this.calc.remove(uuid);
    }
}
