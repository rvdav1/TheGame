package pieces;
import engine.*;

public class Paladin extends CombatUnit{

	public Paladin(boolean team, GameZone unitZone) {
		super(team, unitZone);
		this.setHealth(150);
		this.setAtk(2);
		this.setDef(2);
		this.setMoral(5);
		this.setUnitName(this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf('.') + 1,
				this.getClass().getName().length()));
	}

}
