package fr.gagoi.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;

import fr.gagoi.engine.entities.IUpdatable;
import fr.gagoi.engine.entities.Pickup;
import fr.gagoi.engine.graphics.Display;
import fr.gagoi.engine.graphics.IRenderable;

import fr.gagoi.music.MusicManager;
import fr.wiyochi.level.Level;

public class Game implements Runnable {

	public static final int STATE_MENU = 0;
	public static final int STATE_LVL1 = 1;
	public static final int STATE_LVL2 = 2;
	public static final int STATE_LVL3 = 3;
	public static final int STATE_LVL4 = 4;

	private static int GAME_STATE = -1;
	private static Game game = null;

	private Display window;
	public static boolean isRunning;
	private static List<IUpdatable> updatables = new ArrayList<IUpdatable>();
	private MusicManager music;

	public static void init(String name, Dimension size, int nbLayer) {
		if (game == null) {
			game = new Game();
			game.window = new Display(name, size, nbLayer);
			game.window.activeRender(true);
			game.window.setBackground(Color.WHITE);;
			game.window.getContentPane().setBackground(Color.WHITE);;
			game.window.getContentPane().setVisible(true);
			game.music = new MusicManager();
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
	
	private static void loadPickups(String name_lvl){
		Level l = null;
		for (IUpdatable iUpdatable : updatables) {
			if (((IGameElement) iUpdatable).getId().equals(name_lvl)) {
				l = (Level) iUpdatable;
				break;
			}
		}
		for (Pickup p : l.getPickups()) {
			addElement(p);
		}
	}
	
	private static void unloadPickups(){
		for (IUpdatable obj : updatables) {
			if(obj instanceof Pickup)
				updatables.remove(obj);
		}
	}
	
	@Override
	public void run() {
		isRunning = true;

		long startTimer = System.currentTimeMillis();
		long actualTimer = startTimer;
		long fpsTimer = startTimer;
		float updateTime = 1000 / 120;
		int fps = 0;

		while (isRunning) {
			actualTimer = System.currentTimeMillis();
			if (updateTime <= actualTimer - startTimer) {
				fps++;
				updatables.forEach((e) -> {
					e.update(updatables);
				});
				game.window.canvas.repaint();
				startTimer = System.currentTimeMillis();
			}
			try {
				Thread.sleep((long) (updateTime - (actualTimer - startTimer)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (1000 <= actualTimer - fpsTimer) {
				System.out.println(fps);
				fps = 0;
				fpsTimer = System.currentTimeMillis();
			}
		}
	}

	public MusicManager getMusic() {
		return this.music;
	}
	
	private static void disable(String id) {
		System.out.println("Disabling " + id);
		for (IUpdatable iUpdatable : updatables) {
			if (((IGameElement) iUpdatable).getId().equals(id)) {
				iUpdatable.setActive(false);
			}
		}
		
		for (IRenderable iRenderable : game.window.getElements()) {
			if (((IGameElement) iRenderable).getId().equals(id)) {
				iRenderable.setActiveRender(false);
				System.out.println(iRenderable.needRender());
			}
		}
	}
	
	private static void enable(String id) {
		System.out.println("Enabling " + id);
		for (IUpdatable iUpdatable : updatables) {
			if (((IGameElement) iUpdatable).getId().equals(id)) {
				iUpdatable.setActive(true);
				break;
			}
		}
		
		for (IRenderable iRenderable : game.window.getElements()) {
			if (((IGameElement) iRenderable).getId().equals(id)) {
				iRenderable.setActiveRender(true);
				break;
			}
		}
	}

	public static void setGameState(int newGameState) {
		if (GAME_STATE != newGameState && newGameState >= STATE_MENU && newGameState <= STATE_LVL4) {
			game.window.activeRender(false);
			GAME_STATE = newGameState;
			switch (GAME_STATE) {
			case STATE_MENU:
				disable("level_1");
				disable("level_2");
				disable("level_3");
				disable("level_4");
				disable("player");
				enable("button_1");
				enable("button_2");
				enable("button_3");
				enable("button_4");
				unloadPickups();
				break;
			case STATE_LVL1:
				enable("level_1");
				disable("level_2");
				disable("level_3");
				disable("level_4");
				enable("player");
				disable("button_1");
				disable("button_2");
				disable("button_3");
				disable("button_4");
				unloadPickups();
				loadPickups("level_1");
				break;
			case STATE_LVL2:
				disable("level_1");
				enable("level_2");
				disable("level_3");
				disable("level_4");
				enable("player");
				disable("button_1");
				disable("button_2");
				disable("button_3");
				disable("button_4");
				unloadPickups();
				loadPickups("level_2");
				break;
			case STATE_LVL3:
				disable("level_1");
				disable("level_2");
				disable("level_3");
				enable("level_4");
				enable("player");
				disable("button_1");
				disable("button_2");
				disable("button_3");
				disable("button_4");
				unloadPickups();
				loadPickups("level_3");
				break;
			case STATE_LVL4:
				disable("level_1");
				disable("level_2");
				disable("level_3");
				enable("level_4");
				enable("player");
				disable("button_1");
				disable("button_2");
				disable("button_3");
				disable("button_4");
				unloadPickups();
				loadPickups("level_4");
				break;
			}

			game.window.activeRender(true);
		}
	}
}
