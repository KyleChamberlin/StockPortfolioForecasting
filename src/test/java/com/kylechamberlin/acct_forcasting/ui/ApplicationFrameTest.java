package com.kylechamberlin.acct_forcasting.ui;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ApplicationFrameTest {

	@Test
	public void applicationWindowHasTitle() {
		ApplicationFrame frame = new ApplicationFrame();
		assertEquals("the Title", ApplicationFrame.TITLE, frame.getTitle());
	}
	
}
