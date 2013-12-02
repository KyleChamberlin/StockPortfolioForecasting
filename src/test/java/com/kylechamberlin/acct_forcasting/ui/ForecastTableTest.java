package com.kylechamberlin.acct_forcasting.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

import static org.junit.Assert.assertEquals;


public class ForecastTableTest {

    DefaultTableModel model;

    @Before
    public void setup() {
        model = new DefaultTableModel(0, 1);
    }
	
	@Test
	public void tableRowZeroShouldUseDefault() {
		model.addRow(new String[]{""});
		JTable forecastTable = new ForecastTable(model);

		assertEquals("row 0 should have standard background", ForecastTable.DEFAULT_BACKGROUND, getBackgroundColorForCell(forecastTable, 0, 0));
	}

	@Test
	public void tableShouldAlternateRowColorWithoutColumnHeaders() {

        model = new DefaultTableModel(0, 1);
        addFourBlankRows(model);
		JTable forecastTable = new ForecastTable(model);

		assertEquals(ForecastTable.DEFAULT_BACKGROUND, getBackgroundColorForCell(forecastTable, 0, 0));
		assertEquals(ForecastTable.ALT_BACKGROUND, getBackgroundColorForCell(forecastTable, 1, 0));
		assertEquals(ForecastTable.DEFAULT_BACKGROUND, getBackgroundColorForCell(forecastTable, 2, 0));
		assertEquals(ForecastTable.ALT_BACKGROUND, getBackgroundColorForCell(forecastTable, 3, 0));
	}

    @Test
	public void tableShouldAlternateRowColorWithColumnHeaders() {
		model.setColumnIdentifiers(new Object[]{"Test Header"});
        addFourBlankRows(model);
		JTable forecastTable = new ForecastTable(model);

		assertEquals(ForecastTable.DEFAULT_BACKGROUND, getBackgroundColorForCell(forecastTable, 0, 0));
		assertEquals(ForecastTable.ALT_BACKGROUND, getBackgroundColorForCell(forecastTable, 1, 0));
		assertEquals(ForecastTable.DEFAULT_BACKGROUND, getBackgroundColorForCell(forecastTable, 2, 0));
		assertEquals(ForecastTable.ALT_BACKGROUND, getBackgroundColorForCell(forecastTable, 3, 0));
	}

	@Test
	public void tableShouldDisplaySelectedColorWhenSelected() {
		model.addRow(new String[]{""});
		JTable forecastTable = new ForecastTable(model);

		forecastTable.setRowSelectionInterval(0, 0);
		assertEquals(ForecastTable.SELECTED_BACKGROUND, getBackgroundColorForCell(forecastTable, 0, 0));
	}

    @After
    public void tearDown() {
        model = null;
    }

	private Color getBackgroundColorForCell(JTable table, int row, int column) {
		TableCellRenderer renderer = table.getCellRenderer(row, column);
		Component c = table.prepareRenderer(renderer, row, column);
		Color backgroundColor = c.getBackground();
		return backgroundColor;
	}

    private void addFourBlankRows(DefaultTableModel model) {
        model.addRow(new String[]{""});
        model.addRow(new String[]{""});
        model.addRow(new String[]{""});
        model.addRow(new String[]{""});
    }

}
