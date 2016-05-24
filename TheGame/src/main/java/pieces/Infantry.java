package pieces;
import engine.*;

/**
 * A Infantry egység osztálya.
 */
public class Infantry extends CombatUnit{
	
	/**
	 * A Infantry egység konstruktora.
	 * 
	 * @param team az egység csapata
	 * @param unitZone az egység jelenlegi zónája
	 */
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
