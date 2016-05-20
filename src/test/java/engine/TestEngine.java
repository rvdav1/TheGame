package engine;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import pieces.*;

public class TestEngine {
	Board board = new Board();
	Game game = new Game();
	List<String> firstTeam = Arrays.asList("Peasant","Peasant","Peasant","Peasant","Peasant","Peasant");

	@Test
	public void testZone(){
		GameZone zone = new GameZone(4,5,true);
		
		assertEquals(4, zone.getX(),0);
		assertEquals(5, zone.getY(),0);
		assertEquals(true, zone.isAvailable());
	}
	
	@Test
	public void testBoard(){
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
	}
	
	@Test
	public void testCombatUnit(){
		CombatUnit unit1 = new Cavalry(true,board.getZone(0, 0));
		CombatUnit unit2 = new Guard(false,board.getZone(0, 1));

		assertEquals(true,unit2.isAlive());
		unit1.attackTarget(unit2);
		assertEquals(false,unit2.isAlive());
	}
	
	@Test
	public void testGame(){
		game.initGame(firstTeam, firstTeam);
		assertEquals(true,game.isFirstPlayer());
		assertEquals(6,game.getFirstAlive());
		
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
	}
	
	@Test
	public void testGameUtility(){
		CombatUnit unit1 = new Peasant(true,board.getZone(5, 5));
		CombatUnit unit2 = new Peasant(true,board.getZone(5, 4));
		CombatUnit unit3 = new Peasant(false,board.getZone(5, 5));
		CombatUnit unit4 = new Peasant(true,board.getZone(5, 5));
		CombatUnit unit5 = new Peasant(false,board.getZone(4, 4));
		
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
