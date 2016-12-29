package com.microton.calc.dto;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

import org.immutables.value.Value;

import java.util.List;
import javax.annotation.Nullable;

/**
 * Math response data transfer object.
 *
 * @author Jan Šípek
 */
@Value.Immutable
public abstract class MathResponse {
        
    /**
     * Gets calculation history.
     *
     * @return calculation history
     */
    @Value.Parameter
    @SerializedName("history")
    @Nullable
    public abstract List<String> getHistory();

    /**
     * Gets actual result.
     *
     * @return calculation result
     */
    @Value.Parameter
    @SerializedName("number")
    public abstract BigDecimal getNumber();
}
