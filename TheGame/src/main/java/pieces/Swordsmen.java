package pieces;
import engine.*;

/**
 * A Swordsmen egység osztálya.
 */
public class Swordsmen extends CombatUnit{

	/**
	 * A Swordsmen egység konstruktora.
	 * 
	 * @param team az egység csapata
	 * @param unitZone az egység jelenlegi zónája
	 */
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
