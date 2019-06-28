package project;

import ui.GridPanel;
import java.awt.*;
import java.util.List;

import game.Direction;
import naturesimulator.*;


public class Plant extends Creature implements game.Drawable {
	
	public Plant (int x, int y) {
		super (x, y, 0.5);
	}
	
	public Action chooseAction(LocalInformation information) {
		
		List<Direction> freeDirections = information.getFreeDirections();
		
		if ((this.getHealth() >= 0.75) && !freeDirections.isEmpty()) {
			return new Action (naturesimulator.Action.Type.REPRODUCE, LocalInformation.getRandomDirection(freeDirections));
//			if (information.getFreeDirections().size() == 1) {
//				return new Action (naturesimulator.Action.Type.REPRODUCE, information.getFreeDirections().get(0));
//			} else  {
//				return new Action (naturesimulator.Action.Type.REPRODUCE, LocalInformation.getRandomDirection(information.getFreeDirections()));
//			}
		} else {
			return new Action (naturesimulator.Action.Type.STAY);			
		}
	}
	
	@Override
	public void draw(GridPanel panel) {
		if (this.getHealth() >= 0.75) {
			panel.drawSquare(this.getX(), this.getY(), Color.LIGHT_GRAY.darker().darker());
		} else if (this.getHealth() >= 0.5){
			panel.drawSquare(this.getX(), this.getY(), Color.LIGHT_GRAY.darker());
		} else {
			panel.drawSquare(this.getX(), this.getY(), Color.lightGray);
		}
		//panel.drawSquare(this.getX(), this.getY(), Color.BLUE);
	}
	
	public Creature reproduce (Direction direction) {
		
//		Creature childPlant = new Plant (this.getX(), this.getY());
//		childPlant.setHealth(this.getHealth()/10);
//		this.setHealth(this.getHealth()*(7/10));
		
		if (direction == Direction.UP) {
			Plant childPlant = new Plant (this.getX(), this.getY()-1);
			childPlant.setHealth(this.getHealth()/10);
			this.setHealth(this.getHealth()*7/10);
			return childPlant;
		} else if (direction == Direction.DOWN) {
			Plant childPlant = new Plant (this.getX(), this.getY()+1);
			childPlant.setHealth(this.getHealth()/10);
			this.setHealth(this.getHealth()*7/10);
			return childPlant;
		} else if (direction == Direction.LEFT) {
			Plant childPlant = new Plant (this.getX()-1, this.getY());
			childPlant.setHealth(this.getHealth()/10);
			this.setHealth(this.getHealth()*7/10);
			return childPlant;
		} else {
			Plant childPlant = new Plant (this.getX()+1, this.getY());
			childPlant.setHealth(this.getHealth()/10);
			this.setHealth(this.getHealth()*7/10);
			return childPlant;
		}

	}
		
	public void stay () {
		this.setHealth(Math.min(this.getHealth() + 0.05, 1.0));
	}
}

