package com.kylechamberlin.acct_forcasting.domain;

public class Year {

	private final int year;

	public Year(int year) {
		this.year = year;
	}

	public Year nextYear() {
		return new Year(year + 1);
	}

	public int yearsUntil(Year endingYear) {
		return endingYear.year - this.year + 1;
	}

	public String toString() {
		return "" + year;
	}

	@Override
	public int hashCode() {
		return year; //TODO: perhaps a better hashCode exists?
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

		Year other = (Year) obj;
		if (year != other.year) {
            return false;
        }

		return true;
	}
	
}
