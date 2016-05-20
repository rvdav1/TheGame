package pieces;
import engine.*;

public class Cavalry extends CombatUnit{
	
	public Cavalry(boolean team, GameZone unitZone) {
		super(team, unitZone);
		this.setHealth(100);
		this.setAtk(5);
		this.setDef(1);
		this.setMoral(3);
		this.setUnitName(this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf('.') + 1,
				this.getClass().getName().length()));
	}
}
