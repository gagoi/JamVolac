package fr.gagoi.engine;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import fr.gagoi.engine.entities.IUpdatable;
import fr.gagoi.engine.graphics.Display;
import fr.gagoi.engine.graphics.IRenderable;

public class Game implements Runnable {

	private static Game game = null;

	private Display window;
	private boolean isRunning;
	private static List<IUpdatable> updatables = new ArrayList<IUpdatable>();

	public static void init(String name, Dimension size, int nbLayer) {
		if (game == null) {
			game = new Game();
			game.window = new Display(name, size, nbLayer);
			game.window.activeRender(true);
		}
	}

	public static void start() {
		game.window.setVisible(true);
		new Thread(game).start();
	}

	public static void addElement(IGameElement element) {
		if (element instanceof IUpdatable) {
			if (!updatables.contains((IUpdatable) element)) {
				updatables.add((IUpdatable) element);
			} else {
				System.out.println(String.format("UPDATE : ID already used when adding : ", element.toString()));
			}
		}
		
		if (element instanceof IRenderable) {
			if (!game.window.getElements().contains((IRenderable) element)) {
				game.window.getElements().add((IRenderable) element);
			} else {
				System.out.println(String.format("RENDER : ID already used when adding : ", element.toString()));
			}
		}
	}

	@Override
	public void run() {
		isRunning = true;
		while (isRunning) {
			updatables.forEach((e) -> {
				e.update(updatables);
			});
			game.window.repaint();
		}
	}
}
