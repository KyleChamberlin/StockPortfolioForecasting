package com.kylechamberlin.acct_forcasting.domain;

import static org.junit.Assert.*;
import org.junit.*;

public class MarketTest {

	private static final Year STARTING_YEAR = new Year(2010);
	private static final Year ENDING_YEAR = new Year(2050);
	private static final Dollar STARTING_BALANCE = new Dollar(10000);
	private static final Dollar STARTING_PRINCIPAL = new Dollar(7000);
	private static final GrowthRate INTEREST_RATE = new GrowthRate(10);
	private static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);

	@Test
	public void stockMarketContainsMultipleYears() {
		Market account = new Market(STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE, STARTING_YEAR, ENDING_YEAR, new Dollar(0));
		assertEquals("# of years", 41, account.lengthInYears());
		assertEquals(STARTING_BALANCE, account.getYearOffset(0).beginningBalance());
		assertEquals(new Dollar(11000), account.getYearOffset(1).beginningBalance());
		assertEquals(new Dollar(12100), account.getYearOffset(2).beginningBalance());
		assertEquals(new Year(2050), account.getYearOffset(40).year());
	}
	
	@Test
	public void stockMarketWithdrawsAStandardAmountEveryYear() {
		Market account = new Market(STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE, STARTING_YEAR, ENDING_YEAR, new Dollar(10));
		assertEquals(new Dollar(10), account.getYearOffset(0).totalSellOrders());
		assertEquals(new Dollar(10), account.getYearOffset(1).totalSellOrders());
		assertEquals(new Dollar(10), account.getYearOffset(40).totalSellOrders());
	}
		
	@Test
	public void noCumulativeRoundingErrorInInterestCalculations() {
		Market account = new Market(STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE, STARTING_YEAR, ENDING_YEAR, new Dollar(0));
		assertEquals(new Dollar(497852), account.getYearOffset(40).endingBalance());
	}
	
	@Test
	public void capitalGainsTaxCalculationWorksTheSameWayAsSpreadsheet() {
		Market account = new Market(STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE, STARTING_YEAR, ENDING_YEAR, new Dollar(695));
		assertEquals(new Dollar(2067), account.getYearOffset(40).endingBalance());
	}
	
}
