package engine;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import pieces.*;

public class TestEngine {

	@Test
	public void gameZoneTest(){
		GameZone zone1 = new GameZone(1,1,true);
		GameZone zone2 = new GameZone(1,1);
		
		assertEquals(1,zone1.getX());
		zone1.setX(2);
		assertEquals(2,zone1.getX());
		assertEquals(1,zone1.getY());
		zone1.setY(2);
		assertEquals(2,zone1.getX());
		assertEquals(true,zone1.isAvailable());
		zone1.setAvailable(false);
		assertEquals(false,zone1.isAvailable());
		zone1.setOwnedBy(true);
		assertEquals(true,zone1.isOwnedBy());
		zone1.setOwnedBy(false);
		assertEquals(false,zone1.isOwnedBy());

		assertEquals(1,zone2.getX());
		zone2.setX(2);
		assertEquals(2,zone2.getX());
		assertEquals(1,zone2.getY());
		zone2.setY(2);
		assertEquals(2,zone2.getX());
		zone2.setAvailable(true);
		assertEquals(true,zone2.isAvailable());
		zone2.setAvailable(false);
		assertEquals(false,zone2.isAvailable());
		zone2.setOwnedBy(true);
		assertEquals(true,zone2.isOwnedBy());
		zone2.setOwnedBy(false);
		assertEquals(false,zone2.isOwnedBy());
	}
	
	@Test
	public void boardTest(){
		Board board = new Board();
		
		assertEquals(new Board().getZones()[0][0].getX(),board.getZones()[0][0].getX());
		board.setZones(new Board().getZones());
		assertEquals(new Board().getZones()[0][0].getX(),board.getZones()[0][0].getX());
		assertEquals(10,board.getBoardSize());
		board.setBoardSize(5);
		assertEquals(5,board.getBoardSize());
		assertEquals(new Board().getZones()[0][0].getX(),board.getZone(0, 0).getX());
	}
	
	@Test
	public void unitTest(){
		Unit unit = new Unit(true,new GameZone(1,1));
		
		assertEquals(new GameZone(1,1).getX(),unit.getUnitZone().getX());
		unit.setUnitZone(new GameZone(2,1));
		assertEquals(new GameZone(2,1).getX(),unit.getUnitZone().getX());
		assertEquals(true,unit.isTeam());
		unit.setTeam(false);
		assertEquals(false,unit.isTeam());
		unit.moveUnit(new GameZone(6,1));
		assertEquals(new GameZone(2,1).getX(),unit.getUnitZone().getX());
	}

	@Test
	public void combatUnitTest(){
		CombatUnit unit1 = new Cavalry(true,new GameZone(1,1));
		CombatUnit unit2 = new Guard(true,new GameZone(1,2));
		CombatUnit unit3 = new Infantry(true,new GameZone(1,3));
		CombatUnit unit4 = new Lancers(true,new GameZone(1,4));
		CombatUnit unit5 = new Militia(true,new GameZone(1,5));
		CombatUnit unit6 = new Paladin(true,new GameZone(1,6));
		CombatUnit unit7 = new Peasant(true,new GameZone(1,7));
		CombatUnit unit8 = new Raiders(true,new GameZone(1,8));
		CombatUnit unit9 = new Spearman(true,new GameZone(1,9));
		CombatUnit unit10 = new Swordsmen(true,new GameZone(1,0));

		assertEquals(100,unit1.getHealth());
		unit1.setHealth(88);
		assertEquals(88,unit1.getHealth());
		assertEquals(1,unit2.getAtk());
		unit2.setAtk(2);
		assertEquals(2,unit2.getAtk());
		assertEquals(3,unit3.getDef());
		unit3.setDef(4);
		assertEquals(4,unit3.getDef());
		assertEquals(4,unit4.getMoral());
		unit4.setMoral(5);
		assertEquals(5,unit4.getMoral());
		assertEquals(true,unit5.isAlive());
		unit5.setAlive(false);
		assertEquals(false,unit5.isAlive());
		assertEquals("Paladin",unit6.getUnitName());
		unit6.setUnitName("Bobby");
		assertEquals("Bobby",unit6.getUnitName());
		assertEquals(10,unit7.getActualAtk());
		assertEquals(5,unit8.getActualDef());
		unit9.attackTarget(unit10);
		assertEquals(true,unit9.isAlive());
	}
	
