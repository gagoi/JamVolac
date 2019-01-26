import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.entities.Entity;
import fr.gagoi.engine.entities.Hitbox;
import fr.gagoi.engine.entities.Player;
import fr.gagoi.engine.graphics.TextureManager;
import fr.gagoi.engine.graphics.GUI.MainMenu;
import fr.gagoi.music.MusicManager;
import fr.gagoi.music.SoundManager;
import fr.wiyochi.level.Level;

public class Test {
	
	public static void main(String[] args) {
		Game.init("TEST", new Dimension(1280, 720), 2);

		loadTextures();
		TextureManager.loadTexture("player1", "player");

		Level l = new Level(0, System.getenv("resourcesPath") + "/levels/level1");
		Player e = new Player();
		Entity p1 = new Entity("pickup1", 5.0, 4, new Hitbox(300, 500, 8, 21));
		p1.LoadAnimation("Animation1.");
		
		//Game.addElement(l);
		Game.addElement(e);
		Game.addElement(p1);
		
		
		
		MainMenu menu = new MainMenu();
		Game.setGameState(Game.STATE_MENU);
		
		

		Game.start();
		
		
		MusicManager m = new MusicManager();
		try {
			m.AddAudio("Mariachis");
		} catch (Exception err) {
			err.printStackTrace();
		}
		
		SoundManager s = new SoundManager();
		try {
			s.AddAudio("Chute");
		} catch (Exception err) {
			err.printStackTrace();
		}		
	}

	private static void loadTextures() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(System.getenv("resourcesPath") + "/levels/char-texture.txt"));
			prop.forEach((k, v) -> {
				System.out.println(k + "-----" + v);
				TextureManager.loadTexture("" + v, "" + k);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
