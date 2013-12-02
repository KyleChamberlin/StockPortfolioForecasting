package com.kylechamberlin.acct_forcasting.domain;

public class TaxRate {

	private double rateAsPercentage;
	
	public TaxRate(double rateAsPercentage) {
		this.rateAsPercentage = rateAsPercentage;
	}
	
	public Dollar simpleTaxFor(Dollar amount) {
		return amount.rate(rateAsPercentage);
	}

	public Dollar compoundTaxFor(Dollar amount) {
		double compoundRate = (100.0 / (100.0 - rateAsPercentage)) - 1;
		return amount.rate(compoundRate * 100);
	}
	
	@Override
	public String toString() {
		return (rateAsPercentage) + "%";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(rateAsPercentage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TaxRate other = (TaxRate) obj;
		if (Double.doubleToLongBits(rateAsPercentage) != Double.doubleToLongBits(other.rateAsPercentage)) return false;
		return true;
	}

}
