package engine;

/**
 * Az engine statikus metódusait tartalmazó osztály.
 */
public class GameUtility {
	
	/**
	 * Konstruktor az osztályhoz,sosem hívjuk meg.
	 */
	private GameUtility(){	
	}
	
	/**
	 * Két zóna {@link GameZone#GameZone(int, int)} közötti távolsággal tér vissza.
	 * 
	 * @param sourceZone első zóna
	 * @param destZone második zóna
	 * @return int a két zóna közötti távolság
	 */
	public static int distanceBetween(GameZone sourceZone,GameZone destZone){
		if (Math.abs(sourceZone.getX() - destZone.getX()) >
				Math.abs(sourceZone.getY() - destZone.getY()))
			return Math.abs(sourceZone.getX() - destZone.getX());
		return Math.abs(sourceZone.getY() - destZone.getY());
	}
	
	/**
	 * Két koordinátat {@link GameZone#getX()},{@link GameZone#getY()} megvizsgálva eldönti,hogy létezik
	 * e olyan zóna,mely az adott koordinátákból áll.
	 * 
	 * @param x X koordináta
	 * @param y Y koordináta
	 * @return boolean létezik e ilyen zóna
	 */
	public static boolean isValidPos(int x,int y){
		if ( x >= 0 && x < 10 && y >= 0 && y < 10)
			return true;
		return false;
	}
	
	/**
	 * Két zónát {@link GameZone#GameZone(int, int)},{@link GameZone#GameZone(int, int)} 
	 * megvizsgálva eldönti,hogy érvényes mozgás lehetséges e kettőjükön.
	 * 
	 * @param sourceZone első zóna
	 * @param destZone második zóna
	 * @return boolean létezik e ilyen mozgás
	 */
	public static boolean isValidMove(GameZone sourceZone,GameZone destZone){
		if (distanceBetween(sourceZone,destZone) == 1 && 
				destZone.isAvailable())
			return true;
		return false;
	}
	
	/**
	 * Két zónát {@link GameZone#GameZone(int, int)},{@link GameZone#GameZone(int, int)} 
	 * megvizsgálva eldönti,hogy megyeznek e.
	 * 
	 * @param zone1 első zóna
	 * @param zone2 második zóna
	 * @return boolean megegyeznek e a zónák
	 */
	public static boolean sameZone(GameZone zone1,GameZone zone2){
		if (zone1.getX() == zone2.getX() && zone1.getY() == zone2.getY())
			return true;
		return false;
	}
	
	/**
	 * Két egységet {@link CombatUnit#CombatUnit(boolean, GameZone)},
	 * {@link CombatUnit#CombatUnit(boolean, GameZone)} megvizsgálva eldönti,
	 * hogy megegyeznek e.
	 * 
	 * @param unit1 első egység
	 * @param unit2 második egység
	 * @return boolean megegyeznek e az egységek
	 */
	public static boolean sameUnit(CombatUnit unit1,CombatUnit unit2){
		if (unit1.isTeam() == unit2.isTeam() && 
				GameUtility.sameZone(unit1.getUnitZone(),unit2.getUnitZone()))
			return true;
		return false;
	}
	
	/**
	 * Két egység {@link CombatUnit#CombatUnit(boolean, GameZone)},
	 * {@link CombatUnit#CombatUnit(boolean, GameZone)} közül visszatéríti azt,amelyik megnyeri a harcot.
	 * 
	 * @param unit1 első egység
	 * @param unit2 második egység
	 * @return CombatUnit győztes egység
	 */
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
	
	/**
	 * Két egységet {@link CombatUnit#CombatUnit(boolean, GameZone)},
	 * {@link CombatUnit#CombatUnit(boolean, GameZone)}
	 * megvizsgálva eldönti,hogy érvényes támadás lehetséges e.
	 * 
	 * @param atkUnit támadó egység
	 * @param defUnit védekező egység
	 * @return boolean léhetséges e a támadás
	 */
	public static boolean isValidAttack(CombatUnit atkUnit, CombatUnit defUnit){
		if (GameUtility.distanceBetween(atkUnit.getUnitZone(),defUnit.getUnitZone()) == 1
				&& atkUnit.isTeam() != defUnit.isTeam())
			return true;
		return false;
	}
	
	/**
	 * Visszaad egy egységet a tömbök egyikéből {@link Game#getFirstTeam()},{@link Game#getSecondTeam()}
	 * zóna {@link GameZone#GameZone(int, int)} alapján.
	 * 
	 * @param zone zóna ami alapján keressük az egységet
	 * @param firstTeam első csapat,amiben keresünk
	 * @param secondTeam második csapat,amiben keresünk
	 * @return CombatUnit egység az adott zónán,a két csapat egyikéből
	 */
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
	
	/**
	 * Egy egységet mozgat,figyelembe véve a többi egységet {@link Game#getFirstTeam()},
	 * {@link Game#getSecondTeam()}.
	 * 
	 * @param unit léptetni kívánt egység
	 * @param zone a léptetés céljaként megadott zóna
	 * @param firstTeam első csapat amiben figyeljük az egységeket
	 * @param secondTeam második csapat amiben figyeljük az egységeket
	 */
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
