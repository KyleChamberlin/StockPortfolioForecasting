package com.kylechamberlin.acct_forcasting.domain;

public class Portfolio {

	private final Year startYear;
	private final Year endYear;
	private PortfolioYear[] portfolioYears;
	private final Dollar amountToSellEachYear;

	public Portfolio(Dollar beginningBalance, Dollar beginningCostBase, AppreciationRate interestRate, TaxRate capitalGainsTaxRate, Year startYear, Year endYear, Dollar amountToSellEachYear) {
		this.startYear = startYear;
		this.endYear = endYear;
		this.amountToSellEachYear = amountToSellEachYear;
		fillYearsWith(beginningBalance, beginningCostBase, interestRate, capitalGainsTaxRate);
	}

	private void fillYearsWith(Dollar startingBalance, Dollar startingCostBase, AppreciationRate interestRate, TaxRate capitalGainsTaxRate) {
		this.portfolioYears = new PortfolioYear[lengthInYears()];
		portfolioYears[0] = new PortfolioYear(startingBalance, startingCostBase, interestRate, capitalGainsTaxRate, startYear);
		portfolioYears[0].sell(amountToSellEachYear);
		for (int i = 1; i < lengthInYears(); i++) {
			portfolioYears[i] = portfolioYears[i - 1].nextPortfolioYear();
			portfolioYears[i].sell(amountToSellEachYear);
		}
	}
	
	public int lengthInYears() {
		return startYear.yearsUntil(endYear);
	}
	
	public PortfolioYear getStartingYearPlus(int offset) {
		return portfolioYears[offset];
	}

}
