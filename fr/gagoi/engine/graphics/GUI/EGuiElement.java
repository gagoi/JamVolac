package fr.gagoi.engine.graphics.GUI;

import java.awt.Point;
import java.awt.Polygon;

import fr.gagoi.engine.IGameElement;
import fr.gagoi.engine.entities.IUpdatable;
import fr.gagoi.engine.graphics.IRenderable;

public abstract class EGuiElement extends IGameElement implements IRenderable {

	private String id;
	private Polygon hitbox;

	public EGuiElement(String id, int x, int y, int w, int h) {
		this.id = id;
		this.hitbox = new Polygon(new int[] { x, x + w, x + w, x }, new int[] { y, y, y + h, y + h }, 4);
	}
	
	public EGuiElement(String id, Polygon p) {
		this.id = id;
		this.hitbox = p;
	}

	public boolean isInside(Point p) {
		return hitbox.contains(p);
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public int getLayer() {
		return 0;
	}
}
