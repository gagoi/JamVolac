package fr.gagoi.engine.entities;

import java.util.List;

import fr.gagoi.engine.IGameElement;
import fr.gagoi.engine.inputs.Keyboard;

public class Player extends Entity {

	private int vx = 4, vy;
	private float dx, dy, vj;
	private boolean isJumping;
	private long lastUpdate, lastJumpUpdate;
	// private static double gravity = -0.0028;
	private static final double gravity = -8.8e4;

	public Player() {
		super("player", new Hitbox(100, 300, 32, 64));
		getSound().AddAudio("jump");
		getSound().AddAudio("jumpland");
	}

	@Override
	public void update(List<IUpdatable> map) {
		if (lastUpdate == 0)
			lastUpdate = System.currentTimeMillis();
		if (lastJumpUpdate == 0)
			lastJumpUpdate = System.currentTimeMillis();
		dx = 0;
		dy = 0;
		double delta = (System.currentTimeMillis() / 1000.0 - lastUpdate / 1000.0);
		double deltaJmp = (System.currentTimeMillis() / 1000.0 - lastUpdate / 1000.0);
		
		deltaJmp /= 1.5;
		
		if (Keyboard.isKeyPressed[Keyboard.RIGHT])
			dx = vx;
		hitbox.translate(vx, 0);
		if (Keyboard.isKeyPressed[Keyboard.LEFT])
			dx = -vx;
		hitbox.translate(-vx, 0);
		if (Keyboard.isKeyPressed[Keyboard.UP] && !isJumping) {
			System.out.println("JUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUMPPPPPPPPPPPPPPPPPPPP");
			vj = 2500;
			isJumping = true;
			//getSound().Play("jump");
		}

		dy = (float) -((0.5 * gravity * delta * delta + vy * delta + vj * deltaJmp));

		if(isJumping)
				vj-=40;
		
		for (IUpdatable obj : map) {
			if ((obj.hasInGameHitbox()) && (hitbox.collide(obj.getHitbox()))) {
				if (obj != this) {
					if (obj instanceof Pickup) {
						map.remove(obj);
					} else {
						PlatformColliding(obj);
					}
				}
			}
		}

		hitbox.translate((int) dx, (int) dy);
		lastUpdate = System.currentTimeMillis();
	}

	protected void PlatformColliding(IUpdatable obj) {
		int loc = hitbox.whereCollision(obj.getHitbox(), dx, dy);
		//System.out.println("Collision avec : " + ((IGameElement) obj).getId() + " par le " + loc);
		switch (loc) {
		case (1):
			dx = -dx;
			break;
		case (2):
			if (dy > 0)
			{
				dy = 0;
				if(isJumping)
					isJumping = false;
			}
			// dy = -(hitbox.getY() + obj.getHitbox().getY() - hitbox.getHeight());
			/*
			 * if (isJumping && dy > 0) { dy = -hitbox.getY() + obj.getHitbox().getY() -
			 * hitbox.getHeight(); isJumping = false; }
			 */
			break;
		case (3):
			dx = -dx;
			break;
		case (4):
			dy = dy;
			vy = 0;
			break;
		default:
			break;
		}
		//System.out.println("Localisation = " + loc);
	}
}
