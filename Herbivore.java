package project;

import java.awt.*;
import java.util.List;

import java.util.*;

import game.Direction;
import ui.GridPanel;
import naturesimulator.*;



public class Herbivore extends Creature implements game.Drawable {
	
	private static final double MAX_HEALTH = 20.0;
	
	public Herbivore (int x, int y) {
		super (x, y, MAX_HEALTH/2.0);
	}
	
	
	// herbivore yoktan var oldu bi anda
	public Action chooseAction (LocalInformation information) {
		
		ArrayList<Direction> directionsWithPlant = new ArrayList<Direction> ();
		if (information.getCreatureUp() instanceof Plant) {
			directionsWithPlant.add(Direction.UP);
		}
		if (information.getCreatureDown() instanceof Plant) {
			directionsWithPlant.add(Direction.DOWN);
		}
		if (information.getCreatureLeft() instanceof Plant) {
			directionsWithPlant.add(Direction.LEFT);
		}
		if (information.getCreatureRight() instanceof Plant) {
			directionsWithPlant.add(Direction.RIGHT);
		}
		
		List<Direction> freeDirections = information.getFreeDirections();
		
		if (this.getHealth() == MAX_HEALTH && freeDirections.size() > 0) {
			return new Action (Action.Type.REPRODUCE, LocalInformation.getRandomDirection(freeDirections));
		} else if (directionsWithPlant.size() > 0) {
			return new Action (Action.Type.ATTACK, LocalInformation.getRandomDirection(directionsWithPlant));
		} else if (freeDirections.size() > 0) {
			return new Action (Action.Type.MOVE, LocalInformation.getRandomDirection(freeDirections));
		} else {
			return new Action (Action.Type.STAY);
		}
		
	}
	
	@Override
	public void draw(GridPanel panel) {
		if (this.getHealth() == MAX_HEALTH) {
			panel.drawSquare(this.getX(), this.getY(), Color.PINK.darker().darker());
		} else if (this.getHealth() >= 10.0) {
			panel.drawSquare(this.getX(), this.getY(), Color.PINK.darker());
		} else {
			panel.drawSquare(this.getX(), this.getY(), Color.PINK);
		}
	}
	 
	public Creature reproduce (Direction direction) {
		//Creature childHerbivore = new Herbivore (this.getX(), this.getY());
		//childHerbivore.setHealth(this.getHealth()/5.0);
		//this.setHealth(this.getHealth()*4.0/10.0);
		
		if (direction == Direction.UP) {
			//childHerbivore.setX(this.getX()-1);
			Herbivore childHerbivore = new Herbivore (this.getX(), this.getY()-1);
			childHerbivore.setHealth(this.getHealth()/5);
			this.setHealth(this.getHealth()*4/10);
			return childHerbivore;
		} else if (direction == Direction.DOWN) {
			//childHerbivore.setX(this.getX()+1);
			Herbivore childHerbivore = new Herbivore (this.getX(), this.getY()+1);
			childHerbivore.setHealth(this.getHealth()/5);
			this.setHealth(this.getHealth()*4/10);
			return childHerbivore;
		} else if (direction == Direction.LEFT) {
			//childHerbivore.setY(this.getY()-1);
			Herbivore childHerbivore = new Herbivore (this.getX()-1, this.getY());
			childHerbivore.setHealth(this.getHealth()/5);
			this.setHealth(this.getHealth()*4/10);
			return childHerbivore;
		} else {
			//childHerbivore.setY(this.getY()+1);
			Herbivore childHerbivore = new Herbivore (this.getX()+1, this.getY());
			childHerbivore.setHealth(this.getHealth()/5);
			this.setHealth(this.getHealth()*4/10);
			return childHerbivore;
		}
		
	}
	
	public void move (Direction direction) {
		
		if (this.getHealth()-1.0 > 0) {
			if (direction == Direction.UP) {
				this.setX(this.getX()-1);;
			} else if (direction == Direction.DOWN) {
				this.setX(this.getX()+1);;
			} else if (direction == Direction.LEFT) {
				this.setY(this.getY()-1);;
			} else {
				this.setY(this.getY()+1);;
			}	
			this.setHealth(this.getHealth()-1.0);
		
		} else {
			this.stay();
		}		
	}
	
	public void stay () {
		this.setHealth(this.getHealth() - 0.1);		
	}
	
	public void attack (Creature creature) {
		
		if (creature instanceof Plant) {
			this.setHealth(Math.min(MAX_HEALTH, (this.getHealth() + creature.getHealth())));
			creature.setHealth(0.0);
			
			setX(creature.getX());
			setY(creature.getY());
		}
	}
	
}
