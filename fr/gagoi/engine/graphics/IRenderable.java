package fr.gagoi.engine.graphics;

import java.awt.Graphics;

public interface IRenderable {
	
	public void render(Graphics g);
	
	public int getLayer();

	public void setActiveRender(boolean b);

	public boolean needRender();
}
