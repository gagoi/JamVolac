import java.awt.Dimension;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.entities.Player;
import fr.gagoi.engine.graphics.TextureManager;

public class Test {

	public static void main(String[] args) {
		Game.init("TEST", new Dimension(1280, 720), 2);
		Game.start();
		
		TextureManager.loadTexture("player", "player");
		
		Player e = new Player();
		Game.addElement(e);
	}
}
