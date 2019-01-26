package fr.gagoi.music;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager implements IAudio {
	
	private List<File> ListeSound;
	private AudioInputStream SoundStream;
	private Clip clip;
	
	public SoundManager()
	{
		ListeSound = new ArrayList<>();
	}
	
	@Override
	public void AddAudio(String name)
	{
		File file = new File(System.getenv("resourcesPath") + "/sound/" + name);
		ListeSound.add(file);
	}

	@Override
	public void LoadAudio(int index)
			throws IOException, UnsupportedAudioFileException,
			IndexOutOfBoundsException, LineUnavailableException
	{
		try {
			SoundStream = AudioSystem.getAudioInputStream(ListeSound.get(index));
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
		} catch (IOException e) {
			System.out.println("Le fichier n'a pas été trouvé");
			throw(e);
		} catch (LineUnavailableException e) {
			System.out.println("Problème de démarrage");
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
}
