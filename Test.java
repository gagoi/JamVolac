import java.awt.Dimension;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.entities.Entity;
import fr.gagoi.engine.graphics.TextureManager;
import fr.gagoi.music.MusicManager;
import fr.gagoi.music.SoundManager;

public class Test {

	public static void main(String[] args) {
		Game.init("TEST", new Dimension(1280, 720), 2);
		Game.start();
		
		TextureManager.loadTexture("test.png", "test");
		
		Entity e = new Entity("test");
		Game.addElement(e);
		
		MusicManager m = new MusicManager();
		try {
			m.AddAudio("Mariachis");
			m.Play("Mariachis");
			m.SetVolume(0.5);
		} catch (Exception err) {
			err.printStackTrace();
		}
		
		SoundManager s = new SoundManager();
		try {
			s.AddAudio("Chute");
			s.Play("Chute");
		} catch (Exception err) {
			err.printStackTrace();
		}		
	}
}