	@Test
	public void gameTest(){
		Game game = new Game();
		List<String> units1 = Arrays.asList("Peasant","Cavalry","Guard","Infantry","Lancers","Militia");
		List<String> units2 = Arrays.asList("Paladin","Raiders","Spearman","Swordsmen","Lancers","Militia");
		
		assertEquals(true,game.isFirstPlayer());
		game.setFirstPlayer(false);
		assertEquals(false,game.isFirstPlayer());
		game.initGame(units2, units1);
		game.initGame(units1, units2);
		assertEquals(6,game.getFirstAlive());
		game.setFirstTeam(game.getFirstTeam());
		assertEquals(true,game.getFirstTeam()[0].isTeam());
		game.setSecondTeam(game.getSecondTeam());
		assertEquals(false,game.getSecondTeam()[0].isTeam());
		game.setBoard(game.getBoard());
		assertEquals(new Board().getZone(0, 0).getX(),game.getBoard().getZone(0, 0).getX());
		game.firstTeamRound(0, 2, 1, 2);
		assertEquals(new GameZone(1,2).getX(),game.getFirstTeam()[0].getUnitZone().getX());
		game.secondTeamRound(9, 2, 8, 2);
		assertEquals(new GameZone(8,2).getX(),game.getSecondTeam()[0].getUnitZone().getX());
		game.letsPlay(1, 2, 2, 2);
		assertEquals(new GameZone(2,2).getX(),game.getFirstTeam()[0].getUnitZone().getX());
		assertEquals(false,game.isEnded());
		assertEquals(true,game.firstWon());
		game.moveAI();
		assertEquals(new GameZone(7,2).getX(),game.getSecondTeam()[0].getUnitZone().getX());
	}
	
	@Test
	public void gameUtilityTest(){
		assertEquals(1,GameUtility.distanceBetween(new GameZone(0,0), new GameZone(1,1)));
		assertEquals(true,GameUtility.isValidPos(0, 0));
		assertEquals(true,GameUtility.isValidMove(new GameZone(0,0,true), new GameZone(1,1,true)));
		assertEquals(true,GameUtility.sameZone(new GameZone(1,1), new GameZone(1,1)));
		assertEquals(true,GameUtility.sameUnit(new CombatUnit(true,new GameZone(1,1)),
				new CombatUnit(true,new GameZone(1,1))));
		assertEquals(true,GameUtility.sameUnit(new Cavalry(true,new GameZone(1,1)),
				GameUtility.fightWinner(new Cavalry(true,new GameZone(1,1)),
						new Peasant(false,new GameZone(2,1)))));
		assertEquals(true,GameUtility.isValidAttack(new CombatUnit(true,new GameZone(1,2)),
				new CombatUnit(false,new GameZone(2,2))));
		List<String> units1 = Arrays.asList("Peasant","Cavalry","Guard","Infantry","Lancers","Militia");
		List<String> units2 = Arrays.asList("Paladin","Raiders","Spearman","Swordsmen","Lancers","Militia");
		Game game = new Game();
		game.initGame(units1, units2);
		assertEquals(true,GameUtility.sameUnit(game.getFirstTeam()[0], 
				GameUtility.getUnitByZone(game.getFirstTeam()[0].getUnitZone(),
				game.getFirstTeam(),game.getSecondTeam())));
		GameUtility.moveUnitAdv(game.getFirstTeam()[0], new GameZone(1,2,true),
				game.getFirstTeam(), game.getSecondTeam());
		assertEquals(true,GameUtility.sameZone(new GameZone(1,2,false),game.getFirstTeam()[0].getUnitZone()));
	}
}
