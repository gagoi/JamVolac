package fr.gagoi.music;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

public class MusicManager implements IAudio {
	
	private Map<String, File> ListeMusic;
	private AudioInputStream MusicStream;
	private Clip clip;
	FloatControl gainControl;
	
	public MusicManager()
	{
		ListeMusic = new HashMap<String, File>();
	}
	
	@Override
	public void AddAudio(String name)
	{
		File file = new File(System.getenv("resourcesPath")
				+ "/music/" + name  + ".wav");
		ListeMusic.put(name, file);
	}
	
	@Override
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
	
	@Override
	public AudioInputStream getAudioStream()
	{
		return(MusicStream);
	}
	
	@Override
	public void InitClip()
		throws IOException, LineUnavailableException
	{
		try {
			clip = AudioSystem.getClip();
			clip.open(MusicStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
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
	
	@Override
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
