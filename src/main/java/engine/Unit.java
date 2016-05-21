package engine;

/**
 * A játék prototípus egységeit reprezentáló osztály.
 */
public class Unit {
	
	/**
	 * A prototípus egység jelenlegi zónája.
	 */
	private GameZone unitZone;
	
	/**
	 * A prototípus egység csapata.
	 */
	private boolean team;
	
	/**
	 * Konstruktor egy prototípus egység létrehozásához.
	 * 
	 * @param team a prototípus egység csapata
	 * @param unitZone a prototípus egység jelenlegi zónája
	 */
	public Unit(boolean team,GameZone unitZone){
		this.team = team;
		this.unitZone = unitZone;
		unitZone.setAvailable(false);
		unitZone.setOwnedBy(team);
	}
	
	/**
	 * A prototípus egység jelenlegi zónáját visszaadó függvény.
	 * 
	 * @return unitZone a prototípus egység jelenlegi zónája
	 */
	public GameZone getUnitZone() {
		return unitZone;
	}

	/**
	 * A prototípus egység jelenlegi zónáját beállító függvény.
	 * 
	 * @param unitZone a prototípus egység jelenlegi zónája
	 */
	public void setUnitZone(GameZone unitZone) {
		this.getUnitZone().setAvailable(true);
		this.unitZone = unitZone;
		this.getUnitZone().setAvailable(false);
		unitZone.setOwnedBy(this.team);
	}

	/**
	 * A prototípus egység csapatát visszaadó függvény.
	 * 
	 * @return team a prototípus egység csapata
	 */
	public boolean isTeam() {
		return this.team;
	}

	/**
	 * A prototípus egység csapatát beállító függvény.
	 * 
	 * @param team a prototípus egység csapata
	 */
	public void setTeam(boolean team) {
		this.team = team;
	}
	
	/**
	 * A prototípus egységet új elérhető zónába mozgató függvény,ha az helyes{@link GameUtility#isValidMove(GameZone, GameZone)}.
	 * 
	 * @param destZone a prototípus egység új zónája
	 */
	public void moveUnit(GameZone destZone){
		if (GameUtility.isValidMove(this.getUnitZone(),destZone))
			this.setUnitZone(destZone);
	}
}

