package edu.bionic.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class MenuView extends Composite {
	
	private HorizontalPanel hPanel = new HorizontalPanel();
	private HorizontalPanel mainPanel = new HorizontalPanel();
	private HorizontalPanel controlPanel = new HorizontalPanel();
	
	public MenuView() {
		initWidget(this.hPanel);
		
		hPanel.setSpacing(20);
		hPanel.add(mainPanel);
		
		Button addSampleBtn = new Button("Add Sample...");
		addSampleBtn.addClickHandler(new addSmplClickHandler());
		mainPanel.add(addSampleBtn);
		
		hPanel.add(controlPanel);
		controlPanel.setSpacing(10);
		
		Button playBtn = new Button("Play");
		playBtn.addClickHandler(new playClickHandler());
		controlPanel.add(playBtn);
		
		Button stopBtn = new Button("Stop");
		stopBtn.addClickHandler(new stopClickHandler());
		controlPanel.add(stopBtn);
		
	}
	
	private class playClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class stopClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class addSmplClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
