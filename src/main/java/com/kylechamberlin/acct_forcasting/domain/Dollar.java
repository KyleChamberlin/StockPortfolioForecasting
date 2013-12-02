package com.kylechamberlin.acct_forcasting.domain;

public class Dollar {

	private double amount;

	public Dollar(int amount) {
		this.amount = amount;
	}
	
	public Dollar(double amount) {
		this.amount = amount;
	}
	
	public Dollar add(Dollar dollars) {
		return new Dollar(this.amount + dollars.amount);
	}

	public Dollar subtract(Dollar dollars) {
		return new Dollar(this.amount - dollars.amount);
	}

	public Dollar subtractNonNegative(Dollar dollars) {
		double result = this.amount - dollars.amount;
		return new Dollar(Math.max(0, result));
	}

	public Dollar rate(double percent) {
		return new Dollar(amount * percent / 100.0);
	}

	public static Dollar minimum(Dollar firstValue, Dollar secondValue) {
		return new Dollar(Math.min(firstValue.amount, secondValue.amount));
    }

	private int round() {
		return (int) Math.round(this.amount);
	}

	@Override
	public String toString() {
		return "$" + round();
	}

	@Override
	public int hashCode() {
		return round();
	}

	@Override
	public boolean equals(Object obj) {
		Dollar that = (Dollar) obj;
		return this.round() == that.round();
	}
	
}
