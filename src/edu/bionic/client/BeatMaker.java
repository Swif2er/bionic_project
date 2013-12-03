package edu.bionic.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import edu.bionic.client.Audio.SoundPlayer;
import edu.bionic.client.GUI.UserInterface;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BeatMaker implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		UserInterface ui = new UserInterface();
		ui.setStyleName("center");
		RootPanel.get().add(ui);
		RootPanel.get().setStyleName("body");
		
//		Button test = new Button("PLAY");
//		test.removeStyleName("button");
//		test.removeStyleName("gwt-Button");
//		test.addStyleName("test");
//		test.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				new SoundPlayer("SoundClips/808HH.wav").play();
//			}
//		});
//		RootPanel.get().add(test);
//		
//		Button test2 = new Button("STOP");
//		test2.addStyleName("stop");
//		RootPanel.get().add(test2);
	}
}