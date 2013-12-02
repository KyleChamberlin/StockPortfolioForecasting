package com.kylechamberlin.acct_forcasting.domain;

public class PortfolioYear {

	private Year year;
	private Dollar startingBalance;
	private Dollar costBase;
	private AppreciationRate interestRate;
	private TaxRate taxRate;
	private Dollar totalSales;
	
	public PortfolioYear(Dollar startingBalance, Dollar costBase, AppreciationRate interestRate, TaxRate taxRate, Year year) {
		this.startingBalance = startingBalance;
        this.costBase = costBase;
        this.interestRate = interestRate;
        this.taxRate = taxRate;
        this.year = year;
        this.totalSales = new Dollar(0);
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

	private Dollar beginningGains() {
		return beginningBalance().subtract(beginningCostBase());
	}

	public AppreciationRate interestRate() {
		return interestRate;
	}

	public TaxRate taxRate() {
		return taxRate;
	}

	public void sell(Dollar amount) {
		this.totalSales = totalSales.add(amount);
	}

	private Dollar gainsSold() {
		return Dollar.minimum(beginningGains(), totalSales());
	}

	public Dollar taxesIncurred() {
		return taxRate.complexTaxFor(gainsSold());
	}

	public Dollar totalSales() {
		return totalSales;
	}

	public Dollar totalSold() {
		return totalSales().add(taxesIncurred());
	}

	public Dollar appreciation() {
		return interestRate.appreciationFor(startingBalance.subtract(totalSold()));
	}

	public Dollar endingBalance() {
		return startingBalance.subtract(totalSold()).add(appreciation());
	}

	public Dollar endingCostBase() {
		Dollar soldPurchases = totalSold().subtractNonNegative(beginningGains());
		return beginningCostBase().subtract(soldPurchases);
	}

	public PortfolioYear nextPortfolioYear() {
		return new PortfolioYear(this.endingBalance(), this.endingCostBase(), this.interestRate(), this.taxRate(), year.nextYear());
	}

}
