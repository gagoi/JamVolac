package fr.gagoi.engine.graphics;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.List;

import javax.swing.JFrame;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.inputs.Keyboard;

@SuppressWarnings("serial")
public class Display extends JFrame {
	public GamePanel canvas;

	public Display(String title, Dimension size, int nbLayer) {
		this.setTitle(title);
		// this.setUndecorated(true);
		this.canvas = new GamePanel(size, nbLayer);
		this.getContentPane().add(this.canvas);
		this.setSize(size);
		this.setResizable(false);
		this.addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent e) {
				if (e.getNewState() == WindowEvent.WINDOW_CLOSING) {
					Game.isRunning = false;
					setVisible(false);
					dispose();
				}
			}
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(new Keyboard());
	}

	public void activeRender(boolean needRender) {
		this.canvas.setRenderNeeding(needRender);
	}

	public List<IRenderable> getElements() {
		return canvas.renderables;
	}
}
