package fr.gagoi.engine;

public abstract class IGameElement {

	public abstract String getId();

	@Override
	public boolean equals(Object obj) {
		return obj instanceof IGameElement && getId().equals(((IGameElement) obj).getId());
	}
}
