package com.kylechamberlin.acct_forcasting;

import com.kylechamberlin.acct_forcasting.domain.*;
import com.kylechamberlin.acct_forcasting.ui.ApplicationFrame;
import com.kylechamberlin.acct_forcasting.ui.ForecastTable;
import com.kylechamberlin.acct_forcasting.ui.PortfolioTableModel;

import javax.swing.*;
import java.awt.*;

public class App extends ApplicationFrame {
	private static final long serialVersionUID = 3216816L;

	public App() {
		this.setSize(1024, 600);
		this.setLocation(420, 340);
		
		Container c = this.getContentPane();
		c.add(theTable());
	}

	private JScrollPane theTable() {
		PortfolioTableModel model = new PortfolioTableModel(stockMarket());
		JTable table = new ForecastTable(model);
		return new JScrollPane(table);
	}

	private Portfolio stockMarket() {
		Year start = new Year(2014);  //next year.
		Year end = new Year(2084);    //I turn 100! yay.
		Dollar balance = new Dollar(15000);
		Dollar principal = new Dollar(10000);
		GrowthRate interestRate = new GrowthRate(9.1);
		TaxRate taxRate = new TaxRate(25);
        Dollar amountToSell = new Dollar(900);
        return new Portfolio(balance, principal, interestRate, taxRate, start, end, amountToSell);
	}
	
	public static void main(String[] args) {
		new App().setVisible(true);
	}
	
}
