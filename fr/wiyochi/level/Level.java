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
	public ArrayList<Block> blocks = new ArrayList<Block>();
	ArrayList<Pickup> pickups;

	public Level(int lvlId, String fileName) {
		super("level_" + lvlId);
		this.load(fileName);
		hitbox = new Hitbox(-20000000, -200000000, 1, 1);
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
				Block b = new Block(id, 2, 4, new Hitbox(x * 32, y * 32, 32, 32));
				b.setActiveRender(false);
				blocks.add(b);
				if (isAnimated(charMap[y][x]))
					b.LoadAnimation(charMap[y][x]);
				Game.addElement(b);
			}
		}
		this.img = new BufferedImage[4];

		for (int i = 0; i < 4; i++) {
			this.img[i] = new BufferedImage(loader.getX() * 32, loader.getY() * 32, BufferedImage.TYPE_INT_ARGB);
		}
	}

	@Override
	public void render(Graphics g) {
		lvlImg = img[(int) ((System.nanoTime() / 200000000) % 4)];


		for (Pickup pickup : pickups) {
			pickup.render(g);
		}for (Block pp : blocks) {
			pp.render(g);
		}	
		g.drawImage(lvlImg, 0, 0, null);
	}

	private boolean isHard(char c) {
		String codes = "";
		return codes.contains("" + c);
	}

	private boolean isAnimated(char c) {
		String codes = "";
		return codes.contains("" + c);
	}

	public ArrayList<Pickup> getPickups() {
		return (pickups);
	}

	@Override
	public void setActive(boolean b) {
		super.setActive(b);
		for (Block block : blocks) {
			//if (!isHard(block.getId().charAt(0)))
				block.setActive(b);
		}
		for (Pickup pickup : pickups) {
			pickup.setActive(b);
		}
	}

	@Override
	public void setActiveRender(boolean b) {
		super.setActive(b);
		for (Block block : blocks) {
			block.setActiveRender(b);
		}
		for (Pickup pickup : pickups) {
			pickup.setActiveRender(b);
		}
	}
}
