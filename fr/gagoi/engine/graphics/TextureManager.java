package fr.gagoi.engine.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class TextureManager {

	private static HashMap<String, BufferedImage> textures =
			new HashMap<String, BufferedImage>();
	
	public static void loadTexture(String path, String id) {
		if (!textures.containsKey(id)) {
			File f = null;
			try {
				f = new File(System.getenv("resourcesPath")
						+ "/textures/" + path + ".png");
				BufferedImage img = ImageIO.read(f);
				textures.put(id, img);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println(f.getAbsolutePath());
				e.printStackTrace();
			}
		} else {
			System.err.println("Already having the texture : " + id);
		}
	}
	
	public static BufferedImage getTexture(String id) {
		return textures.get(id);
		//return textures.getOrDefault(id, new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB));
	}
}
