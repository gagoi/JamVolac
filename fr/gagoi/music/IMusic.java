package fr.gagoi.music;

import java.io.IOException;
import java.util.Scanner; 
  
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public interface IMusic {
	
	public void AddMusic(String name);
	public void LoadMusic(int index)
			throws IOException, UnsupportedAudioFileException, 
			IndexOutOfBoundsException, LineUnavailableException;
	public AudioInputStream getAudioStream();
	
	public void InitClip()
			throws IOException, LineUnavailableException;
	public void CloseClip();
	public Clip GetClip();
	public void Play();
	public void setVolume(double v);
	public float getVolume();
}
