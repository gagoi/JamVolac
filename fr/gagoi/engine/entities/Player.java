package fr.gagoi.engine.entities;

import java.util.Iterator;
import java.util.List;

import fr.gagoi.engine.inputs.Keyboard;

public class Player extends Entity {

	private int vx = 4, vy;
	private float dx, dy, vj;
	private boolean isJumping;
	private long lastJumpUpdate;
	private static double gravity = -0.0028;

	public Player() {
		super("player", new Hitbox(100, 300, 32, 64));
		getSound().AddAudio("jump");
		getSound().AddAudio("jumpland");
	}

	@Override
	public void update(List<IUpdatable> map) {
		dx = 0;
		dy = 0;
		if (Keyboard.isKeyPressed[Keyboard.RIGHT])
			dx = vx;
			hitbox.translate(vx, 0);
		if (Keyboard.isKeyPressed[Keyboard.LEFT])
			dx = -vx;
			hitbox.translate(-vx, 0);
		if (Keyboard.isKeyPressed[Keyboard.UP] && !isJumping) {
			vj = .25f;
			isJumping = true;
			lastJumpUpdate = System.currentTimeMillis();
			getSound().Play("jump");
		}
		
		if (vj != 0 && isJumping) {
			long delta = System.currentTimeMillis() - lastJumpUpdate;
			delta /= 1.5;

			vy += vy > vj * 2 ? gravity * delta + vj : vy;
			if (hitbox.getY() + hitbox.getHeight() >= 650 + 10) {
				vj = 0;
				dy = -40;
				isJumping = false;
				getSound().Play("jumpland");
			} else {
				dy = (int) -((0.5 * gravity * delta * delta + vy * delta + vj * delta));
			}
		}
		for (IUpdatable obj : map) {
			if((obj.hasInGameHitbox()) && (hitbox.collide(obj.getHitbox()))) {
				
				if(obj!=this) {
					System.out.println("Collision avec " + obj.toString());
					if(obj instanceof Pickup) {
						System.out.println("Pickup récuperé");
						map.remove(obj);
					} else {
						PlatformColliding(obj);
					}
				}
			}
		}
		System.out.println("");
		System.out.println("dx = "+dx+" ,dy = "+dy);
		hitbox.translate((int)dx,(int)dy);

	}
	
	protected void PlatformColliding(IUpdatable obj) {
		int loc = hitbox.whereCollision(obj.getHitbox(), dx, dy);
		
		switch(loc){
		case(1) : dx=-dx;
			break;
		case(2): if(isJumping && dy>0) {
				dy=0;
				isJumping=false;
			}
			break;
		case(3): dx=-dx;
			break;
		case(4): dy=-dy;
			break;
		default : break;
		}
		System.out.println("Localisation = "+loc);
	}
}
