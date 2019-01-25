import java.awt.Dimension;
import java.io.File;

import fr.gagoi.engine.Game;
import fr.wiyochi.level.Loader;

public class Test {

	public static void main(String[] args) {
		Loader loader = new Loader();
		
		loader.loadLevel(System.getenv("resourcesPath") + "/levels/test.txt");
		
		Game.init("TEST", new Dimension(1280, 720), 2);
		Game.start();
		
	}
}
