package pieces;
import engine.*;

public class Peasant extends CombatUnit {

	public Peasant(boolean team, GameZone unitZone) {
		super(team, unitZone);
		this.setHealth(100);
		this.setAtk(4);
		this.setDef(2);
		this.setMoral(2);
		this.setUnitName(this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf('.') + 1,
				this.getClass().getName().length()));
	}
}
