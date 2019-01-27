package fr.gagoi.engine.entities;

public class Hitbox {
	
	float x,y,w,h;

	public Hitbox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
	}

	public boolean contain(float xp, float yp) {
		return this.x <= xp && this.x + this.w >= xp && this.y <= yp && this.y + this.h >= yp;
	}
	
	public boolean collide(Hitbox hitbox) {
		return (this.contain(hitbox.x, hitbox.y) || this.contain(hitbox.x + hitbox.w, hitbox.y)
				|| this.contain(hitbox.x + w, hitbox.y + h) || this.contain(hitbox.x, hitbox.y + h));
	}
	
	public float getX() {
		return x;
	}
	
	public void setY(float f) 
	{
		this.y = f;
	}
	public float getY() {
		return y;
	}

	public float getWidth() {
		return w;
	}

	public float getHeight() {
		return h;
	}

	public void translate(float x, float y) {
		this.x += x;
		this.y += y;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("{%f, %f, %f, %f}", getX(), getY(), getWidth(), getHeight());
	}
}
