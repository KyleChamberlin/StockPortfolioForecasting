package com.kylechamberlin.acct_forcasting.domain;

public class PortfolioYear {

	private Year year;
	private Dollar startingBalance;
	private Dollar costBase;
	private GrowthRate interestRate;
	private TaxRate taxRate;
	private Dollar totalSellOrders;
	
	public PortfolioYear(Dollar startingBalance, Dollar costBase, GrowthRate interestRate, TaxRate taxRate, Year year) {
		this.startingBalance = startingBalance;
        this.costBase = costBase;
        this.interestRate = interestRate;
        this.taxRate = taxRate;
        this.year = year;
        this.totalSellOrders = new Dollar(0);
    }

	public Year year() {
		return year;
	}

	public Dollar beginningBalance() {
		return startingBalance;
	}

	public Dollar beginningCostBase() {
		return costBase;
	}

	private Dollar startingCapitalGains() {
		return beginningBalance().subtract(beginningCostBase());
	}

	public GrowthRate interestRate() {
		return interestRate;
	}

	public TaxRate taxRate() {
		return taxRate;
	}

	public void sell(Dollar amount) {
		this.totalSellOrders = totalSellOrders.add(amount);
	}

	private Dollar capitalGainsWithdrawn() {
		return Dollar.minimum(startingCapitalGains(), totalSellOrders());
	}

	public Dollar taxIncurred() {
		return taxRate.compoundTaxFor(capitalGainsWithdrawn());
	}

	public Dollar totalSellOrders() {
		return totalSellOrders;
	}

	public Dollar totalSold() {
		return totalSellOrders().add(taxIncurred());
	}

	public Dollar appreciation() {
		return interestRate.growthFor(startingBalance.subtract(totalSold()));
	}

	public Dollar endingBalance() {
		return startingBalance.subtract(totalSold()).add(appreciation());
	}

	public Dollar endingCostBase() {
		Dollar soldPurchases = totalSold().subtractNonNegative(startingCapitalGains());
		return beginningCostBase().subtract(soldPurchases);
	}

	public PortfolioYear nextPortfolioYear() {
		return new PortfolioYear(this.endingBalance(), this.endingCostBase(), this.interestRate(), this.taxRate(), year.nextYear());
	}

}
