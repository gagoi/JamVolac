package fr.gagoi.engine.graphics.GUI;

import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class EButton extends EGuiElement implements MouseListener{

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

}
