import java.awt.Dimension;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.entities.Entity;
import fr.gagoi.engine.entities.Hitbox;
import fr.gagoi.engine.graphics.TextureManager;

public class Test {

	public static void main(String[] args) {
		Game.init("TEST", new Dimension(1280, 720), 2);
		Game.start();
		
		TextureManager.loadTexture("test.png", "test");
		
		Entity e = new Entity("test", new Hitbox(20, 20, 16, 16));
		Game.addElement(e);
	}
}
