package edu.bionic.client.Util;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ToggleButton;

import edu.bionic.client.GUI.UserInterface;

public class Delay extends Timer {

	//private int miliseconds = 250;
	static int togBtnIndex;
	private boolean stop = false;

	public void run() {
		if (getStop() == true) {
			this.cancel(); // Terminate the timer thread
		}
		if (togBtnIndex == 17) {
			resetTogBtnIndex();
		}
		//UserInterface.getTracks().get(0).get
		for (int x = 0; x < UserInterface.getTracks().size(); x++) {
			ToggleButton toggle = (ToggleButton) UserInterface.getTracks().get(x).getWidget(togBtnIndex);
			//toggle.addStyleName("play");
			if (toggle.isDown() == true) {
				UserInterface.getPlayers().get(x).play();
			}
		}
		
		togBtnIndex++;
	}

	public void repeating(int msec) {
		this.scheduleRepeating(msec);
	}
	
//	public void setDelay(int msec) {
//		this.miliseconds = msec;
//	}

//	public int getDelay() {
//		return miliseconds;
//	}

	public boolean getStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	public void resetTogBtnIndex() {
		togBtnIndex = 1;
	}
}