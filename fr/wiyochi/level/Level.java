package fr.wiyochi.level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import fr.gagoi.engine.entities.Entity;
import fr.gagoi.engine.entities.Hitbox;
import fr.gagoi.engine.graphics.TextureManager;

public class Level extends Entity {
	
	BufferedImage backgroundImg, lvlImg;
	int dX = 0;
	int dY = 0;
	int dXLimit;
	int dYLimit;
	
	public Level(int lvlId, String fileName) {
		super("level_" + lvlId);
		this.load(fileName);
	}
	
	private void load(String filename) {
		Properties properties = new Properties();
		FileInputStream input = null;

		try {
			input = new FileInputStream(filename);
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Loader loader = new Loader();
		loader.loadLevel(filename);

		dXLimit = loader.getX();
		dYLimit = loader.getY();
		
		char[][] charMap = loader.getMap();

		this.lvlImg = new BufferedImage(loader.getX() * 32, loader.getY() * 32, BufferedImage.TYPE_INT_ARGB);
		
		hitbox = new Hitbox();
		
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
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(lvlImg, dX, dY, null);
	}
	
	@Override
	public void translate(int i, int j) {
		dX += i;
		dY += j;
		hitbox.translate(i, j);
	}
	
	private boolean isHard(char c) {
		String codes = "A";
		return codes.contains("" + c);
	}
}
