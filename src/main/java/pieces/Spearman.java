package pieces;
import engine.*;


/**
 * A Spearman egység osztálya.
 */
public class Spearman extends CombatUnit{

	/**
	 * A Spearman egység konstruktora.
	 * 
	 * @param team az egység csapata
	 * @param unitZone az egység jelenlegi zónája
	 */
	public Spearman(boolean team, GameZone unitZone) {
		super(team, unitZone);
		this.setHealth(100);
		this.setAtk(4);
		this.setDef(1);
		this.setMoral(3);
		this.setUnitName(this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf('.') + 1,
				this.getClass().getName().length()));
	}

}
