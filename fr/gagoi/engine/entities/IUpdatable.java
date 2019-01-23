package fr.gagoi.engine.entities;

import java.util.HashMap;

public interface IUpdatable {

	public void update(HashMap<String, IUpdatable> map);
	
	public String getId();

}
