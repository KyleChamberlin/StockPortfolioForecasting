package com.kylechamberlin.acct_forcasting.domain;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;


public class YearTest {

	@Test
	public void nextYearIsThisYearPlusOne() {
		Year thisYear = new Year(2014);
		assertEquals(new Year(2015), thisYear.nextYear());
	}
	
	@Test
	public void yearsUntilIncludesFirstYear() {
		Year thisYear = new Year(2014);
		assertEquals(71, thisYear.yearsUntil(new Year(2084)));
	}

    @Test
    public void toStringIsYear() {
        assertEquals("2014",new Year(2014).toString());
        assertEquals("2015",new Year(2015).toString());
    }

    @Test
    public void yearsWithSameValueAreEqual() {
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            int randomInt = random.nextInt(1000) + 2000; //year from 2000 to 3000
            assertEquals(new Year(randomInt), new Year(randomInt));
        }
    }

    @Test
    public void sameObjectIsEqual() {
        Year year = new Year(2014);

        assertEquals(year, year);
    }

    @Test
    public void yearsWithDifferentValuesAreNotEqual() {
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            int randomInt1 = 2000 + random.nextInt(1000);
            int randomInt2 = randomInt1 + 1 + random.nextInt(1000);
            assertFalse(new Year(randomInt1).equals(new Year(randomInt2)));
        }
    }

    @Test
    public void yearsWithSameValueHaveSameHashCode() {
        Random random = new Random();

        for (int i = 0; i < 100; i++){
            int randomInt = 2000 + random.nextInt(1000);
            assertTrue(new TaxRate(randomInt).hashCode() == new TaxRate(randomInt).hashCode());
        }
    }
}
