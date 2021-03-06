package fr.gagoi.music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip; 

import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface IAudio {
	
	public void AddAudio(String name);
	public void LoadAudio(String name)
			throws IOException, UnsupportedAudioFileException, 
			IndexOutOfBoundsException, LineUnavailableException;
	public AudioInputStream getAudioStream();
	
	public void InitClip()
			throws IOException, LineUnavailableException;
	public void CloseClip();
	public Clip GetClip();
	public void Play();
	public void Play(String name);
	public void SetVolume(double v);
	public double GetVolume();
}
