import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.entities.Entity;
import fr.gagoi.engine.entities.Hitbox;
import fr.gagoi.engine.entities.Player;
import fr.gagoi.engine.graphics.TextureManager;

public class Test {

	public static void main(String[] args) {
		Game.init("TEST", new Dimension(1280, 720), 2);

		loadTextures();
		//TextureManager.loadTexture("player1", "player");

		Player e = new Player();

		Game.start();
		
		//Game.addElement(l);
		Game.addElement(new Entity("platform1", new Hitbox(250, 100, 100, 200)) {
			@Override
			public void render(Graphics g) {
				super.render(g);
				g.setColor(Color.BLUE);
				g.fillRect(250, 100, 100, 200);
			}
		});
		Game.addElement(new Entity("platform2", new Hitbox(250, 500, 100, 200)){
			@Override
			public void render(Graphics g) {
				super.render(g);
				g.setColor(Color.RED);
				g.fillRect(250, 500, 100, 200);
			}
		});
		Game.addElement(e);
			
	}

	private static void loadTextures() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(System.getenv("resourcesPath") + "/levels/char-texture.txt"));
			prop.forEach((k, v) -> {
				System.out.println(k + "-----" + v);
				TextureManager.loadTexture("" + v, "" + k);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
