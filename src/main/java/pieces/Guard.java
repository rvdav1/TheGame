package pieces;
import engine.*;

public class Guard extends CombatUnit{

	public Guard(boolean team, GameZone unitZone) {
		super(team, unitZone);
		this.setHealth(120);
		this.setAtk(1);
		this.setDef(5);
		this.setMoral(3);
		this.setUnitName(this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf('.') + 1,
				this.getClass().getName().length()));
	}

}
