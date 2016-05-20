package pieces;
import engine.*;

public class Swordsmen extends CombatUnit{

	public Swordsmen(boolean team, GameZone unitZone) {
		super(team, unitZone);
		this.setHealth(150);
		this.setAtk(4);
		this.setDef(3);
		this.setMoral(2);
		this.setUnitName(this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf('.') + 1,
				this.getClass().getName().length()));
	}

}
