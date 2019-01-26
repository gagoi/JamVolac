package fr.gagoi.music;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager implements IAudio {
	
	private Map<String, File> ListeSound;
	private AudioInputStream SoundStream;
	private Clip clip;
	FloatControl gainControl;
	
	public SoundManager()
	{
		ListeSound = new HashMap<>(); 
	}
	
	@Override
	public void AddAudio(String name)
	{
		File file = new File(System.getenv("resourcesPath") +
				"/sound/" + name + ".wav");
		ListeSound.put(name, file);
	}

	@Override
	public void LoadAudio(String name)
			throws IOException, UnsupportedAudioFileException,
			IndexOutOfBoundsException, LineUnavailableException
	{
		try {
			SoundStream = AudioSystem.getAudioInputStream(ListeSound.get(name));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("L'index donn� est trop grand");
			throw(e);
		} catch (UnsupportedAudioFileException e){
			System.out.println("Le fichier audio ne peut �tre lu");
			throw(e);
		} catch (IOException e) {
			System.out.println("Le fichier n'a pas �t� trouv�");
			throw(e);
		}
		
		try {
			InitClip();
		} catch (Exception e) {
			System.out.println("L'initialisation du clip a �chou�e");
			throw(e);
		}
	}

	@Override
	public AudioInputStream getAudioStream()
	{
		return(SoundStream);
	}

	@Override
	public void InitClip()
			throws IOException, LineUnavailableException
	{
		try {
			clip = AudioSystem.getClip();
			clip.open(SoundStream);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (IOException e) {
			System.out.println("Le fichier n'a pas �t� trouv�");
			throw(e);
		} catch (LineUnavailableException e) {
			System.out.println("Probl�me de d�marrage");
			throw(e);
		}
	}

	@Override
	public void CloseClip()
	{
		clip.close();
	}

	@Override
	public Clip GetClip()
	{
		return(clip);
	}

	@Override
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
	
	@Override
	public void SetVolume(double v)
	{
		gainControl.setValue((float) (20.0 * Math.log10(v)));
	}
	
	@Override
	public double GetVolume()
	{
		return(Math.pow(10.0, gainControl.getValue() / 20.0));
	}
}
