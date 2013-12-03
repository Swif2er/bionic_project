package edu.bionic.client.Util;

import java.util.ArrayList;
import java.util.List;

import org.moxieapps.gwt.uploader.client.Uploader;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.ToggleButton;

import edu.bionic.client.Audio.SoundPlayer;
import edu.bionic.client.GUI.UserInterface;

public class ScheduledRun {

	private Timer timer;
	private int miliseconds = 250;
	static int togBtnIndex = 1;
	private static boolean stop = false;
	public static List<SoundPlayer> players = new ArrayList<SoundPlayer>();

	// private String pathToSoundFile;

	public ScheduledRun() {
		timer = new Timer() {
			public void run() {
				// if(getStart == true) {
				if (togBtnIndex == 17) {
					togBtnIndex = 1;
				}
				if (((ToggleButton) UserInterface.getTracks().get(panelId).getWidget(togBtnIndex))
						.isDown() == true) {
					// System.out.println("index: "
					// + togBtnIndex
					// + " state"
					// + ((ToggleButton) UserInterface.getRows().get(0)
					// .getWidget(togBtnIndex)).isDown());
					//
					// new SoundPlayer(pathToSoundFile).play();
					//
					// for (SoundPlayer player : players) {
					// if (player.getAudioName().equals(
					// ((Uploader)
					// UserInterface.getTracks().get(i).getWidget(0))
					// .getTitle())) {
					// player.play();
					// }
					// }

				}
				if (getStop() == true) {
					timer.cancel(); // Terminate the timer thread
				}
				togBtnIndex++;
			}
		};
		timer.scheduleRepeating(miliseconds); // in miliseconds
	}

	public void setDelay(int msec) {
		this.miliseconds = msec;
	}

	public int getDelay() {
		return miliseconds;
	}

	public static boolean getStop() {
		return stop;
	}

	public static void setStop(boolean stop) {
		ScheduledRun.stop = stop;
	}

	// public void setPathToSoundFile(String path) {
	// pathToSoundFile = path;
	// }

}