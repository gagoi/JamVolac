package fr.gagoi.engine.graphics.GUI;

import java.awt.Polygon;

import fr.gagoi.engine.Game;
import fr.wiyochi.level.Level;

public class MainMenu {
	private EButton[] buttons;
	private Level[] levels;
	private int[][][] butpos = { { {}, {} }, { {}, {} }, { {}, {} }, { {}, {} }, };

	public MainMenu() {
		buttons = new EButton[4];
		levels = new Level[4];

		for (int i = 0; i < levels.length; i++) {
			levels[i] = new Level(i+1, System.getenv("resourcesPath") + "/levels/level" + (i + 1));

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

}
