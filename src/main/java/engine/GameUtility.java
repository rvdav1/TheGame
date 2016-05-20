package engine;

public class GameUtility {
	
	private GameUtility(){	
	}
	
	public static int distanceBetween(GameZone sourceZone,GameZone destZone){
		if (Math.abs(sourceZone.getX() - destZone.getX()) >
				Math.abs(sourceZone.getY() - destZone.getY()))
			return Math.abs(sourceZone.getX() - destZone.getX());
		return Math.abs(sourceZone.getY() - destZone.getY());
	}
	
	public static boolean isValidPos(int x,int y){
		if ( x >= 0 && x < 10 && y >= 0 && y < 10)
			return true;
		return false;
	}
	
	public static boolean isValidMove(GameZone sourceZone,GameZone destZone){
		if (distanceBetween(sourceZone,destZone) == 1 && 
				destZone.isAvailable())
			return true;
		return false;
	}
	
	public static boolean sameZone(GameZone zone1,GameZone zone2){
		if (zone1.getX() == zone2.getX() && zone1.getY() == zone2.getY())
			return true;
		return false;
	}
	
	public static boolean sameUnit(CombatUnit unit1,CombatUnit unit2){
		if (unit1.isTeam() == unit2.isTeam() && 
				GameUtility.sameZone(unit1.getUnitZone(),unit2.getUnitZone()))
			return true;
		return false;
	}
	
	public static CombatUnit fightWinner(CombatUnit unit1,CombatUnit unit2){
		while (unit1.getHealth() > 0 && unit2.getHealth() > 0){
			unit2.setHealth(unit2.getHealth() + unit2.getActualDef() - 
					(unit1.getActualAtk() + 10));			
			if (unit2.getHealth() > 0){
				unit1.setHealth(unit1.getHealth() + unit1.getActualAtk() - 
						(unit2.getActualDef() + 10));
			} else {
				unit2.setHealth(0);
				return unit1;			
			}
		}
		unit1.setHealth(0);
		return unit2;
	}
	
	public static boolean isValidAttack(CombatUnit atkUnit, CombatUnit defUnit){
		if (GameUtility.distanceBetween(atkUnit.getUnitZone(),defUnit.getUnitZone()) == 1
				&& atkUnit.isTeam() != defUnit.isTeam())
			return true;
		return false;
	}
	
	public static CombatUnit getUnitByZone(GameZone zone,CombatUnit[] firstTeam,CombatUnit[] secondTeam){
		if (firstTeam.length != 6 || secondTeam.length != 6)
			return null;	
		for (int i = 0; i < 6; i++){
			if (GameUtility.sameZone(firstTeam[i].getUnitZone(), zone))
				return firstTeam[i];
			if (GameUtility.sameZone(secondTeam[i].getUnitZone(), zone))
				return secondTeam[i];
		}
		return null;
	}
	
	public static void moveUnitAdv(CombatUnit unit,GameZone zone,CombatUnit[] firstTeam,CombatUnit[] secondTeam){
		if (firstTeam.length != 6 || secondTeam.length != 6)
			return;
		if (GameUtility.distanceBetween(unit.getUnitZone(), zone) != 1)
			return;
		if (zone.isAvailable())
			unit.moveUnit(zone);
		if (!(zone.isAvailable()) && (zone.isOwnedBy() != unit.isTeam())){
			unit.attackTarget(GameUtility.getUnitByZone(zone,firstTeam,secondTeam));
		}		
	}
}
