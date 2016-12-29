package com.microton.calc.holder;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for Holder {@link Holder}
 *
 * @author Jan Šípek
 */
public class HolderTest {

    public static final String UUID = "9864";

    @Test
    public void createInstance_test() {
        Holder holder = new Holder();
        Assert.assertTrue(!holder.checkUuid("345689876543"));
    }

    @Test
    public void updateCalc_test() {
        Holder holder = new Holder();
        Calculation calc = Mockito.mock(Calculation.class);
        holder.updateCalculatin(UUID, calc);
        Assert.assertEquals(calc, holder.getCalculation(UUID));
    }

    @Test
    public void checkCalc_test() {
        Holder holder = new Holder();
        Calculation calc = Mockito.mock(Calculation.class);
        holder.updateCalculatin(UUID, calc);
        Assert.assertTrue(holder.checkUuid(UUID));
    }

    @Test
    public void getCalc_test() {
        Holder holder = new Holder();
        Calculation calc = Mockito.mock(Calculation.class);
        holder.updateCalculatin(UUID, calc);
        Assert.assertEquals(calc, holder.getCalculation(UUID));
    }

    @Test
    public void removeCalc_test() {
        Holder holder = new Holder();
        Calculation calc = Mockito.mock(Calculation.class);
        holder.updateCalculatin(UUID, calc);
        holder.removeCalculation(UUID);
        Assert.assertNull(holder.getCalculation(UUID));
    }
}
