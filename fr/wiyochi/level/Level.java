package fr.wiyochi.level;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import fr.gagoi.engine.graphics.TextureManager;

public class Level {
	private BufferedImage[] img;
	
	public void load(String filename) {
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
		
		char[][] charMap = loader.getMap();
		for (int i = 0; i < loader.getX(); i++) {
			for (int j = 0; j < loader.getY(); j++) {
				String path = "resources/textures/" + properties.getProperty("" + charMap[i][j]);
				switch (charMap[i][j]) {
				case 'A':
					TextureManager.loadTexture(path, "" + charMap[i][j]); // Appelle le textureManager avec "resources/textures/air.png" et "A" comme id
					break;
				case 'B':
					TextureManager.loadTexture(path, "" + charMap[i][j]);
					break;
				case 'C':
					TextureManager.loadTexture(path, "" + charMap[i][j]);
					break;

				default:
					break;
				}
			}
		}
	}
}
