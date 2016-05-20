package engine;

public class CombatUnit extends Unit{
	private int health;
	private int atk;
	private int def;
	private int moral;
	private boolean alive;
	private String unitName;

	public CombatUnit(boolean team, GameZone unitZone) {
		super(team, unitZone);
		this.alive = true;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getMoral() {
		return moral;
	}

	public void setMoral(int moral) {
		this.moral = moral;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getActualAtk(){
		return this.atk + this.moral * 3;
	}
	
	public int getActualDef(){
		return this.def + this.moral * 2;
	}
	
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
