package com.kylechamberlin.acct_forcasting.domain;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;


public class TaxRateTest {

    @Test
    public void toStringHasPercentSign() {
        assertEquals("10.0%",new TaxRate(10).toString());
        assertEquals("99.9%", new TaxRate(99.90).toString());
        assertEquals("10.5%", new TaxRate(10.5).toString());
    }

    @Test
    public void taxRatesWithSameValueAreEqual() {
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            double randomDouble = random.nextDouble();
            assertEquals(new TaxRate(randomDouble), new TaxRate(randomDouble));
        }
    }

    @Test
    public void SameObjectIsEqual() {
        TaxRate rate = new TaxRate(1);

        assertEquals(rate, rate);
    }

    @Test
    public void appreciationRatesWithDifferentValuesAreNotEqual() {
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            int randomInt1 = random.nextInt(200);
            double randomDouble2 = 0.01 + random.nextInt(200);
            assertFalse(new TaxRate(randomInt1).equals(new TaxRate(randomDouble2)));
        }
    }

    @Test
    public void appreciationRatesWithSameValueHaveSameHashCode() {
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            double randomDouble = random.nextDouble();
            assertTrue(new TaxRate(randomDouble).hashCode() == new TaxRate(randomDouble).hashCode());
        }
    }

	@Test
	public void dumbTaxIgnoresCompoundingTaxes() {
		TaxRate taxRate = new TaxRate(10);
		assertEquals(new Dollar(10), taxRate.dumbTax(new Dollar(100)));
	}
	
	@Test
	public void complexTaxIsTotalTaxIncurredWhenTaxingWithdrawlToCoverTax() {
		TaxRate taxRate = new TaxRate(25);
		assertEquals(new Dollar(333), taxRate.complexTaxFor(new Dollar(1000)));
	}
}
