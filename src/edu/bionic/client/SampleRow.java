package edu.bionic.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ToggleButton;

public class SampleRow extends Composite {
	
	private HorizontalPanel hPanel = new HorizontalPanel();
	private HorizontalPanel samplePanel = new HorizontalPanel();
	private HorizontalPanel gridPanel = new HorizontalPanel();

	public SampleRow(String btnName) {
		initWidget(hPanel);
		
		hPanel.setSpacing(30);
		gridPanel.setSpacing(5);

		hPanel.add(samplePanel);
		Button uploadSample = new Button(btnName);
		samplePanel.add(uploadSample);

		hPanel.add(gridPanel);
		for (int i = 0; i < 16; i++) {
			gridPanel.add(new ToggleButton(new Image("/favicon.ico")));
		}
	}
}
