package com.kylechamberlin.acct_forcasting.ui;

import javax.swing.table.*;
import com.kylechamberlin.acct_forcasting.domain.*;

public class StockMarketTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_TITLES = {"Year", "Starting Balance", "Cost Basis", "Sales", "Growth", "Ending Balance"};

	private Market market;

	public StockMarketTableModel(Market market) {
		this.market = market;
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
		return market.lengthInYears();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		StockYear currentYear = market.getYearOffset(rowIndex);
		switch (columnIndex) {
			case 0: return currentYear.year();
			case 1: return currentYear.beginningBalance();
			case 2: return currentYear.beginningCostBase();
			case 3: return currentYear.totalSold();
			case 4: return currentYear.growth();
			case 5: return currentYear.endingBalance();
			default: return "";
		}
	}
}
