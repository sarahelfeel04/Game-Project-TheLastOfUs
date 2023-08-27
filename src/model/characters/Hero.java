package model.characters;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import application.DownSide;
import application.LeftSide;
import application.RightSide;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public abstract class Hero extends Character {

	private int actionsAvailable;
	private int maxActions;
	private boolean specialAction;
	private ArrayList<Vaccine> vaccineInventory;
	private ArrayList<Supply> supplyInventory;
	public static String msg="";

	public Hero(String name, int maxHp, int attackDamage, int maxActions) {
		super(name, maxHp, attackDamage);
		this.maxActions = maxActions;
		this.actionsAvailable = maxActions;
		this.supplyInventory = new ArrayList<Supply>();
		this.vaccineInventory = new ArrayList<Vaccine>();
	}

	public int getActionsAvailable() {
		return actionsAvailable;
	}

	public void setActionsAvailable(int actionsAvailable) {
		if (actionsAvailable <= 0)
			this.actionsAvailable = 0;
		else
			this.actionsAvailable = actionsAvailable;
	}

	public boolean isSpecialAction() {
		return specialAction;
	}

	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}

	public int getMaxActions() {
		return maxActions;
	}

	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}

	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}

	public void move(Direction d) throws MovementException, NotEnoughActionsException {
		if (actionsAvailable < 1)
			throw new NotEnoughActionsException("You need at least 1 action point in order to move.");
		//System.out.println("changed!");
		int tX = getLocation().x;
		int tY = getLocation().y;
		switch (d) {
		case DOWN:
			tX--;
			break;
		case LEFT:
			tY--;
			break;
		case RIGHT:
			tY++;
			
			break;
		case UP:
			tX++;
			break;
		}
		boolean special=false;
		boolean trap=false;
		if (tX < 0 || tY < 0 || tX > Game.map.length - 1 || tY > Game.map.length - 1)
			throw new MovementException("You cannot move outside the borders of the map.");
		if (Game.map[tX][tY] instanceof CharacterCell && ((CharacterCell) Game.map[tX][tY]).getCharacter() != null)
			throw new MovementException("You cannot move to an occuppied cell.");
		else if (Game.map[tX][tY] instanceof CollectibleCell) {{
			((CollectibleCell) Game.map[tX][tY]).getCollectible().pickUp(this);
			DownSide.mediaPlayerMoveVS.seek(javafx.util.Duration.ZERO);
			DownSide.mediaPlayerMoveVS.play();
			special=true;
			}
			if (((CollectibleCell) Game.map[tX][tY]).getCollectible() instanceof Vaccine)
			msg+="You collected a vaccine\n";
			if (((CollectibleCell) Game.map[tX][tY]).getCollectible() instanceof Supply)
				msg+="You collected a supply\n";
			
		} else if (Game.map[tX][tY] instanceof TrapCell) {
			this.setCurrentHp(this.getCurrentHp() - ((TrapCell) Game.map[tX][tY]).getTrapDamage());
			DownSide.mediaPlayerTrap.seek(javafx.util.Duration.ZERO);
			DownSide.mediaPlayerTrap.play();
			msg+="You entered a trap Cell\n";
			trap=true;
		}
		Game.map[getLocation().x][getLocation().y] = new CharacterCell(null);
		this.actionsAvailable--;

		if (this.getCurrentHp() ==  0) {
			return;
		}
		Game.map[tX][tY] = new CharacterCell(this);
		if (special==false && trap==false) {
			
		//DownSide.mediaPlayerMoveC.setVolume(0.5);
		DownSide.mediaPlayerMoveC.seek(javafx.util.Duration.ZERO);
		DownSide.mediaPlayerMoveC.play();
		}
		setLocation(new Point(tX, tY));
		Game.adjustVisibility(this);
	}

	@Override
	public void attack() throws NotEnoughActionsException, InvalidTargetException {
		if (actionsAvailable < 1 && !(this instanceof Fighter && this.specialAction))
			throw new NotEnoughActionsException("You need at least 1 action point to be able to attack.");
		//if (actionsAvailable < 1)
			//throw new NotEnoughActionsException("You need at least 1 action point to be able to attack.");
		if (this.getTarget() == null)
			throw new InvalidTargetException("You should select a target to attack first.");
		if (!checkDistance())
			throw new InvalidTargetException("You are only able to attack adjacent targets.");
		if (this.getTarget() instanceof Hero)
			throw new InvalidTargetException("You can only attack zombies.");
		msg+="You attacked "+this.getTarget().getName()+"\n";
		super.attack();
		if (this instanceof Fighter && (this.isSpecialAction())) {
			return;}
		actionsAvailable--;
		
	}

	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {
		if (this.getSupplyInventory().size() == 0)
			throw new NoAvailableResourcesException(
					"You need to have at least 1 supply in your inventory to use your special abililty.");
		this.supplyInventory.get(0).use(this);
		this.setSpecialAction(true);
		msg+="You are using the special action\n";
		
	}

	public boolean checkDistance() {
		Point p1 = getLocation();
		Point p2 = getTarget().getLocation();
		if (Math.abs(p1.x - p2.x) > 1)
			return false;
		else if (Math.abs(p1.y - p2.y) > 1)
			return false;
		return true;
	}

	public void cure() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException {
		if (this.vaccineInventory.size() == 0)
			throw new NoAvailableResourcesException(
					"You need to have at least 1 vaccine in your inventory to be able to cure zombies.");
		if (this.actionsAvailable < 1)
			throw new NotEnoughActionsException("You need to have at least 1 action point in order to cure a zombie.");
		if (this.getTarget() == null)
			throw new InvalidTargetException("You need to pick a target to cure first.");
		if (!checkDistance())
			throw new InvalidTargetException("You are only able to cure adjacent targets.");
		if (!(this.getTarget() instanceof Zombie))
			throw new InvalidTargetException("You can only cure zombies.");
		this.vaccineInventory.get(0).use(this);
		actionsAvailable--;
		msg+="You cured "+ this.getTarget().getName()+"\n";
	}

	

}
