import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.entities.Player;
import fr.gagoi.engine.graphics.TextureManager;
import fr.gagoi.engine.graphics.BackGround;
import fr.gagoi.engine.graphics.GUI.MainMenu;
import fr.wiyochi.level.Level;

public class Test {
	
	public static void main(String[] args) {
		Game.init("TEST", new Dimension(1280, 720),3);

		loadTextures();
		
		Player p = new Player();
		
		p.setActive(false);
		p.setActiveRender(false);
		
		MainMenu menu = new MainMenu();
		Game.rmBackGround();

		Game.setGameState(Game.STATE_MENU);
		Game.start();
		
		Game.addElement(menu);
		Game.addElement(p);
	}

	private static void loadTextures() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(System.getenv(
					"resourcesPath") + "/levels/char-texture.txt"));
			prop.forEach((k, v) -> {
				System.out.println(k + "-----" + v);
				TextureManager.loadTexture("" + v, "" + k);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
