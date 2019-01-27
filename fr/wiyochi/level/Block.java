package fr.wiyochi.level;

import java.awt.Graphics;

import fr.gagoi.engine.entities.Entity;
import fr.gagoi.engine.entities.Hitbox;
import fr.gagoi.engine.graphics.TextureManager;

public class Block extends Entity {

	public Block(String id, int v, int nbSprites, Hitbox hitbox) {
		super(id, v, nbSprites, hitbox);
	}

	@Override
	public void render(Graphics g) {
		if (needRender()) {
			super.render(g);
			//g.drawImage(TextureManager.getTexture(id),(int) hitbox.getX(), (int) hitbox.getY(), 32, 32, null);
		}
	}

	@Override
	public int getLayer() {
		return 2;
	}
}
