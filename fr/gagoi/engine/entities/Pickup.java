package fr.gagoi.engine.entities;

import java.util.List;

public class Pickup extends Entity {

	public Pickup(String id, Hitbox hitbox) {
		super(id, hitbox);
	}
	
	public Pickup(String id, double vitesse, int nbSprites, Hitbox hitbox) {
		super(id, vitesse, nbSprites, hitbox);
	}

	@Override
	public void update(List<IUpdatable> map) {
		// TODO: Collisions avec Player pour le rammassage du pickup
	}
}
