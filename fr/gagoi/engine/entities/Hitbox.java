package fr.gagoi.engine.entities;

import java.awt.Polygon;

public class Hitbox {

	private Polygon p;

	public Hitbox(int x, int y, int width, int height) {
		p.addPoint(x, y);
		p.addPoint(x + width, y);
		p.addPoint(x + width, y + height);
		p.addPoint(x, y + height);
	}

	public Hitbox(Polygon p2) {
		this.p = new Polygon(p2.xpoints, p2.ypoints, p2.npoints);
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
}
