package edu.bionic.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel contentPanel;
	private int numberOfSamples = 4;

	public MainView() {
		initWidget(vPanel);
		
		MenuView menu = new MenuView();
		vPanel.add(menu);

		contentPanel = new VerticalPanel();
		vPanel.add(contentPanel);

		addSampleRows(numberOfSamples);
	}
	
	private void addSampleRows(int n) {
		for (int i = 0; i < n; i++) {
			int j = i+1;
			contentPanel.add(new SampleRow("SoundClip " + j));
		}
	}
}
