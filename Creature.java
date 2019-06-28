package project;

import game.Direction;
import naturesimulator.*;


public abstract class Creature implements game.Drawable {
	
	private double health;
	private int x;
	private int y;
	
	public Creature (int x, int y, double health) {
		this.x = x;
		this.y = y;
		this.health = health;
	}
	
	public double getHealth() {
		return this.health;
	}
	
	public void setHealth(double health) {
		if (this instanceof Plant) {
			this.health = Math.min(1.0, health);
		} else if (this instanceof Herbivore) {
			this.health = Math.min(20.0, health);
		}
	}
	
	public void setX (int x) {
		this.x = x;
	}
	
	public void setY (int y) {
		this.y = y;
	}
	
	public int getX () {
		return this.x;
	}
	
	public int getY () {
		return this.y;
	}
	
	public abstract Action chooseAction (LocalInformation information) ;
	
	public void move (Direction direction) {
		
	}
	
	public abstract Creature reproduce (Direction direction) ;
	
	public void attack (Creature creature) {
		
	}
	
	public abstract void stay () ;
}
