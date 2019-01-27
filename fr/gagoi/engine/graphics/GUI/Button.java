package fr.gagoi.engine.graphics.GUI;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import fr.gagoi.engine.entities.Entity;

public class Button extends Entity implements MouseListener{
	
	private boolean needRender = true;
	private boolean isActive = true;
	Polygon p;
	int level;
	BufferedImage im;
	Color c = Color.RED;
	
	public Button(String id, Polygon p, int lvl) {
		super(id);
		this.p=p;
		im = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		level = lvl;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Clic");
		if (p.contains(e.getPoint())) {
			System.out.println("Chargement du niveau ");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		c = Color.BLUE;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		c = Color.RED;
	}
}
