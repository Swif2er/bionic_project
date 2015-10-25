package edu.bionic.client.Audio;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;

public class VoicesPlayer {
	
	private Sound sound;
	
	public VoicesPlayer(String aName) {
		SoundController soundController = new SoundController();
		sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_WAV_UNKNOWN,
				"UserSoundClips/" + aName); 
	}
	
	public void play() {
	    sound.play();
	}
}
