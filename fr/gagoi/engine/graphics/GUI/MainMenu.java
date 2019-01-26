package fr.gagoi.engine.graphics.GUI;

import java.awt.Polygon;

import fr.wiyochi.level.Level;

public class MainMenu {
	
	public static int STATE_MENU = 0;
	public static int STATE_LVL1 = 1;
	public static int STATE_LVL2 = 2;
	public static int STATE_LVL3 = 2;
	public static int STATE_LVL4 = 2;
	
	
	private EButton[] buttons;
	private Level[] levels;
	private static int GAME_STATE = 0;
	
	public MainMenu() {
		buttons = new EButton[4];
		levels = new Level[4];
		
		for (int i = 0; i < levels.length; i++)
			levels[i] = new Level(0, System.getenv("resourcesPath") + "/levels/level" + (i + 1));
		
		buttons[0] = new EButton("button_1", new Polygon(new int[] {}, new int[] {}, 0)) {
			@Override
			public void action() {
				
			}
		};
	}
	
}
	