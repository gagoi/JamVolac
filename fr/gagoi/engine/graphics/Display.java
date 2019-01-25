package fr.gagoi.engine.graphics;

import java.awt.Dimension;
import java.util.List;

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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void activeRender(boolean needRender) {
		this.canvas.setRenderNeeding(needRender);
	}

	public List<IRenderable> getElements() {
		return canvas.renderables;
	}
}
