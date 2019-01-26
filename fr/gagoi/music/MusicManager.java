package fr.gagoi.music;

import java.io.File; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

public class MusicManager implements IMusic {
	
	private List<File> ListeMusic;
	private AudioInputStream MusicStream;
	private Clip clip;
	private FloatControl gainControl;
	
	public MusicManager()
	{
		ListeMusic = new ArrayList<>();
	}
	
	public void AddMusic(String name)
	{
		File file = new File(System.getenv("resourcesPath") + "/music/" + name);
		ListeMusic.add(file);
	}
	
	public void LoadMusic(int index)
			throws IOException, UnsupportedAudioFileException,
			IndexOutOfBoundsException, LineUnavailableException
	{
		try {
			MusicStream = AudioSystem.getAudioInputStream(ListeMusic.get(index));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("L'index donné est trop grand");
			throw(e);
		} catch (UnsupportedAudioFileException e){
			System.out.println("Le fichier audio ne peut être lu");
			throw(e);
		} catch (IOException e) {
			System.out.println("Le fichier n'a pas été trouvé");
			throw(e);
		}
		
		try {
			InitClip();
		} catch (Exception e) {
			System.out.println("L'initialisation du clip a échouée");
			throw(e);
		}
	}
	
	public AudioInputStream getAudioStream()
	{
		return(MusicStream);
	}
	
	public void InitClip()
		throws IOException, LineUnavailableException
	{
		try {
			clip = AudioSystem.getClip();
			clip.open(MusicStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (IOException e) {
			System.out.println("Le fichier n'a pas été trouvé");
			throw(e);
		} catch (LineUnavailableException e) {
			System.out.println("Problème de démarrage");
			throw(e);
		}
	}
	
	public void CloseClip()
	{
		clip.close();
	}
	
	public Clip GetClip()
	{
		return(clip);
	}
	
	public void Play()
	{
		clip.start();
	}
	
	public void InitGainControl()
	{
		 gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	}
	
	public void setVolume(double v)
	{        
	    gainControl.setValue(20f * (float) Math.log10(v));
	}
	
	public float getVolume()
	{
		return (float) Math.pow(10f, gainControl.getValue() / 20f);
	}
}
