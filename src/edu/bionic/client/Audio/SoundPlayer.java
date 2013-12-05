package edu.bionic.client.Audio;

import com.google.gwt.media.client.Audio;

public class SoundPlayer {

	private Audio audio;
	private String audioName;
	
	public SoundPlayer(String sFile) {
	//public SoundPlayer() {
		audioName = sFile;
		audio = Audio.createIfSupported();
		//audio.setSrc("SoundClips/808HH.wav");
		audio.setSrc(sFile);
	}

//	public String getAudioName(){
//		int nameStartAt = audioName.lastIndexOf("/") + 1;
//		return audioName.substring(nameStartAt);
//	}
	
	public void play() {
		audio.play();
	}
	 
}
