package fr.gagoi.engine.entities;

import java.util.List;

import fr.gagoi.engine.Game;
import fr.gagoi.engine.graphics.GUI.MainMenu;
import fr.gagoi.engine.inputs.Keyboard;
import fr.wiyochi.level.Level;

public class Player extends Entity {

	private int vx = 4;
	private float vy = 4;

	private float dx, dy;

	public Player() {
		super("player", new Hitbox(300, 390, 32, 64));
		getSound().AddAudio("jump");
		getSound().AddAudio("jumpland");

	}

	@Override
	public void update(List<IUpdatable> map) {
		if (isActive) {
			dx = 0;
			dy = 0;

			if (Keyboard.isKeyPressed[Keyboard.RIGHT] && !esgibteinplateformADroite())
				dx += vx;
			if (Keyboard.isKeyPressed[Keyboard.LEFT] && !esgibteinplateformAGauche())
				dx -= vx;
			if (Keyboard.isKeyPressed[Keyboard.UP] && !esgibteinplateformAudessus() && esgibteinplateformEndessous()) {
				vy = -10;
			}
			if (Math.abs(vy) <= 0.05 && !esgibteinplateformEndessous())
				vy = 2;
			if (vy < 0 && !esgibteinplateformAudessus()) {
				dy += vy;
				vy *= 0.9;
			}
			if (vy > 0 && !esgibteinplateformEndessous()) {
				dy += vy;
				vy = (float) Math.min(1.1 * vy, 3);
			}

			if (Keyboard.isKeyPressed[Keyboard.DOWN] && !esgibteinplateformADroite()) {
				dy += 2;
			}

			hitbox.translate(dx, dy);

			map.forEach((e) -> {
				System.out.println(e.getHitbox());
				//if (e != null && e != this && e instanceof Entity && ((Entity) e).isActive)
					e.getHitbox().translate(dx, -dy);
			});
		}
	}

	private boolean esgibteinplateformAudessus() {
		boolean ilyenaune = false;

		for (IUpdatable el : Game.updatables) {
			if (el.hasInGameHitbox() && el != this)
				ilyenaune = ilyenaune || (el.getHitbox().contain(hitbox.x, hitbox.y - 4)
						|| el.getHitbox().contain(hitbox.x + hitbox.w, hitbox.y - 4));
			if (ilyenaune)
				break;
		}

		return ilyenaune;
	}

	private boolean esgibteinplateformEndessous() {
		boolean ilyenaune = false;

		for (IUpdatable el : Game.updatables) {
			if (el.hasInGameHitbox() && el != this) {
				ilyenaune = ilyenaune || (el.getHitbox().contain(hitbox.x, hitbox.y + hitbox.h + 4)
						|| el.getHitbox().contain(hitbox.x + hitbox.w, hitbox.y + hitbox.h + 4));
			}
			if (ilyenaune)
				break;
		}

		return ilyenaune;
	}

	private boolean esgibteinplateformAGauche() {
		boolean ilyenaune = false;

		for (IUpdatable el : Game.updatables) {
			if (el.hasInGameHitbox() && el != this)
				ilyenaune = ilyenaune || (el.getHitbox().contain(hitbox.x - 4, hitbox.y)
						|| el.getHitbox().contain(hitbox.x - 4, hitbox.y + hitbox.h));
			if (ilyenaune)
				break;
		}

		return ilyenaune;
	}

	@Override
	public int getLayer() {
		return 2;
	}

	private boolean esgibteinplateformADroite() {
		boolean ilyenaune = false;

		for (IUpdatable el : Game.updatables) {
			if (el.hasInGameHitbox() && el != this)
				ilyenaune = ilyenaune || (el.getHitbox().contain(hitbox.x + hitbox.w + 4, hitbox.y)
						|| el.getHitbox().contain(hitbox.x + hitbox.w + 4, hitbox.y + hitbox.h));
			if (ilyenaune)
				break;
		}

		return ilyenaune;
	}
}
