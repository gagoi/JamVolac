package fr.wiyochi.level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import fr.gagoi.engine.entities.Entity;
import fr.gagoi.engine.entities.Hitbox;
import fr.gagoi.engine.entities.Pickup;
import fr.gagoi.engine.graphics.TextureManager;

public class Level extends Entity {
	
	BufferedImage backgroundImg, lvlImg;
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
			pickups.add(new Pickup(k, new Hitbox(v[0], v[1], 32, 32)));
			System.out.println("pickup :" + k + " (" + v[0] + "," + v[1] + ")");
		});
		
		char[][] charMap = loader.getMap();

		this.lvlImg = new BufferedImage(loader.getX() * 32, loader.getY() * 32, BufferedImage.TYPE_INT_ARGB);
		
		//hitbox = new Hitbox();
		/*
		for (int y = 0; y < charMap.length; y++) {
			for (int x = 0; x < charMap[y].length; x++) {
				if (isHard(charMap[y][x])) {
					
					hitbox.p.addPoint(x * 32, y * 32);
					hitbox.p.addPoint((x + 1) * 32, y * 32);
					hitbox.p.addPoint((x + 1) * 32, (y + 1) * 32);
					hitbox.p.addPoint(x * 32, (y + 1) * 32);
				}
				
				lvlImg.getGraphics().drawImage(TextureManager.getTexture(charMap[y][x] + ""), x * 32, y * 32, 32, 32, null);
			}
		}
		*/
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(lvlImg, 0, 0, null);
		for (Pickup pickup : pickups) {
			pickup.render(g);
		}
	}
	
	private boolean isHard(char c) {
		String codes = "BCD";
		return codes.contains("" + c);
	}
}
