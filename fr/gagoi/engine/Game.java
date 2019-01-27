package fr.gagoi.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import fr.gagoi.engine.entities.IUpdatable;
import fr.gagoi.engine.entities.Pickup;
import fr.gagoi.engine.graphics.BackGround;
import fr.gagoi.engine.graphics.Display;
import fr.gagoi.engine.graphics.IRenderable;
import fr.gagoi.engine.graphics.GUI.MainMenu;
import fr.gagoi.music.MusicManager;
import fr.wiyochi.level.Block;
import fr.wiyochi.level.Level;

public class Game implements Runnable {

	public static final int STATE_MENU = 0;
	public static final int STATE_LVL1 = 1;
	public static final int STATE_LVL2 = 2;
	public static final int STATE_LVL3 = 3;
	public static final int STATE_LVL4 = 4;

	public static int GAME_STATE = -1;
	
	public static Game game = null;
	private Display window;
	public static boolean isRunning;
	public static List<IUpdatable> updatables = new ArrayList<IUpdatable>();
	private MusicManager music;

	public static void init(String name, Dimension size, int nbLayer) {
		if (game == null) {
			game = new Game();
			game.window = new Display(name, size, nbLayer);
			game.window.activeRender(true);
			game.window.getContentPane().setBackground(Color.WHITE);;
			game.window.getContentPane().setVisible(true);
			game.music = new MusicManager();
		}
	}

	public static void start() {
		game.window.setVisible(true);
		new Thread(game).start();
	}

	public static void addElement(Object element) {
		if (element instanceof IUpdatable) {
			if (!updatables.contains((IUpdatable) element)) {
				updatables.add((IUpdatable) element);
			} 
//			else {
//				System.out.println(String.format("UPDATE : ID already used when adding : ", element.toString()));
//			}
		}

		if (element instanceof IRenderable) {
			if (!game.window.getElements().contains((IRenderable) element)) {
				game.window.getElements().add((IRenderable) element);
//			} else {
//				System.out.println(String.format(
//				"RENDER : ID already used when adding : ", element.toString()));
//			}
			}
		}
	}
	
	private static void addBackGround(String name) {
		BackGround bg = new BackGround(name);
		System.out.println("Chargement fond");
		game.window.getElements().add((IRenderable) bg);
	}
	
	public static void rmBackGround() {
		IRenderable mem = null;
		for (IRenderable e : game.window.getElements()) {
			if(e instanceof BackGround)
				mem = e;
		}
		if(mem!=null)
			game.window.getElements().remove(mem);
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
	
	private static void loadBlocks(String name_lvl){
		Level l = null;
		for (IUpdatable iUpdatable : updatables) {
			if (((IGameElement) iUpdatable).getId().equals(name_lvl)) {
				l = (Level) iUpdatable;
				break;
			}
		}
		for (Block b : l.getBlocks()) {
			addElement(b);
		}
	}
	
	private static void unloadPickups(){
		for (IUpdatable obj : updatables) {
			if(obj instanceof Pickup)
				updatables.remove(obj);
		}
	}
	
	private static void unloadBlocks(){
		for (IUpdatable obj : updatables) {
			if(obj instanceof Block)
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
	
	public static void addMouse(MouseListener m) {
		game.window.addMouseListener(m);
	}

	public MusicManager getMusic() {
		return this.music;
	}
	
	private static void disable(String id) {
		for (IUpdatable iUpdatable : updatables) {
			if (((IGameElement) iUpdatable).getId().equals(id)) {
				iUpdatable.setActive(false);
			}
		}
		
		for (IRenderable iRenderable : game.window.getElements()) {
			if (((IGameElement) iRenderable).getId().equals(id)) {
				iRenderable.setActiveRender(false);
				System.out.println("Disabling " + id);
			}
		}
	}
	
	private static void enable(String id) {
		for (IUpdatable iUpdatable : updatables) {
			if (((IGameElement) iUpdatable).getId().equals(id)) {
				iUpdatable.setActive(true);
				System.out.println("Enabling " + id);
				break;
			}
		}
		
		for (IRenderable iRenderable : game.window.getElements()) {
			if (((IGameElement) iRenderable).getId().equals(id)) {
				iRenderable.setActiveRender(true);
				System.out.println("Enabling " + id);
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
				enable("menu");
				disable("level_1");
				disable("level_2");
				disable("level_3");
				disable("level_4");
				disable("player");
				enable("button_1");
				enable("button_2");
				enable("button_3");
				enable("button_4");
				MainMenu.activ = true;
				//Game.addBackGround("ImageMenu");
				unloadPickups();
				break;
			case STATE_LVL1:
				disable("menu");
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
				addBackGround("fondblanc");
				loadBlocks("level_1");
				MainMenu.activ = false;
				break;
			case STATE_LVL2:
				disable("menu");
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
				MainMenu.activ = false;
				break;
			case STATE_LVL3:
				disable("menu");
				disable("level_1");
				disable("level_2");
				disable("level_3");
				enable("level_4");
				enable("player");
				disable("button_1");
				disable("button_2");
				disable("button_3");
				disable("button_4");
				MainMenu.activ = false;
				unloadPickups();
				loadPickups("level_3");
				break;
			case STATE_LVL4:
				disable("menu");
				disable("level_1");
				disable("level_2");
				disable("level_3");
				enable("level_4");
				enable("player");
				disable("button_1");
				disable("button_2");
				disable("button_3");
				disable("button_4");
				MainMenu.activ = false;
				unloadPickups();
				loadPickups("level_4");
				break;
			}

			game.window.activeRender(true);
		}
	}
}
