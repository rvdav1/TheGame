package engine;

public class Unit {
	private GameZone unitZone;
	private boolean team;
	
	public Unit(boolean team,GameZone unitZone){
		this.team = team;
		this.unitZone = unitZone;
		unitZone.setAvailable(false);
		unitZone.setOwnedBy(team);
	}

	public GameZone getUnitZone() {
		return unitZone;
	}

	public void setUnitZone(GameZone unitZone) {
		this.getUnitZone().setAvailable(true);
		this.unitZone = unitZone;
		this.getUnitZone().setAvailable(false);
		unitZone.setOwnedBy(this.team);
	}

	public boolean isTeam() {
		return this.team;
	}

	public void setTeam(boolean team) {
		this.team = team;
	}
	
	public void moveUnit(GameZone destZone){
		if (GameUtility.isValidMove(this.getUnitZone(),destZone))
			this.setUnitZone(destZone);
	}
}

