package com.microton.calc.holder;

import java.math.BigDecimal;
import java.util.Arrays;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Unit tests for Holder {@link Holder}
 *
 * @author Jan Šípek
 */
public class HolderTest {

    private static final String UUID = "9864";
    private static Calculation CALCULATION;

    @BeforeClass
    public void setUp() {
        CALCULATION = Mockito.mock(Calculation.class);
    }

    @Test
    public void createInstance_test() {
        Holder holder = new Holder();
        Assert.assertTrue(!holder.checkUuid("345689876543"));
    }

    @Test
    public void updateCalc_test() {
        Holder holder = new Holder();
        holder.updateCalculatin(UUID, CALCULATION);
        Assert.assertEquals(CALCULATION, holder.getCalculation(UUID));
    }

    @Test
    public void checkCalc_test() {
        Holder holder = new Holder();
        holder.updateCalculatin(UUID, CALCULATION);
        Assert.assertTrue(holder.checkUuid(UUID));
    }

    @Test
    public void getCalc_test() {
        Holder holder = new Holder();
        holder.updateCalculatin(UUID, CALCULATION);
        Assert.assertEquals(CALCULATION, holder.getCalculation(UUID));
    }

    @Test
    public void removeCalc_test() {
        Holder holder = new Holder();
        holder.updateCalculatin(UUID, CALCULATION);
        holder.removeCalculation(UUID);
        Assert.assertNull(holder.getCalculation(UUID));
    }
}
