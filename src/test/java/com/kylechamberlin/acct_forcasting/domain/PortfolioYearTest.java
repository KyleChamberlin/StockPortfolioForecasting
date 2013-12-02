package com.kylechamberlin.acct_forcasting.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PortfolioYearTest {

	private static final Year YEAR = new Year(2014);
	private static final AppreciationRate INTEREST_RATE = new AppreciationRate(10);
	private static final Dollar BEGINNING_BALANCE = new Dollar(10000);
	private static final Dollar BEGINNING_PRINCIPAL = new Dollar(3000);
	private static final TaxRate CAPITAL_GAINS_TAX_RATE = new TaxRate(25);

    private PortfolioYear testPortfolioYear;

    @Before
    public void setUp() {
        testPortfolioYear = makeNewYear();
    }

	@Test
	public void nounValues() {
		assertEquals(YEAR, testPortfolioYear.year());
		assertEquals(BEGINNING_BALANCE, testPortfolioYear.beginningBalance());
		assertEquals(BEGINNING_PRINCIPAL, testPortfolioYear.beginningCostBase());
		assertEquals(INTEREST_RATE, testPortfolioYear.interestRate());
		assertEquals(CAPITAL_GAINS_TAX_RATE, testPortfolioYear.taxRate());
		assertEquals(new Dollar(0), testPortfolioYear.totalSold());
	}
	
	@Test
	public void totalSold() {
		assertEquals(new Dollar(0), testPortfolioYear.totalSales());
		testPortfolioYear.sell(new Dollar(3000));
		testPortfolioYear.sell(new Dollar(750));
		assertEquals(new Dollar(3750), testPortfolioYear.totalSales());
	}
	
	@Test
	public void TaxUsesComplexTax() {
		testPortfolioYear.sell(new Dollar(4000));
		assertEquals(new Dollar(1333), testPortfolioYear.taxesIncurred());
		assertEquals(new Dollar(5333), testPortfolioYear.totalSold());
	}
	
	@Test
	public void SalesAreSubjectToTaxUntilAllAppreciationHasBeenSold() {
		Dollar appreciation = BEGINNING_BALANCE.subtract(BEGINNING_PRINCIPAL);

		testPortfolioYear.sell(new Dollar(500));
		assertEquals(new Dollar(167), testPortfolioYear.taxesIncurred());
		testPortfolioYear.sell(appreciation.subtract(new Dollar(500)));
		assertEquals(new Dollar(2333), testPortfolioYear.taxesIncurred());
		testPortfolioYear.sell(new Dollar(1000));
		assertEquals(new Dollar(2333), testPortfolioYear.taxesIncurred());
	}
	
	@Test
	public void totalAppreciation() {
		assertEquals(new Dollar(1000), testPortfolioYear.appreciation());
		testPortfolioYear.sell(new Dollar(2000));
		assertEquals(new Dollar(733), testPortfolioYear.appreciation());
	}
	
	@Test
	public void endingCostBase() {
		testPortfolioYear.sell(new Dollar(500));
		assertEquals(BEGINNING_PRINCIPAL, testPortfolioYear.endingCostBase());
		testPortfolioYear.sell(new Dollar(6500));
		
		Dollar totalWithdrawn = new Dollar(9333);
		Dollar capitalGains = new Dollar(7000);
		Dollar principalReducedBy = totalWithdrawn.subtract(capitalGains);
		Dollar expectedPrincipal = BEGINNING_PRINCIPAL.subtract(principalReducedBy);
		assertEquals(expectedPrincipal, testPortfolioYear.endingCostBase());
		
		testPortfolioYear.sell(new Dollar(1000));
		assertEquals(new Dollar(-333), testPortfolioYear.endingCostBase());
	}

	@Test
	public void endingBalance() {
		assertEquals(new Dollar(11000), testPortfolioYear.endingBalance());
		testPortfolioYear.sell(new Dollar(1000));
		assertEquals(new Dollar(9533), testPortfolioYear.endingBalance());
	}

	@Test
	public void nextYearNounsMatchTestPortfoliosEndingValues() {
		PortfolioYear nextPortfolioYear = testPortfolioYear.nextPortfolioYear();
		assertEquals(new Year(2015), nextPortfolioYear.year());
		assertEquals(testPortfolioYear.endingBalance(), nextPortfolioYear.beginningBalance());
		assertEquals(testPortfolioYear.endingCostBase(), nextPortfolioYear.beginningCostBase());
		assertEquals(testPortfolioYear.interestRate(), nextPortfolioYear.interestRate());
		assertEquals(testPortfolioYear.taxRate(), nextPortfolioYear.taxRate());
	}

    @After
    public void tearDown() {
        testPortfolioYear = null;
    }

	private PortfolioYear makeNewYear() {
		return new PortfolioYear(BEGINNING_BALANCE, BEGINNING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAINS_TAX_RATE, YEAR);
	}

}
