import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.entities.Player;
import fr.gagoi.engine.graphics.TextureManager;
import fr.wiyochi.level.Level;

public class Test {

	public static void main(String[] args) {
		Game.init("TEST", new Dimension(1280, 720), 2);
		
<<<<<<< HEAD
		loadTextures();
		TextureManager.loadTexture("player", "player");

		Level l = new Level(0, System.getenv("resourcesPath") + "/levels/test.txt");
=======
		TextureManager.loadTexture("player1", "player");
		
>>>>>>> 13b4b0b2b09cbf28dc4307514e962016ed067e03
		Player e = new Player();
		
		
		Game.addElement(e);
		Game.addElement(l);
		
		Game.start();
	}
	
	private static void loadTextures() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(System.getenv("resourcesPath") + "/levels/char-texture.txt"));
			prop.forEach((k, v) -> {TextureManager.loadTexture("" + v, "" + k);});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
