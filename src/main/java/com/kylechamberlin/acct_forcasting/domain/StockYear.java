package com.kylechamberlin.acct_forcasting.domain;

public class StockYear {

	private Year year;
	private Dollar startingBalance;
	private Dollar costBasis;
	private GrowthRate growthRate;
	private TaxRate capitalGainsTaxRate;
	private Dollar totalSellOrders;
	
	public StockYear(Year year, Dollar startingBalance, Dollar costBasis, GrowthRate growthRate, TaxRate capitalGainsTaxRate) {
		this.year = year;
		this.startingBalance = startingBalance;
		this.costBasis = costBasis;
		this.growthRate = growthRate;
		this.capitalGainsTaxRate = capitalGainsTaxRate;
		this.totalSellOrders = new Dollar(0);
	}

	public Year year() {
		return year;
	}

	public Dollar beginningBalance() {
		return startingBalance;
	}

	public Dollar beginningCostBase() {
		return costBasis;
	}

	private Dollar startingCapitalGains() {
		return beginningBalance().subtract(beginningCostBase());
	}

	public GrowthRate growthRate() {
		return growthRate;
	}

	public TaxRate capitalGainsTaxRate() {
		return capitalGainsTaxRate;
	}

	public void sell(Dollar amount) {
		this.totalSellOrders = totalSellOrders.add(amount);
	}

	private Dollar capitalGainsWithdrawn() {
		return Dollar.minimum(startingCapitalGains(), totalSellOrders());
	}

	public Dollar capitalGainsTaxIncurred() {
		return capitalGainsTaxRate.compoundTaxFor(capitalGainsWithdrawn());
	}

	public Dollar totalSellOrders() {
		return totalSellOrders;
	}

	public Dollar totalSold() {
		return totalSellOrders().add(capitalGainsTaxIncurred());
	}

	public Dollar growth() {
		return growthRate.growthFor(startingBalance.subtract(totalSold()));
	}

	public Dollar endingBalance() {
		return startingBalance.subtract(totalSold()).add(growth());
	}

	public Dollar endingCostBase() {
		Dollar soldPurchases = totalSold().subtractNonNegative(startingCapitalGains());
		return beginningCostBase().subtract(soldPurchases);
	}

	public StockYear nextYear() {
		return new StockYear(year.nextYear(), this.endingBalance(), this.endingCostBase(), this.growthRate(), this.capitalGainsTaxRate());
	}

}
