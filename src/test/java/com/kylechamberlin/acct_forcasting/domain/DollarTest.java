package com.kylechamberlin.acct_forcasting.domain;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DollarTest {

	@Test
	public void add() {
		assertEquals(new Dollar(40), new Dollar(10).add(new Dollar(30)));
	}
	
	@Test
	public void subtractWithPositiveResult() {
        assertEquals(new Dollar(20), new Dollar(50).subtract(new Dollar(30)));
	}

	@Test
	public void subtractWithNegativeResult() {
		assertEquals(new Dollar(-60), new Dollar(40).subtract(new Dollar(100)));
	}

	@Test
	public void subtractNonNegativeReturnsProperPositiveResult() {
		assertEquals(new Dollar(20), new Dollar(50).subtractNonNegative(new Dollar(30)));
	}

    @Test
	public void subtractNonNegativeReturnsProperZeroResult() {
		assertEquals(new Dollar(0), new Dollar(40).subtractNonNegative(new Dollar(100)));
	}

    @Test
    public void subtractNonNegativeNeverReturnsNegative() {
        Dollar randomDollarAmount1 = generateRandomDollarAmount();
        Dollar randomDollarAmount2 = generateRandomDollarAmount();

        for (int i = 0; i < 100; i++) {
            assertTrue(randomDollarAmount1.subtractNonNegative(randomDollarAmount2).hashCode() >= 0);
        }
    }

	@Test
	public void percentageOfCalculationForRoundNumbers() {
		assertEquals(new Dollar(20), new Dollar(100).percentageOf(20));
	}

	@Test
	public void PercentageOfCalculationForDecimalNumbers() {
		assertEquals(new Dollar(20), new Dollar(100.20).percentageOf(20.01));
	}

	@Test
	public void minimum() {
		Dollar value1 = new Dollar(20);
		Dollar value2 = new Dollar(30);
		assertEquals(new Dollar(20), Dollar.minimum(value1, value2));
		assertEquals(new Dollar(20), Dollar.minimum(value2, value1));
	}
	@Test
    public void sameObjectsAreEqual() {
        Dollar dollar = generateRandomDollarAmount();

        for (int i = 0; i < 100; i++) {
            assertTrue(dollar.equals(dollar));
            dollar = generateRandomDollarAmount();
        }
    }
	@Test
	public void equalsUsesRound() {
        Random random = new Random();

		int value = random.nextInt();
        double valueToRound = (- 0.1) + (double) value;
        assertTrue("Round up", new Dollar(value).equals(new Dollar(valueToRound)));

        value = random.nextInt();
        valueToRound = (0.1) + (double) value;
        assertTrue("Round down", new Dollar(value).equals(new Dollar(valueToRound)));

        value = random.nextInt();
        valueToRound = (-0.5) + (double) value;
        assertTrue("Midway always rounds up", new Dollar(value).equals(new Dollar(valueToRound)));
	}
	
	@Test
	public void hashCodeUsesRoundedValue() {
        Random random = new Random();

        int value = random.nextInt();
        double valueToRound = (0.1) + (double) value;
        assertTrue("Round down", new Dollar(value).hashCode() == new Dollar(valueToRound).hashCode());

        value = random.nextInt();
        valueToRound = (- 0.1) + (double) value;
        assertTrue("Round up", new Dollar(value).hashCode() == new Dollar(valueToRound).hashCode());

        value = random.nextInt();
        valueToRound = (- 0.5) + (double) value;
        assertTrue("Midway always rounds up", new Dollar(value).hashCode() == new Dollar(valueToRound).hashCode());
	}
	
	@Test
	public void toStringReturnsRoundedValue() {
		assertEquals("$100", new Dollar(100.10).toString());
		assertEquals("$100", new Dollar(99.90).toString());
		assertEquals("$11", new Dollar(10.5).toString());
	}

    private Dollar generateRandomDollarAmount() {
        Random random = new Random();
        return new Dollar(random.nextDouble());
    }

}
