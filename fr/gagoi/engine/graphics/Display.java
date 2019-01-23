package fr.gagoi.engine.graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Display extends JFrame {
	private GameCanvas canvas;

	public Display(String title, Dimension size, int nbLayer) {
		this.setTitle(title);
		this.setUndecorated(true);
		this.canvas = new GameCanvas(size, nbLayer);
		this.getContentPane().add(this.canvas);
		this.setSize(size);
		this.setResizable(false);
	}

	public void activeRender(boolean needRender) {
		this.canvas.setRenderNeeding(needRender);
	}
}
