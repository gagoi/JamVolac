package fr.gagoi.engine.entities;

import java.util.Iterator;
import java.util.List;

import fr.gagoi.engine.inputs.Keyboard;

public class Player extends Entity {

	private int vx = 4, vy;
	private float vj;
	private boolean isJumping;
	private long lastJumpUpdate;
	private static double gravity = -0.0028;

	public Player() {
		super("player", new Hitbox(100, 500, 32, 64));
		getSound().AddAudio("jump");
		getSound().AddAudio("jumpland");
	}

	@Override
	public void update(List<IUpdatable> map) {
		if (Keyboard.isKeyPressed[Keyboard.RIGHT])
			hitbox.translate(vx, 0);
		if (Keyboard.isKeyPressed[Keyboard.LEFT])
			hitbox.translate(-vx, 0);
		if (Keyboard.isKeyPressed[Keyboard.UP] && !isJumping) {
			vj = .25f;
			isJumping = true;
			lastJumpUpdate = System.currentTimeMillis();
			getSound().Play("jump");
		}
		
		for (IUpdatable obj : map) {
			if(obj.hasInGameHitbox()) {
				// TODO: Collisions?
			}
		}

		if (vj != 0 && isJumping) {
			long delta = System.currentTimeMillis() - lastJumpUpdate;
			delta /= 1.5;

			vy += vy > vj * 2 ? gravity * delta + vj : vy;
			if (hitbox.getY() + hitbox.getHeight() >= 650 + 10) {
				vj = 0;
				hitbox.translate(0, -40);
				isJumping = false;
				getSound().Play("jumpland");
			} else {
				hitbox.translate(0, (int) -((0.5 * gravity * delta * delta + vy * delta + vj * delta)));
			}
		}

	}
}
