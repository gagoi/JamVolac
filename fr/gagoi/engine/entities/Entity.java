package fr.gagoi.engine.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import fr.gagoi.engine.IGameElement;
import fr.gagoi.engine.graphics.IRenderable;
import fr.gagoi.engine.graphics.TextureManager;

public class Entity extends IGameElement implements IRenderable, IUpdatable {

	protected Hitbox hitbox;
	private String id;
	private int nbSprites;
	private int vitesse;
	private BufferedImage[] img;
	
	public Entity(String id) {
		this.id = id;
		this.nbSprites = 1;
	}
	
	public Entity(String id, int nbSprites, int vitesse) {
		this(id);
		this.img = new BufferedImage[nbSprites];
		this.nbSprites = nbSprites;
		this.vitesse = vitesse;
	}
	
	public Entity(String id, Hitbox hitbox) {
		this(id);
		this.hitbox = hitbox;
	}

	@Override
	public void render(Graphics g) {
		if (nbSprites == 1) 
			g.drawImage(TextureManager.getTexture(getId()), hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight(), null);
		if (nbSprites > 1)
			g.drawImage(img[(int) ((System.nanoTime() / (1000 / vitesse) ) % nbSprites)], hitbox.getX(), hitbox.getY(), null);
	}
	
	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public void update(List<IUpdatable> map) {
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return super.toString() + String.format("{%s, %d, %d, %d, %d}", getId(), hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
	}
}
