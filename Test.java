import java.awt.Dimension;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.entities.Entity;
import fr.gagoi.engine.graphics.TextureManager;
import fr.gagoi.music.MusicManager;

import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

public class Test {

	public static void main(String[] args) {
		Game.init("TEST", new Dimension(1280, 720), 2);
		Game.start();
		
		TextureManager.loadTexture("test.png", "test");
		
		Entity e = new Entity("test");
		Game.addElement(e);
		
		MusicManager m = new MusicManager();
		try {
			m.AddMusic("Mariachis.wav");
			m.LoadMusic(0);
			m.Play();
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
}
