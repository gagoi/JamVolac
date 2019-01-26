package fr.gagoi.engine.entities;

import java.util.List;

public interface IUpdatable {

	public void update(List<IUpdatable> list);
	
	/*
	 * Renvoie True si l'objet est affecté par la gravité. False sinon.
	 */
	default public boolean hasWeight() {return true;};
	
	/*
	 * Renvoie la maniere dont l'objet est affecté par la gravité. (2 -> 2 fois plus lourd).
	 */
	default public float getWeight() {return 1;};
	
	/*
	 * Renvoie si oui ou non l'objet peut entrer en collision avec les utres objets du jeu.
	 */
	default public boolean hasInGameHitbox() {return true;};
	
	/*
	 * Renvoie si oui ou non l'objet est cliquable.
	 */
	default public boolean canBeClicked() {return false;};
	
	default public void translate(int i, int j) { };

}
