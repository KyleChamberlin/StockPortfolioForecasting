package com.kylechamberlin.acct_forcasting.ui;

import com.kylechamberlin.acct_forcasting.domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PortfolioTableModelTest {

	private static final Year START_YEAR = new Year(2014);
	private static final Year END_YEAR = new Year(2100);
	private static final Dollar BEGINNING_BALANCE = new Dollar(10000);
	private static final Dollar BEGINNING_PRINCIPAL = new Dollar(7000);
	private PortfolioTableModel model;
	private Portfolio portfolio;

	@Before
	public void setup() {
        portfolio = new Portfolio(BEGINNING_BALANCE, BEGINNING_PRINCIPAL, new GrowthRate(10), new TaxRate(25), START_YEAR, END_YEAR, new Dollar(0));
        model = new PortfolioTableModel(portfolio);
	}
	
	@Test
	public void columns() {
		assertEquals(6, model.getColumnCount());
		assertEquals("Year", model.getColumnName(0));
		assertEquals("Beginning Balance", model.getColumnName(1));
		assertEquals("Cost Base", model.getColumnName(2));
	}

	@Test
	public void oneRow() {
		assertEquals(START_YEAR, model.getValueAt(0, 0));
		assertEquals(BEGINNING_BALANCE, model.getValueAt(0, 1));
		assertEquals(BEGINNING_PRINCIPAL, model.getValueAt(0, 2));
		assertEquals(new Dollar(0), model.getValueAt(0, 3));
		assertEquals(new Dollar(1000), model.getValueAt(0, 4));
		assertEquals(new Dollar(11000), model.getValueAt(0, 5));
	}
	
	@Test
	public void multipleRows() {
		assertEquals(87, model.getRowCount());
		assertEquals(START_YEAR, model.getValueAt(0, 0));
		assertEquals(BEGINNING_BALANCE, model.getValueAt(0, 1));
		assertEquals(new Dollar(11000), model.getValueAt(1, 1));
		assertEquals(END_YEAR, model.getValueAt(86, 0));
	}

    @After
    public void tearDown() {
        portfolio = null;
        model = null;
    }
	
}
