package pieces;
import engine.*;

/**
 * A Raiders egység osztálya.
 */
public class Raiders extends CombatUnit{

	/**
	 * A Raiders egység konstruktora.
	 * 
	 * @param team az egység csapata
	 * @param unitZone az egység jelenlegi zónája
	 */
	public Raiders(boolean team, GameZone unitZone) {
		super(team, unitZone);
		this.setHealth(100);
		this.setAtk(6);
		this.setDef(1);
		this.setMoral(2);
		this.setUnitName(this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf('.') + 1,
				this.getClass().getName().length()));
	}

}
