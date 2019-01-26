package fr.gagoi.engine.entities;

import java.awt.Polygon;

public class Hitbox {

	public Polygon p;

	public Hitbox() {
		p = new Polygon();
	}

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

	public int whereCollision(Hitbox hitbox, float dx, float dy) {
		int location = 0;
		/*
		 * location = 1 : l'objet collisionne � droite location = 2 : l'objet
		 * collisionne en bas location = 3 : l'objet collisionne � gauche location = 4 :
		 * l'objet collisionne en haut
		 */
		int x1 = hitbox.getX();
		int x2 = hitbox.getX() + hitbox.getWidth();
		int y1 = hitbox.getY();
		int y2 = hitbox.getY() + hitbox.getHeight();

		//System.out.println("Player : " + this.toString());
		
		if (dy > 0)
			location = 2;
		else if (dy < 0)
			location = 4;
		/*
		 * if ((this.getX() <= x2) && (this.getX() + this.getWidth() >= x1)) { if
		 * (this.getY() + dy > y2) location = 4; else if (this.getY() + this.getHeight()
		 * + dy < y1) location = 2; } else if (this.getX() + this.getWidth() + dx > x1)
		 * location = 1; else if (this.getX() + dx < x2) location = 3;
		 * 
		 * // if (this.getY()+this.getHeight()+dy>y1) // location = 2;
		 */
		return (location);
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

	@Override
	public String toString() {
		return super.toString() + String.format("{%d, %d, %d, %d}", getX(), getY(), getWidth(), getHeight());
	}
}
