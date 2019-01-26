package fr.gagoi.engine.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import fr.gagoi.engine.IGameElement;
import fr.gagoi.engine.graphics.IRenderable;
import fr.gagoi.engine.graphics.TextureManager;

import fr.gagoi.music.SoundManager;

public class Entity extends IGameElement implements IRenderable, IUpdatable {

	protected Hitbox hitbox;
	private String id;
	private int nbSprites;
	private double vitesse;
	protected BufferedImage[] img;
	private SoundManager sound;
	private boolean isActive = true, needRender = true;

	public Entity(String id) {
		this.id = id;
		this.nbSprites = 1;
		sound = new SoundManager();
	}

	public Entity(String id, int nbSprites, int vitesse) {
		this(id);
		this.img = new BufferedImage[nbSprites];
		this.nbSprites = nbSprites;
		this.vitesse = vitesse;
		sound = new SoundManager();
	}

	public Entity(String id, Hitbox hitbox) {
		this(id);
		this.hitbox = hitbox;
		sound = new SoundManager();
	}
	
	public Entity(String id, double vitesse, int nbSprites, Hitbox hitbox) {
		this(id);
		this.img = new BufferedImage[nbSprites];
		this.vitesse = vitesse;
		this.nbSprites = nbSprites;
		this.hitbox = hitbox;
		sound = new SoundManager();
	}

	@Override
	public void render(Graphics g) {
		if (needRender)
			if (nbSprites == 1)
				g.drawImage(TextureManager.getTexture(getId()), hitbox.getX(), hitbox.getY(), hitbox.getWidth(),
						hitbox.getHeight(), null);
			else if (nbSprites > 1)
				g.drawImage(img[(int) ((System.nanoTime() / (1000000000 / vitesse)) % nbSprites)], hitbox.getX(),
						hitbox.getY(), null);
	}

	public SoundManager getSound() {
		return this.sound;
	}

	public void LoadAnimation(String name) {
		File file = null;
		
		for(int i=0; i<nbSprites; i++) {
			file = new File(System.getenv("resourcesPath") +
					"/textures/" + name + (i+1) + ".png");
			try {
				img[i] = ImageIO.read(file);
			} catch (IOException e) {
				System.out.print("Problème d'ouverture");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public boolean hasInGameHitbox() {
		return isActive;
	}

	@Override
	public void update(List<IUpdatable> map) {
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public void setActive(boolean b) {
		this.isActive = b;
		
	}

	@Override
	public String toString() {
		return super.toString() + String.format("{%s, %d, %d, %d, %d}", getId(), hitbox.getX(), hitbox.getY(),
				hitbox.getWidth(), hitbox.getHeight());
	}

	@Override
	public void setActiveRender(boolean b) {
		this.needRender = b;
	}
	
	@Override
	public boolean needRender() {
		return needRender;
	}
}
