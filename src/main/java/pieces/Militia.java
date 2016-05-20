package pieces;
import engine.*;

public class Militia extends CombatUnit{

	public Militia(boolean team, GameZone unitZone) {
		super(team, unitZone);
		this.setHealth(130);
		this.setAtk(2);
		this.setDef(3);
		this.setMoral(4);
		this.setUnitName(this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf('.') + 1,
				this.getClass().getName().length()));
	}

}
