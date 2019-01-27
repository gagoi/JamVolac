package fr.gagoi.engine.graphics.GUI;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import fr.gagoi.engine.entities.IUpdatable;

public abstract class EButton extends EGuiElement  implements MouseListener {

	private boolean needRender = true;
	private boolean isActive = true;
	Polygon p;

	public EButton(String id, Polygon p) {
		super(id, p);
	}

	public abstract void action();

	@Override
	public void mouseClicked(MouseEvent e) {
		if (p.contains(e.getPoint()))
			action();
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
