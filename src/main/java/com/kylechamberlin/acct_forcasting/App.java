package com.kylechamberlin.acct_forcasting;

import java.awt.*;
import javax.swing.*;
import com.kylechamberlin.acct_forcasting.domain.*;
import com.kylechamberlin.acct_forcasting.ui.*;

public class App extends ApplicationFrame {
	private static final long serialVersionUID = 3216816L;

	public App() {
		this.setSize(1024, 600);
		this.setLocation(420, 340);
		
		Container c = this.getContentPane();
		c.add(theTable());
	}

	private JScrollPane theTable() {
		StockMarketTableModel model = new StockMarketTableModel(stockMarket());
		JTable table = new ForecastTable(model);
		return new JScrollPane(table);
	}

	private Market stockMarket() {
		Year start = new Year(2014);
		Year end = new Year(2100);
		Dollar balance = new Dollar(7500);
		Dollar principal = new Dollar(7500);
		GrowthRate interestRate = new GrowthRate(7.125);
		TaxRate taxRate = new TaxRate(25);
        Dollar amountToSell = new Dollar(500);
        return new Market(balance, principal, interestRate, taxRate, start, end, amountToSell);
	}
	
	public static void main(String[] args) {
		new App().setVisible(true);
	}
	
}
