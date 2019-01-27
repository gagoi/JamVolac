package fr.wiyochi.level;

import java.awt.Graphics;

import fr.gagoi.engine.entities.Entity;
import fr.gagoi.engine.entities.Hitbox;

public class Block extends Entity {
	
	public Block(String id, int v, int nbSprites, Hitbox hitbox) {
		super(id, v, nbSprites, hitbox);
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
	}
	
	@Override
	public int getLayer() {
		return 1;
	}
}
