package pieces;
import engine.*;

/**
 * A Cavalry egység osztálya.
 */
public class Guard extends CombatUnit{
	
	/**
	 * A Guard egység konstruktora.
	 * 
	 * @param team az egység csapata
	 * @param unitZone az egység jelenlegi zónája
	 */
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
