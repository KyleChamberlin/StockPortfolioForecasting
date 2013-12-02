package com.kylechamberlin.acct_forcasting.domain;

import static org.junit.Assert.*;
import org.junit.*;

public class PortfolioYearTest {

	private static final Year YEAR = new Year(2014);
	private static final AppreciationRate INTEREST_RATE = new AppreciationRate(10);
	private static final Dollar BEGINNING_BALANCE = new Dollar(10000);
	private static final Dollar BEGINNING_PRINCIPAL = new Dollar(3000);
	private static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);

	@Test
	public void initialValues() {
		PortfolioYear year = newYear();
		assertEquals(YEAR, year.year());
		assertEquals(BEGINNING_BALANCE, year.beginningBalance());
		assertEquals(BEGINNING_PRINCIPAL, year.beginningCostBase());
		assertEquals(INTEREST_RATE, year.interestRate());
		assertEquals(CAPITAL_GAINS_TAX_RATE, year.taxRate());
		assertEquals(new Dollar(0), year.totalSold());
	}
	
	@Test
	public void totalSold() {
		PortfolioYear year = newYear();
		assertEquals(new Dollar(0), year.totalSellOrders());
		year.sell(new Dollar(3000)); // TODO: rename to 'sell'
		assertEquals(new Dollar(3000), year.totalSellOrders());
		year.sell(new Dollar(750));
		year.sell(new Dollar(1350));
		assertEquals(new Dollar(5100), year.totalSellOrders());
	}
	
	@Test
	public void capitalGainsTax() {
		PortfolioYear year = newYear();
		year.sell(new Dollar(4000));
		assertEquals(new Dollar(1333), year.taxIncurred());
		assertEquals(new Dollar(5333), year.totalSold());
	}
	
	@Test
	public void AllWithdrawalsAreSubjectToTaxUntilAllGainsHaveSold() {
		PortfolioYear year = newYear();
		
		Dollar capitalGains = BEGINNING_BALANCE.subtract(BEGINNING_PRINCIPAL);

		year.sell(new Dollar(500));
		assertEquals(new Dollar(167), year.taxIncurred());
		year.sell(capitalGains);
		assertEquals(new Dollar(2333), year.taxIncurred());
		year.sell(new Dollar(1000));
		assertEquals(new Dollar(2333), year.taxIncurred());
	}
	
	@Test
	public void interestEarned() {
		PortfolioYear year = newYear();
		assertEquals(new Dollar(1000), year.appreciation());
		year.sell(new Dollar(2000));
		assertEquals(new Dollar(733), year.appreciation());
	}
	
	@Test
	public void endingPrincipal() {
		PortfolioYear year = newYear();
		year.sell(new Dollar(500));
		assertEquals(BEGINNING_PRINCIPAL, year.endingCostBase());
		year.sell(new Dollar(6500));
		
		Dollar totalWithdrawn = new Dollar(9333);
		Dollar capitalGains = new Dollar(7000);
		Dollar principalReducedBy = totalWithdrawn.subtract(capitalGains);
		Dollar expectedPrincipal = BEGINNING_PRINCIPAL.subtract(principalReducedBy);
		assertEquals(expectedPrincipal, year.endingCostBase());
		
		year.sell(new Dollar(1000));
		assertEquals(new Dollar(-333), year.endingCostBase());
	}

	@Test
	public void endingBalance() {
		PortfolioYear year = newYear();
		assertEquals(new Dollar(11000), year.endingBalance());
		year.sell(new Dollar(1000));
		assertEquals(new Dollar(9533), year.endingBalance());
	}

	@Test
	public void nextYearStartingValuesMatchesThisYearEndingValues() {
		PortfolioYear thisYear = newYear();
		PortfolioYear nextYear = thisYear.nextPortfolioYear();
		assertEquals(new Year(2015), nextYear.year());
		assertEquals(thisYear.endingBalance(), nextYear.beginningBalance());
		assertEquals(thisYear.endingCostBase(), nextYear.beginningCostBase());
		assertEquals(thisYear.interestRate(), nextYear.interestRate());
		assertEquals(thisYear.taxRate(), nextYear.taxRate());
	}

	private PortfolioYear newYear() {
		return new PortfolioYear(BEGINNING_BALANCE, BEGINNING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE, YEAR);
	}

}
