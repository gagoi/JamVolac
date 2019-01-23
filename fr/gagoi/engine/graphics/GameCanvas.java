package fr.gagoi.engine.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class GameCanvas extends Canvas {

	private BufferedImage[] img;
	private boolean needRender;
	private List<IRenderable> renderables;
	private Dimension size;

	public GameCanvas(Dimension size, int nbLayer) {
		this.size = size;
		img = new BufferedImage[nbLayer];
		renderables = new ArrayList<IRenderable>();
		for (int i = 0; i < nbLayer; ++i)
			img[i] = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
		setPreferredSize(size);
	}

	@Override
	public void paint(Graphics g) {
		if(needRender)
			render(g);
			
	}

	private void clear() {
		for (BufferedImage bufferedImage : img) {
			bufferedImage.getGraphics().clearRect(0, 0, size.width, size.height);
		}
	}
	
	public void render(Graphics g) {
		clear();
		for (IRenderable rend : renderables)
			rend.render(img[rend.getLayer()].getGraphics());
	}

	public void setRenderNeeding(boolean rend) {
		needRender = rend;
	}
}
