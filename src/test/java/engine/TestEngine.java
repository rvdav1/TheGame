package engine;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import pieces.*;

public class TestEngine {
	Board board = new Board();
	Game game = new Game();
	List<String> firstTeam = Arrays.asList("Cavalry","Guard","Infantry","Lancers","Militia","Peasant");
	List<String> secondTeam = Arrays.asList("Paladin","Raiders","Spearman","Swordsmen","Militia","Peasant");

	@Test
	public void testZone(){
		GameZone zone = new GameZone(4,5,true);
		GameZone zone2 = new GameZone(0,0);
		zone2.setX(1);
		zone2.setY(1);
		
		assertEquals(1,zone2.getX());
		assertEquals(1,zone2.getY());
		assertEquals(4, zone.getX());
		assertEquals(5, zone.getY());
		assertEquals(true, zone.isAvailable());
	}
	
	@Test
	public void testBoard(){
		assertEquals(10,board.getBoardSize(),0);
		assertEquals(board.getZones()[4][4],board.getZone(4, 4));
		board.setBoardSize(10);
		board.setZones(board.getZones());
		assertEquals(10,board.getBoardSize(),0);
		assertEquals(board.getZones()[4][4],board.getZone(4, 4));
	}
	
	@Test
	public void testUnit(){
		GameZone zone1 = board.getZone(4, 4);
		GameZone zone2 = board.getZone(5, 5);

		Unit unit = new Unit(true,zone1);
		
		assertEquals(zone1,unit.getUnitZone());
		assertEquals(true,unit.isTeam());
		unit.moveUnit(zone2);
		assertEquals(zone2,unit.getUnitZone());
		unit.setTeam(false);
		assertEquals(false,unit.isTeam());
		unit.moveUnit(zone1);
		assertEquals(zone1,unit.getUnitZone());
	}
	
	@Test
	public void testCombatUnit(){
		CombatUnit unit1 = new Cavalry(true,board.getZone(0, 0));
		CombatUnit unit2 = new Guard(false,board.getZone(0, 1));
		
		assertEquals(100,unit1.getHealth());
		assertEquals(true,unit2.isAlive());
		unit1.attackTarget(unit2);
		assertEquals(false,unit2.isAlive());
		assertEquals(5,unit1.getAtk());
		assertEquals(1,unit1.getDef());
		assertEquals(3,unit1.getMoral());
		assertEquals(14,unit1.getActualAtk());
		assertEquals(7,unit1.getActualDef());
		assertEquals("Cavalry",unit1.getUnitName());
	}
	
	@Test
	public void testGame(){
		game.initGame(firstTeam, secondTeam);
		assertEquals(true,game.isFirstPlayer());
		assertEquals(6,game.getFirstAlive());
		Board board2 = game.getBoard();
		
		game.getFirstTeam()[5].setAlive(false);
		assertEquals(5,game.getFirstAlive());
		assertEquals(6,game.getSecondAlive());
		
		game.getSecondTeam()[5].setAlive(false);
		assertEquals(5,game.getSecondAlive());
		
		game.firstTeamRound(0, 2, 1, 2);
		assertEquals(false,game.isFirstPlayer());
		
		game.firstTeamRound(9, 2, 8, 2);
		assertEquals(true,game.isFirstPlayer());
		
		game.letsPlay(1, 2, 2, 2);
		assertEquals(false,game.isFirstPlayer());
		
		assertEquals(false,game.isEnded());
		
		for (int i = 0; i < 6; i++)
			game.getFirstTeam()[i].setAlive(false);
		assertEquals(false,game.firstWon());
		
		game.setFirstPlayer(false);
		game.setBoard(game.getBoard());
		game.setFirstTeam(game.getSecondTeam());
		game.setSecondTeam(game.getFirstTeam());
		assertEquals(false,game.isFirstPlayer());
		assertEquals(game.getFirstTeam()[0],game.getSecondTeam()[0]);
		assertEquals(board2,game.getBoard());
		
		Game game2 = new Game();
		game2.initGame(secondTeam, firstTeam);
		game2.letsPlay(0, 2, 1, 2);
		game2.letsPlay(9, 2, 8, 2);
		assertEquals(1,game2.getFirstTeam()[0].getUnitZone().getX());
		assertEquals(8,game2.getSecondTeam()[0].getUnitZone().getX());

	}
	
