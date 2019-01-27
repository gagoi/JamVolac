package fr.gagoi.engine.graphics.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.entities.IUpdatable;

public abstract class EButton extends EGuiElement  implements MouseListener {

	private boolean needRender = true;
	private boolean isActive = true;
	private int level;
	Polygon p;
	BufferedImage im;

	public EButton(String id, Polygon p, int lvl) {
		super(id, p);
		im = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		level = lvl;
	}

	public abstract void action(int lvl);

	@Override
	public void mouseClicked(MouseEvent e) {
		if (getHitbox().contains(e.getPoint()))
			action(level);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	
	@Override
	public void render(Graphics g) {
		if(needRender) {
			g.setColor(Color.BLUE);
			g.fillPolygon(getHitbox());
		}
	}

	@Override
	public void setActiveRender(boolean b) {
		needRender = b;
	}
	@Override
	public boolean needRender() {
		return needRender;
	}
}
