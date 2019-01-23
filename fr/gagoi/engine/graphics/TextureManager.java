package fr.gagoi.engine.graphics;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TextureManager {

	private static HashMap<String, BufferedImage> textures = new HashMap<String, BufferedImage>();
	
	public static void loadTexture(String path, String id) {
		if (!textures.containsKey(id)) {
			try {
				BufferedImage img = ImageIO.read(TextureManager.class.getResource(path));
				textures.put(id, img);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Already having the texture : " + id);
		}
	}
	
	public static BufferedImage getTexture(String id) {
		return textures.getOrDefault(id, new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB));
	}
}
