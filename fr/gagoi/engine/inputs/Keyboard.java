package fr.gagoi.engine.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	public static int UP = 0;
	public static int LEFT = 1;
	public static int RIGHT = 2;
	
	public static boolean[] isKeyPressed = new boolean[3];

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_UP:
			isKeyPressed[UP] = true;
			break;
		case KeyEvent.VK_LEFT:
			isKeyPressed[LEFT] = true;
			break;
		case KeyEvent.VK_RIGHT:
			isKeyPressed[RIGHT] = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_UP:
			isKeyPressed[UP] = false;
			break;
		case KeyEvent.VK_LEFT:
			isKeyPressed[LEFT] = false;
			break;
		case KeyEvent.VK_RIGHT:
			isKeyPressed[RIGHT] = false;
			break;
		}
	}

}
