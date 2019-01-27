package fr.wiyochi.level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.entities.Entity;
import fr.gagoi.engine.entities.Hitbox;
import fr.gagoi.engine.entities.Pickup;
import fr.gagoi.engine.graphics.TextureManager;

public class Level extends Entity {

	BufferedImage backgroundImg, lvlImg;
	ArrayList<Block> blocks;
	ArrayList<Pickup> pickups;

	public Level(int lvlId, String fileName) {
		super("level_" + lvlId);
		this.load(fileName);
	}

	private void load(String filename) {

		Loader loader = new Loader();
		loader.loadLevel(filename);

		HashMap<String, Integer[]> mapPickups = loader.getPickups();
		System.out.println(mapPickups.get("note1")[0] + "-" + mapPickups.get("note1")[1]);

		pickups = new ArrayList<Pickup>();
		mapPickups.forEach((k, v) -> {
			Pickup p1 = new Pickup(k, 4, 4, new Hitbox(v[0], v[1], 32, 32));
			p1.LoadAnimation(k);
			pickups.add(p1);
			System.out.println("pickup :" + k + " (" + v[0] + "," + v[1] + ")");
		});

		char[][] charMap = loader.getMap();
		
		for (int y = 0; y < charMap.length; y++) {
			for (int x = 0; x < charMap[y].length; x++) {
				String id = new String() + charMap[y][x];
				Block b = new Block(id, 2, 4, new Hitbox(x*32, y*32, 32, 32));
				if (!isHard(charMap[y][x]))
					b.setActive(false);
				if (isAnimated(charMap[y][x]))
					b.LoadAnimation(charMap[y][x]);
				Game.addElement(b);
			}
		}

		this.img = new BufferedImage[4];
		// hitbox = new Hitbox();

		for (int i = 0; i < 4; i++) {
			this.img[i] = new BufferedImage(loader.getX() * 32, loader.getY() * 32, BufferedImage.TYPE_INT_ARGB);
			/*
			 * for (int y = 0; y < charMap.length; y++) { for (int x = 0; x <
			 * charMap[y].length; x++) { if (isHard(charMap[y][x])) { hitbox.p.addPoint(x *
			 * 32, y * 32); hitbox.p.addPoint((x + 1) * 32, y * 32); hitbox.p.addPoint((x +
			 * 1) * 32, (y + 1) * 32); hitbox.p.addPoint(x * 32, (y + 1) * 32); }
			 * 
			 * 
			 * if (isAnimated(charMap[y][x])) { String id = new String() + charMap[y][x]; id
			 * = id + (i+1); img[i].getGraphics().drawImage( TextureManager.getTexture(id),
			 * x * 32, y * 32, 32, 32, null); } else { img[i].getGraphics().drawImage(
			 * TextureManager.getTexture(charMap[y][x] + ""), x * 32, y * 32, 32, 32, null);
			 */
		}
	}

	@Override
	public void render(Graphics g) {
		lvlImg = img[(int) ((System.nanoTime() / 200000000) % 4)];
		g.drawImage(lvlImg, 0, 0, null);
		for (Pickup pickup : pickups) {
			pickup.render(g);
		}
	}

	private boolean isHard(char c) {
		String codes = "BCD";
		return codes.contains("" + c);
	}

	private boolean isAnimated(char c) {
		String codes = "BCD";
		return codes.contains("" + c);
	}

	public ArrayList<Pickup> getPickups() {
		return (pickups);
	}
}
