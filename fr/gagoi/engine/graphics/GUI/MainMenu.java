package fr.gagoi.engine.graphics.GUI;

import java.awt.Graphics;
import java.awt.Polygon;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.graphics.IRenderable;
import fr.gagoi.engine.graphics.TextureManager;
import fr.wiyochi.level.Level;

public class MainMenu implements IRenderable{
	private EButton[] buttons;
	private Level[] levels;
	
	
	private boolean activ;
	
	private int[][][] butpos = { { { 0, 0, 598, 598 }, { 0, 455, 455, 0 } },
			{ { 638, 638, 483, 483, 1279, 1279 }, { 306, 495, 495, 719, 719, 306 } },
			{ { 639, 1279, 1279, 639 }, { 0, 0, 266, 266 } }, { { 0, 443, 443, 0 }, { 495, 495, 719, 719 } }, };

	public MainMenu() {
		
		TextureManager.loadTexture("ImageMenu", "menu");
		
		buttons = new EButton[4];
		levels = new Level[1];

		for (int i = 0; i < levels.length; i++) {
			levels[i] = new Level(i + 1, System.getenv("resourcesPath") + "/levels/level" + (i + 1));

			Game.addElement(levels[i]);
		}
		for (int i = 0; i < buttons.length; i++) {
			final int j = i;
			buttons[i] = new EButton("button_" + (i + 1),
					new Polygon(butpos[i][0], butpos[i][1], butpos[i][0].length)) {
				@Override
				public void action() {
					Game.setGameState(Game.STATE_LVL1 + j);
				}

			};
			Game.addElement(buttons[i]);
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(TextureManager.getTexture("menu"), 0, 0, null);
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public void setActiveRender(boolean b) {
		this.activ = b;
	}

	@Override
	public boolean needRender() {
		return activ;
	}
	
	

}
