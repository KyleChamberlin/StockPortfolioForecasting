package com.kylechamberlin.acct_forcasting.domain;

import static org.junit.Assert.*;
import org.junit.*;


public class GrowthRateTest {

	@Test
	public void interest() {
		GrowthRate rate = new GrowthRate(10);
		assertEquals(new Dollar(100), rate.growthFor(new Dollar(1000)));
	}
	
	@Test
	public void valueObject() {
		GrowthRate rate1a = new GrowthRate(10);
		GrowthRate rate1b = new GrowthRate(10);
		GrowthRate rate2 = new GrowthRate(20);
		
		assertEquals("10.0%", rate1a.toString());
		assertTrue("same rates are equal", rate1a.equals(rate1b));
		assertFalse("different rates are not equal", rate1a.equals(rate2));
		assertTrue("same rates have same hash code", rate1a.hashCode() == rate1b.hashCode());
	}
}
