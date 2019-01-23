package fr.gagoi.engine;

import java.awt.Dimension;
import java.util.HashMap;

import fr.gagoi.engine.entities.IUpdatable;
import fr.gagoi.engine.graphics.Display;

public class Game implements Runnable {

	private static Game game = null;

	private Display window;
	private boolean isRunning;
	private HashMap<String, IUpdatable> updatables = new HashMap<String, IUpdatable>();

	public static void init(String name, Dimension size, int nbLayer) {
		if (game == null) {
			game = new Game();
			game.window = new Display(name, size, nbLayer);
			game.window.setVisible(true);
			game.window.activeRender(true);
		}
	}

	public static void start() {
		game.window.setVisible(true);
	}

	@Override
	public void run() {
		isRunning = true;

		while (isRunning)
			updatables.forEach((k, v) -> {
				v.update(updatables);
			});
	}
}
