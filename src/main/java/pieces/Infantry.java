package pieces;
import engine.*;

public class Infantry extends CombatUnit{

	public Infantry(boolean team, GameZone unitZone) {
		super(team, unitZone);
		this.setHealth(120);
		this.setAtk(3);
		this.setDef(3);
		this.setMoral(3);
		this.setUnitName(this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf('.') + 1,
				this.getClass().getName().length()));
	}

}