	@Test
	public void testGameUtility(){
		CombatUnit unit1 = new Peasant(true,board.getZone(5, 5));
		CombatUnit unit2 = new Peasant(true,board.getZone(5, 4));
		CombatUnit unit3 = new Peasant(false,board.getZone(5, 5));
		CombatUnit unit4 = new Peasant(true,board.getZone(5, 5));
		CombatUnit unit5 = new Peasant(false,board.getZone(4, 4));
		CombatUnit unit11 = new Cavalry(false,board.getZone(0, 0));
		CombatUnit unit12 = new Guard(false,board.getZone(1, 0));
		CombatUnit unit13 = new Infantry(false,board.getZone(2, 0));
		CombatUnit unit14 = new Lancers(false,board.getZone(3, 0));
		CombatUnit unit15 = new Militia(false,board.getZone(4, 0));
		CombatUnit unit16 = new Paladin(false,board.getZone(5, 0));
		CombatUnit unit17 = new Raiders(false,board.getZone(6, 0));
		CombatUnit unit18 = new Spearman(false,board.getZone(7, 0));
		CombatUnit unit19 = new Swordsmen(false,board.getZone(8, 0));

		assertEquals(false,GameUtility.sameUnit(unit11, unit12));
		assertEquals(false,GameUtility.sameUnit(unit13, unit14));
		assertEquals(false,GameUtility.sameUnit(unit15, unit16));
		assertEquals(false,GameUtility.sameUnit(unit17, unit18));
		assertEquals(true,GameUtility.sameUnit(unit19, unit19));
		
		game.initGame(firstTeam, firstTeam);
		
		assertEquals(3,GameUtility.distanceBetween(board.getZone(0, 0),board.getZone(2, 3)));
		assertEquals(true,GameUtility.isValidPos(1, 1));
		assertEquals(false,GameUtility.isValidPos(10, 1));
		assertEquals(true,GameUtility.isValidMove(board.getZone(1, 1), board.getZone(2, 1)));
		assertEquals(false,GameUtility.isValidMove(board.getZone(1, 1), board.getZone(3, 1)));
		assertEquals(true,GameUtility.sameZone(board.getZone(1, 1), board.getZone(1, 1)));
		assertEquals(false,GameUtility.sameZone(board.getZone(1, 1), board.getZone(2, 1)));
		assertEquals(true,GameUtility.sameZone(unit1.getUnitZone(), unit4.getUnitZone()));
		assertEquals(false,GameUtility.sameUnit(unit1, unit2));
		assertEquals(false,GameUtility.sameUnit(unit1, unit3));
		assertEquals(true,GameUtility.sameUnit(unit1, unit4));
		assertEquals(true,GameUtility.sameUnit(unit1, GameUtility.fightWinner(unit1, unit2)));
		assertEquals(true,GameUtility.isValidAttack(unit1, unit5));
		assertEquals(false,GameUtility.isValidAttack(unit1, unit2));
		assertEquals(true,GameUtility.sameUnit(game.getFirstTeam()[0],
				GameUtility.getUnitByZone(board.getZone(0, 2), game.getFirstTeam(), game.getSecondTeam())));
		
		GameUtility.moveUnitAdv(game.getFirstTeam()[0], game.getBoard().getZone(1, 2), 
				game.getFirstTeam(), game.getSecondTeam());
		assertEquals(true,GameUtility.sameZone(game.getFirstTeam()[0].getUnitZone(),
				game.getBoard().getZone(1, 2)));
	}

}
