package pieces;
import engine.*;

/**
 * A Peasant egység osztálya.
 */
public class Peasant extends CombatUnit {
	
	/**
	 * A Peasant egység konstruktora.
	 * 
	 * @param team az egység csapata
	 * @param unitZone az egység jelenlegi zónája
	 */
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
