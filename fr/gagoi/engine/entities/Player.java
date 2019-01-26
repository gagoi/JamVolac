package fr.gagoi.engine.entities;

import java.util.List;

import fr.gagoi.engine.inputs.Keyboard;

public class Player extends Entity {

	private int vx = 4, vy;
	private float dx, dy, vj;
	private boolean isJumping;
	private long lastUpdate, lastJumpUpdate;
	private static final double gravity = -8.8e4;

	public Player() {
		super("player", new Hitbox(600, 400, 32, 64));
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
			vj = 2500;
			isJumping = true;
			// getSound().Play("jump");
		}

		// dy = (float) -((0.5 * gravity * delta * delta + vy * delta + vj * deltaJmp));

		if (isJumping)
			vj -= 40;

		Integer[] nd = new Integer[] { (int) dx, (int) dy };

		hitbox.translate(nd[0], nd[1]);

		for (IUpdatable obj : map) {
			if ((obj.hasInGameHitbox()) && (hitbox.collide(obj.getHitbox()))) {
				if (obj != this) {
					if (obj instanceof Pickup) {
						map.remove(obj);
					} else {
						nd = PlatformColliding(obj, (int) (hitbox.getX() - dx), (int) (hitbox.getY() - dy));
					}
				}
			}
		}

		hitbox.translate(nd[0], nd[1]);
		lastUpdate = System.currentTimeMillis();
	}
	/*
	 * protected void PlatformColliding(IUpdatable obj) { int colX =
	 * hitbox.whereCollisionX(obj.getHitbox(), dx, dy); int colY =
	 * hitbox.whereCollisionX(obj.getHitbox(), dx, dy);
	 * 
	 * if (colY == 2) { if (dy > 0) { dy = getHitbox().getY() +
	 * getHitbox().getHeight() - obj.getHitbox().getY(); dy = -dy; if (isJumping) {
	 * isJumping = false; vj = 0; } } } else if (colY == 4) {
	 * 
	 * }
	 * 
	 * if (colX == 2) { dx = 0; } else if (colX == 4) { dx = 0; } }
	 */

	private Integer[] PlatformColliding(IUpdatable G, Integer xb, Integer yb) {
		int x = getHitbox().getX();
		int y = getHitbox().getY();
		int w = getHitbox().getWidth() + 1;
		int h = getHitbox().getHeight() + 1;

		int Gx = G.getHitbox().getX();
		int Gy = G.getHitbox().getY();
		int Gw = G.getHitbox().getWidth() + 1;
		int Gh = G.getHitbox().getHeight() + 1;

		Integer ndx = 0, ndy = 0;

		boolean isHori = Math.abs(dx) > Math.abs(dy);

		int dp = 0;
		int code = 0;

		System.out.println("xb:" + xb + " | w:" + w + " | Gx:" + Gx + " | xb+w:" + (xb + w));
		System.out.println("x:" + x + " | w:" + w + " | Gx:" + Gx + " | x+w:" + (x + w));
		if (xb + w < Gx) {
			code += 100;
			System.out.println("ndx:" + ndx);
			if (yb + h < Gy) {
				code += 10;
				if (isHori) {
					code += 1;
					ndy = -y - h + Gy;
				} else {
					code += 2;
					ndx = -x - w + Gx - dp;
				}
			} else if (yb > Gy + Gh) {
				code += 20;
				if (isHori) {
					code += 1;
					ndy = Gy + Gh - y;
				} else {
					code += 2;
					ndx = -x + Gw + Gx - dp;
				}
			} else {
				code += 30;
				ndx = -x - w + Gx - dp;
				System.out.println("ndx:" + ndx);
			}
		} else if (xb >= Gx + Gw) {
			code += 200;
			if (yb + h < Gy) {
				code += 10;
				if (isHori) {
					code += 1;
					ndy = -y - h + Gy;
				} else {
					code += 2;
					ndx = Gx + Gw - x - dp;
				}
			} else if (yb > Gy + Gh) {
				code += 20;
				if (isHori) {
					ndy = Gy + Gh - y;
					code += 1;
				} else {
					code += 2;
					ndx = Gx + Gw - x - dp;
				}
			} else {
				code += 30;
				System.out.println("n2dx:" + ndx);
				ndx = Gx + Gw - x - dp;
			}
		} else {
			code += 300;
			if (yb + h < Gy) {
				code += 10;
				ndy = -y - h + Gy;
			} else if (yb > Gy + Gh) {
				code += 20;
				ndy = Gy + Gh - y;
			}
		}
		// System.out.println("REST " + ndx + "-" + dx);
		// System.out.println("Dist : " + (Gx - x - w) );
		// System.exit(0);

		System.out.println("Code : " + code);
		return new Integer[] { ndx, ndy };
	}
}
