package com.kylechamberlin.acct_forcasting.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PortfolioTest {

	private static final Year START_YEAR = new Year(2014);
	private static final Year ENDING_YEAR = new Year(2084);
	private static final Dollar BEGINNING_BALANCE = new Dollar(10000);
	private static final Dollar BEGINNING_COST_BASE = new Dollar(7000);
	private static final AppreciationRate APPRECIATION_RATE = new AppreciationRate(10);
	private static final TaxRate TAX_RATE = new TaxRate(25);

	@Test
	public void PortfolioContainsMultipleYears() {
		Portfolio account = new Portfolio(BEGINNING_BALANCE, BEGINNING_COST_BASE, APPRECIATION_RATE, TAX_RATE, START_YEAR, ENDING_YEAR, new Dollar(0));
		assertEquals("# of years", 71, account.lengthInYears());
		assertEquals(BEGINNING_BALANCE, account.getStartingYearPlus(0).beginningBalance());
		assertEquals(new Dollar(11000), account.getStartingYearPlus(1).beginningBalance());
		assertEquals(new Dollar(12100), account.getStartingYearPlus(2).beginningBalance());
		assertEquals(new Year(2084), account.getStartingYearPlus(70).year());
	}
	
	@Test
	public void portfolioSellsAStandardAmountEachYear() {
		Portfolio account = new Portfolio(BEGINNING_BALANCE, BEGINNING_COST_BASE, APPRECIATION_RATE, TAX_RATE, START_YEAR, ENDING_YEAR, new Dollar(10));
		assertEquals(new Dollar(10), account.getStartingYearPlus(0).totalSales());
		assertEquals(new Dollar(10), account.getStartingYearPlus(1).totalSales());
		assertEquals(new Dollar(10), account.getStartingYearPlus(40).totalSales());
	}
		
	@Test
	public void InterestCalculationsDoNotRound() {
		Portfolio account = new Portfolio(BEGINNING_BALANCE, BEGINNING_COST_BASE, APPRECIATION_RATE, TAX_RATE, START_YEAR, ENDING_YEAR, new Dollar(0));
		assertEquals(new Dollar(497852), account.getStartingYearPlus(40).endingBalance());
	}
	
	@Test
	public void TaxCalculationWorksInSimplifiedManner() {
		Portfolio account = new Portfolio(BEGINNING_BALANCE, BEGINNING_COST_BASE, APPRECIATION_RATE, TAX_RATE, START_YEAR, ENDING_YEAR, new Dollar(695));
		assertEquals(new Dollar(2067), account.getStartingYearPlus(40).endingBalance());
	}
	
}
