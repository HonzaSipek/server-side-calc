package com.microton.calc.dto;

import com.google.gson.annotations.SerializedName;
import org.immutables.value.Value;

/**
 * Math request data transfer object.
 *
 * @author Jan Šípek
 */
@Value.Immutable
public abstract class MathRequest {

    /**
     * Gets UUID.
     *
     * @return UUID
     */
    @Value.Parameter
    @SerializedName("uuid")
    public abstract String getUuid();

    /**
     * Gets key code.
     *
     * @return key code
     */
    @Value.Parameter
    @SerializedName("key_code")
    public abstract Integer getKeyCode();
}
