import java.awt.Dimension;

import fr.gagoi.engine.Game;

public class Test {

	public static void main(String[] args) {
		Game.init("TEST", new Dimension(1280, 720), 2);
		Game.start();
		
	}
}
