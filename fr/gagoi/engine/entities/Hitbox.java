package fr.gagoi.engine.entities;

import java.awt.Polygon;

public class Hitbox {

	private Polygon p;

	public Hitbox(int x, int y, int width, int height) {
		p = new Polygon();
		p.addPoint(x, y);
		p.addPoint(x + width, y);
		p.addPoint(x + width, y + height);
		p.addPoint(x, y + height);
	}

	public boolean collide(Hitbox hitbox) {
		return p.intersects(hitbox.p.getBounds2D());
	}
	
	public int getX() {
		return p.xpoints[0];
	}
	
	public int getY() {
		return p.ypoints[0];
	}
	
	public int getWidth() {
		return p.xpoints[1] - p.xpoints[0];
	}
	
	public int getHeight() {
		return p.ypoints[2] - p.ypoints[0];
	}

	public void translate(int i, int j) {
		p.translate(i, j);
	}
}
