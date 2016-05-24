package pieces;
import engine.*;

/**
 * A Cavalry egység adatai.
 */
public class Cavalry extends CombatUnit{
	
	/**
	 * A Cavalry egység konstruktora.
	 * 
	 * @param team az egység csapata
	 * @param unitZone az egység jelenlegi zónája
	 */
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
