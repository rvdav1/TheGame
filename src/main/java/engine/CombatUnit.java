package engine;

/**
 * A játék egységeit reprezentáló osztály.
 */
public class CombatUnit extends Unit{
	
	/**
	 * A játék egy egységének az élete.
	 */
	private int health;
	
	/**
	 * A játék egy egységének a támadó értéke.
	 */
	private int atk;
	
	/**
	 * A játék egy egységének a védekező értéke.
	 */
	private int def;
	
	/**
	 * A játék egy egységének a morálja.
	 */
	private int moral;
	
	/**
	 * A játék adott egysége életbe van e.
	 */
	private boolean alive;
	
	/**
	 * A játék egy egységének neve.
	 */
	private String unitName;

	/**
	 * Konstruktor a játék egy egységének létrehozásához.
	 * 
	 * @param team a játék egy egységének a csapata
	 * @param unitZone a játék egy egységének a jelenlegi zónája
	 */
	public CombatUnit(boolean team, GameZone unitZone) {
		super(team, unitZone);
		this.alive = true;
	}

	/**
	 * A játék egy egységének életét visszaadó függvény.
	 * 
	 * @return health a játék egy egységének élete
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * A játék egy egységének életét beállító függvény.
	 * 
	 * @param health a játék egy egységének élete
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * A játék egy egységének támató értékét visszaadó függvény.
	 * 
	 * @return atk a játék egy egységének támadó értéke
	 */
	public int getAtk() {
		return atk;
	}

	/**
	 * A játék egy egységének támató értékét beállító függvény.
	 * 
	 * @param atk a játék egy egységének támadó értéke
	 */
	public void setAtk(int atk) {
		this.atk = atk;
	}

	/**
	 * A játék egy egységének védekező értékét visszaadó függvény.
	 * 
	 * @return def a játék egy egységének védekező értéke
	 */
	public int getDef() {
		return def;
	}

	/**
	 * A játék egy egységének védekező értékét beállító függvény.
	 * 
	 * @param def a játék egy egységének védekező értéke
	 */
	public void setDef(int def) {
		this.def = def;
	}

	/**
	 * A játék egy egységének morálját visszaadó függvény.
	 * 
	 * @return moral a játék egy egységének morálja
	 */
	public int getMoral() {
		return moral;
	}

	/**
	 * A játék egy egységének morálját beállító függvény.
	 * 
	 * @param moral a játék egy egységének morálja
	 */
	public void setMoral(int moral) {
		this.moral = moral;
	}
	
	/**
	 * A játék egy egységének életben létét visszaadó függvény.
	 * 
	 * @return alive a játék egy egységének életben léte
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * A játék egy egységének életben létét beállító függvény.
	 * 
	 * @param alive a játék egy egységének életben léte
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * A játék egy egységének nevét visszaadó függvény.
	 * 
	 * @return unitName a játék egy egységének neve
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * A játék egy egységének nevét beállító függvény.
	 * 
	 * @param unitName a játék egy egységének neve
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * A játék egy egységének módosított támadó értékét visszaadó függvény.
	 * 
	 * @return realAtk a játék egy egységének módosított támadó értéke
	 */
	public int getActualAtk(){
		int realAtk = this.atk + this.moral * 3;
		return realAtk;
	}
	
	/**
	 * A játék egy egységének módosított védekező értékét visszaadó függvény.
	 * 
	 * @return realDef a játék egy egységének módosított védekező értéke
	 */
	public int getActualDef(){
		int realDef = this.def + this.moral * 2;
		return realDef;
	}
	
	/**
	 * Ha az egyik egység megtudja támadni a másik egységet {@link GameUtility#isValidAttack(CombatUnit, CombatUnit)},
	 * akkor ez meg is történik {@link GameUtility#fightWinner(CombatUnit, CombatUnit)} és a nyertes egységet
	 * átmozgatja a célterületre {@link Unit#moveUnit(GameZone)}.
	 * 
	 * @param destUnit a megtámadni kívánt egység
	 */
	public void attackTarget(CombatUnit destUnit){
		if (!GameUtility.isValidAttack(this,destUnit))
			return;
		
		GameZone zoneToMove;
		if(this == GameUtility.fightWinner(this,destUnit)){
			zoneToMove = destUnit.getUnitZone();
			destUnit.setUnitZone(new GameZone(10,10,true));
			destUnit.setAlive(false);
			this.moveUnit(zoneToMove);
		} else {
			zoneToMove = this.getUnitZone();
			this.setUnitZone(new GameZone(10,10,true));
			this.setAlive(false);
		}
	}
}
