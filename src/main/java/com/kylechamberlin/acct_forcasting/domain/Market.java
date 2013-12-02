package com.kylechamberlin.acct_forcasting.domain;

public class Market {

	private final Year startingYear;
	private final Year endingYear;
	private StockYear[] years;
	private final Dollar sellEveryYear;

	public Market(Dollar startingBalance, Dollar startingPrincipal, GrowthRate interestRate, TaxRate capitalGainsTaxRate, Year startingYear, Year endingYear, Dollar sellEveryYear) {
		this.startingYear = startingYear;
		this.endingYear = endingYear;
		this.sellEveryYear = sellEveryYear;
		populateYears(startingBalance, startingPrincipal, interestRate, capitalGainsTaxRate);
	}

	private void populateYears(Dollar startingBalance, Dollar startingPrincipal, GrowthRate interestRate, TaxRate capitalGainsTaxRate) {
		this.years = new StockYear[lengthInYears()];
		years[0] = new StockYear(startingYear, startingBalance, startingPrincipal, interestRate, capitalGainsTaxRate);
		years[0].sell(sellEveryYear);
		for (int i = 1; i < lengthInYears(); i++) {
			years[i] = years[i - 1].nextYear();
			years[i].sell(sellEveryYear);
		}
	}
	
	public int lengthInYears() {
		return startingYear.yearsUntil(endingYear);
	}
	
	public StockYear getYearOffset(int offset) {
		return years[offset];
	}

}
