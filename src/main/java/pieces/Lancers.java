package pieces;
import engine.*;

/**
 * A Lancers egység osztálya.
 */
public class Lancers extends CombatUnit{

	/**
	 * A Lancers egység konstruktora.
	 * 
	 * @param team az egység csapata
	 * @param unitZone az egység jelenlegi zónája
	 */
	public Lancers(boolean team, GameZone unitZone) {
		super(team, unitZone);
		this.setHealth(100);
		this.setAtk(4);
		this.setDef(1);
		this.setMoral(4);
		this.setUnitName(this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf('.') + 1,
				this.getClass().getName().length()));
	}

}
