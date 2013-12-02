package com.kylechamberlin.acct_forcasting;

import com.kylechamberlin.acct_forcasting.domain.*;
import com.kylechamberlin.acct_forcasting.ui.ApplicationFrame;
import com.kylechamberlin.acct_forcasting.ui.ForecastTable;
import com.kylechamberlin.acct_forcasting.ui.PortfolioTableModel;

import javax.swing.*;
import java.awt.*;

/* NOT TESTED!
 * STUB CODE TO RUN THE CURRENT APP
 * TODO: Replace this entire class with a TDD class once the API settles down.
 */

public class App extends ApplicationFrame {
	private static final long serialVersionUID = 3216816L;

	public App() {
		this.setSize(1024, 600);
		this.setLocation(420, 340);
		
		Container c = this.getContentPane();
		c.add(theTable());
	}

	private JScrollPane theTable() {
		PortfolioTableModel model = new PortfolioTableModel(portfolio());
		JTable table = new ForecastTable(model);
		return new JScrollPane(table);
	}

	private Portfolio portfolio() {
		Year start = new Year(2014);  //next year.
		Year end = new Year(2084);    //I turn 100! yay.
		Dollar balance = new Dollar(15000);
		Dollar principal = new Dollar(10000);
		AppreciationRate interestRate = new AppreciationRate(9.1);
		TaxRate taxRate = new TaxRate(25);
        Dollar amountToSellEachYear = new Dollar(900);
        return new Portfolio(balance, principal, interestRate, taxRate, start, end, amountToSellEachYear);
	}
	
	public static void main(String[] args) {
		new App().setVisible(true);
	}
	
}
