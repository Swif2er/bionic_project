package edu.bionic.client.Audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player {
	private File file;
	private AudioInputStream ais;
	private Clip clip;
	private static Mixer mixer;

	public void play(String str) {
		try {
			file = new File(str);
			ais = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}

//	public static void main(String[] args) throws Exception {
//		File file1 = new File(
//				"/Users/alex/Dropbox/workspace/BeatMaker/war/SoundClips/Cymbal tap 6.wav");
//		AudioInputStream stream1 = AudioSystem.getAudioInputStream(file1);
//		Clip clip1 = AudioSystem.getClip();
//		clip1.open(stream1);
//
//		File file2 = new File("/Users/alex/Dropbox/workspace/BeatMaker/war/SoundClips/Militia.wav");
//		AudioInputStream stream2 = AudioSystem.getAudioInputStream(file2);
//		Clip clip2 = AudioSystem.getClip();
//		clip2.open(stream2);
//		Line[] line = { clip1, clip2 };
//
//	}

}
