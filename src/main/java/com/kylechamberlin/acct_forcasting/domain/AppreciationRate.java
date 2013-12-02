package com.kylechamberlin.acct_forcasting.domain;

public class AppreciationRate {

	private double rateAsPercentage;
	
	public AppreciationRate(double rateAsPercentage) {
		this.rateAsPercentage = rateAsPercentage;
	}

	public Dollar appreciationFor(Dollar amount) {
		return amount.rate(rateAsPercentage);
	}
	
	@Override
	public String toString() {
		return rateAsPercentage + "%";
	}

	@Override
	public int hashCode() {
		return (int) (rateAsPercentage); //TODO: research better hashCode that is still simple.
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		if (obj == null) {
            return false;
        }
		if (getClass() != obj.getClass()) {
            return false;
        }

		AppreciationRate other = (AppreciationRate) obj;
		if (Double.doubleToLongBits(rateAsPercentage) != Double.doubleToLongBits(other.rateAsPercentage)) {
            return false;
        }

		return true;
	}

}
