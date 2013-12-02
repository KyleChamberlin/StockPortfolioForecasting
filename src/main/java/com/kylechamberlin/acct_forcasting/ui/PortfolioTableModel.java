package com.kylechamberlin.acct_forcasting.ui;

import com.kylechamberlin.acct_forcasting.domain.Portfolio;
import com.kylechamberlin.acct_forcasting.domain.PortfolioYear;

import javax.swing.table.AbstractTableModel;

public class PortfolioTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_TITLES = {"Year", "Beginning Balance", "Cost Base", "Total Sold", "Growth", "Ending Balance"};

	private Portfolio portfolio;

	public PortfolioTableModel(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	@Override
	public int getColumnCount() {
		return COLUMN_TITLES.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMN_TITLES[column];
	}

	@Override
	public int getRowCount() {
		return portfolio.lengthInYears();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PortfolioYear portfolioYearAtRow = portfolio.getStartingYearPlus(rowIndex);
		switch (columnIndex) {
			case 0: return portfolioYearAtRow.year();
			case 1: return portfolioYearAtRow.beginningBalance();
			case 2: return portfolioYearAtRow.beginningCostBase();
			case 3: return portfolioYearAtRow.totalSold();
			case 4: return portfolioYearAtRow.appreciation();
			case 5: return portfolioYearAtRow.endingBalance();
			default: return "";
		}
	}
}
