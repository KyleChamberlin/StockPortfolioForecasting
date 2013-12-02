package com.kylechamberlin.acct_forcasting.domain;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class AppreciationRateTest {

    @Test
	public void appreciation() {
		AppreciationRate testRate = new AppreciationRate(10);
		assertEquals(new Dollar(10), testRate.appreciationFor(new Dollar(100)));
	}

    @Test
    public void toStringHasPercentSign() {
        assertEquals("10.0%",new AppreciationRate(10).toString());
        assertEquals("99.9%", new AppreciationRate(99.90).toString());
        assertEquals("10.5%", new AppreciationRate(10.5).toString());
    }

    @Test
    public void appreciationRatesWithSameValueAreEqual() {
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            double randomDouble = random.nextDouble();
            assertEquals(new AppreciationRate(randomDouble), new AppreciationRate(randomDouble));
        }
    }

    @Test
    public void SameObjectIsEqual() {
        AppreciationRate rate = new AppreciationRate(1);

        assertEquals(rate, rate);
    }

    @Test
    public void appreciationRatesWithDifferentValuesAreNotEqual() {
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            int randomInt1 = random.nextInt(200);
            double randomDouble2 = 0.01 + random.nextInt(200);
            assertFalse(new AppreciationRate(randomInt1).equals(new AppreciationRate(randomDouble2)));
        }
    }

    @Test
    public void appreciationRatesWithSameValueHaveSameHashCode() {
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            double randomDouble = random.nextDouble();
            assertTrue(new AppreciationRate(randomDouble).hashCode() == new AppreciationRate(randomDouble).hashCode());
        }
    }
}
