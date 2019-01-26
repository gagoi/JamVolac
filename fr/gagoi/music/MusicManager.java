package fr.gagoi.music;

import java.io.File; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

public class MusicManager implements IAudio {
	
	private Map<String, File> ListeMusic;
	private AudioInputStream MusicStream;
	private Clip clip;
	
	public MusicManager()
	{
		ListeMusic = new HashMap<String, File>();
	}
	
	public void AddAudio(String name)
	{
		File file = new File(System.getenv("resourcesPath")
				+ "/music/" + name  + ".wav");
		ListeMusic.put(name, file);
	}
	
	public void LoadAudio(String name)
			throws IOException, UnsupportedAudioFileException,
			IndexOutOfBoundsException, LineUnavailableException
	{
		try {
			MusicStream = AudioSystem.getAudioInputStream(ListeMusic.get(name));
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
	
	public void Play(String name)
	{
		try {
			LoadAudio(name);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
