package fr.gagoi.engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.gagoi.engine.IGameElement;

public class BackGround extends IGameElement implements IRenderable {

	private BufferedImage im;
	private String id = "bg";
	
	public BackGround(String name) {
		File file = null;
		
		file = new File(System.getenv("resourcesPath") +
				"/textures/" + name + ".png");
		try {
			im = ImageIO.read(file);
		} catch (IOException e) {
			System.out.print("Probl�me d'ouverture");
			e.printStackTrace();
		}
	}
	
	public String getId() {
		return(this.id);
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(im, 0, 0, 1300, 900, null);
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setActiveRender(boolean b) {
		
	}

	@Override
	public boolean needRender() {
		// TODO Auto-generated method stub
		return true;
	}

}
