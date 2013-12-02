package com.kylechamberlin.acct_forcasting.ui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;

public class ForecastTable extends JTable {
	private static final long serialVersionUID = 681188L;

	public static final Color DEFAULT_BACKGROUND = Color.WHITE;
	public static final Color ALT_BACKGROUND = new Color(210, 230, 250);
	public static final Color SELECTED_BACKGROUND = new Color(50, 115, 235);

	public ForecastTable(TableModel model) {
		super(model);
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component cell = super.prepareRenderer(renderer, row, column);
		
		if (isCellSelected(row, column)) {
            cell.setBackground(SELECTED_BACKGROUND);
        } else if (altRow(row)) {
            cell.setBackground(ALT_BACKGROUND);
        } else {
            cell.setBackground(DEFAULT_BACKGROUND);
        }
		
		return cell;
	}

	private boolean altRow(int row) {
		return row % 2 == 1;
	}
}
