package fr.gagoi.engine.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private BufferedImage[] img;
	private boolean needRender = true;
	protected List<IRenderable> renderables;
	private Dimension size;
	private int nbLayers;

	public GamePanel(Dimension size, int nbLayers) {
		this.size = size;
		this.nbLayers = nbLayers;
		img = new BufferedImage[nbLayers + 1];
		renderables = new ArrayList<IRenderable>();
		for (int i = 0; i < nbLayers + 1; ++i)
			img[i] = new BufferedImage(size.width, size.height,
					BufferedImage.TYPE_INT_ARGB);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (needRender)
			render(g);

	}

	private void clear() {
		for (BufferedImage bufferedImage : img) {
			bufferedImage.getGraphics().clearRect(0, 0, size.width, size.height);
		}
	}

	
	public void render(Graphics g) {
		clear();
		for (IRenderable rend : renderables) {
			if (rend.needRender())
				rend.render(img[rend.getLayer()].getGraphics());
		}
		for (int i = 0; i < nbLayers; i++) {
			img[nbLayers].getGraphics().drawImage(img[i], 0, 0, null);
		}
		g.drawImage(img[nbLayers], 0, 0, null);
	}

	public void setRenderNeeding(boolean rend) {
		needRender = rend;
	}
}
