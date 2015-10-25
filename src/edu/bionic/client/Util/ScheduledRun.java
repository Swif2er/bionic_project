package edu.bionic.client.Util;

import java.util.ArrayList;
import java.util.List;

import org.moxieapps.gwt.uploader.client.Uploader;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ToggleButton;

import edu.bionic.client.Audio.SoundPlayer;
import edu.bionic.client.Audio.VoicesPlayer;
import edu.bionic.client.GUI.UserInterface;

public class ScheduledRun {

	//private Timer timer;
	private static int miliseconds = 250;
	//static int togBtnIndex = 1;
	//private static boolean stop = false;
	private Delay delay;

	// private String pathToSoundFile;
	//private int trackId;
	
//	public ScheduledRun(int id) {
//		trackId = id;
	public ScheduledRun() {
//		Timer timer = new Timer() {
//			public void run() {
//				// if(getStart == true) {
//				if (togBtnIndex == 17) {
//					togBtnIndex = 1;
//				}
//				for (int x = 0; x < UserInterface.getTracks().size(); x++) {
////				if (((ToggleButton) UserInterface.getTracks().get(trackId).getWidget(togBtnIndex))
////						.isDown() == true) {
////					new VoicesPlayer(UserInterface.getFileNames().gettrackIdx)).play();
////				}
//					if (((ToggleButton) UserInterface.getTracks().get(x).getWidget(togBtnIndex)).isDown() == true) {	
//						UserInterface.players.get(x).play();
//					}
//				}
//				if (getStop() == true) {
//					this.cancel(); // Terminate the timer thread
//				}
//				togBtnIndex++;
//			}
//		};
//		timer.scheduleRepeating(miliseconds); // in miliseconds
		delay = new Delay();
		delay.resetTogBtnIndex();
		delay.repeating(miliseconds);
		
	}

	public void setDelay(int msec) {
		this.miliseconds = msec;
	}

	public int getDelay() {
		return miliseconds;
	}

	public boolean getStop() {
		return delay.getStop();
	}

	public void setStop(boolean stop) {
		delay.setStop(stop);
	}

	// public void setPathToSoundFile(String path) {
	// pathToSoundFile = path;
	// }

//	public void setPanelId(int id) {
//		panelId = id;
//	}
//	
//	public int getPanelId() {
//		return panelId;
//	}
}