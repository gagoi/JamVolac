package fr.gagoi.engine.entities;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import fr.gagoi.engine.graphics.IRenderable;

public class Entity implements IRenderable, IUpdatable {

	private Hitbox hitbox;
	private String id;
	private int nbSprites;
	private int vitesse;
	private BufferedImage[] img;
	
	public Entity(String id) {
		this.id = id;
	}
	
	public Entity(String id, int nbSprites, int vitesse) {
		this(id);
		this.img = new BufferedImage[nbSprites];
		this.nbSprites = nbSprites;
		this.vitesse = vitesse;
	}
	
	public Entity addHitbox(Polygon p) {
		this.hitbox = new Hitbox(p);
		return this;
	}
	
	@Override
	public void render(Graphics g) {
		if (nbSprites > 1)
			g.drawImage(img[(int) ((System.nanoTime() / (1000 / vitesse) ) % nbSprites)], hitbox.getX(), hitbox.getY(), null);
	}
	
	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public void update(HashMap<String, IUpdatable> map) {
		
	}
	
	@Override
	public String getId() {
		return this.id;
	}
}
