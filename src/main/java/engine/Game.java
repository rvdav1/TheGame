package engine;

import java.util.List;

import pieces.*;

public class Game {
	private boolean firstPlayer = true;
	private int firstAlive;
	private int secondAlive;
	
	public Game(){
	}
	
	private Board board = new Board();
	
	private CombatUnit firstTeam[] = new CombatUnit[6];
	private CombatUnit secondTeam[] = new CombatUnit[6];
	
	public boolean isFirstPlayer() {
		return firstPlayer;
	}
	
	public void setFirstPlayer(boolean firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	
	public int getFirstAlive() {
		firstAlive = 0;
		for (int i = 0; i < 6; i++){
			if (firstTeam[i].isAlive())
				firstAlive++;
		}
		return firstAlive;
	}
	
	public int getSecondAlive() {
		secondAlive = 0;
		for (int i = 0; i < 6; i++){
			if (secondTeam[i].isAlive())
				secondAlive++;
		}
		return secondAlive;	
	}
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public CombatUnit[] getFirstTeam() {
		return firstTeam;
	}
	public void setFirstTeam(CombatUnit[] firstTeam) {
		this.firstTeam = firstTeam;
	}
	public CombatUnit[] getSecondTeam() {
		return secondTeam;
	}
	public void setSecondTeam(CombatUnit[] secondTeam) {
		this.secondTeam = secondTeam;
	}

	public void initGame(List<String> first,List<String> second){
		int i = 0;
		while (i != 6){
			if (first.get(i).equals("Cavalry"))
				firstTeam[i] = (new Cavalry(true,board.getZone(0, i + 2)));
			else if (first.get(i).equals("Guard"))
				firstTeam[i] = (new Guard(true,board.getZone(0, i + 2)));
			else if (first.get(i).equals("Infantry"))	
				firstTeam[i] = (new Infantry(true,board.getZone(0, i + 2)));
			else if (first.get(i).equals("Lancers"))
				firstTeam[i] = (new Lancers(true,board.getZone(0, i + 2)));
			else if (first.get(i).equals("Militia"))
				firstTeam[i] = (new Militia(true,board.getZone(0, i + 2)));
			else if (first.get(i).equals("Paladin"))
				firstTeam[i] = (new Paladin(true,board.getZone(0, i + 2)));
			else if (first.get(i).equals("Peasant"))
				firstTeam[i] = (new Peasant(true,board.getZone(0, i + 2)));
			else if (first.get(i).equals("Raiders"))
				firstTeam[i] = (new Raiders(true,board.getZone(0, i + 2)));
			else if (first.get(i).equals("Spearman"))
				firstTeam[i] = (new Spearman(true,board.getZone(0, i + 2)));
			else if (first.get(i).equals("Swordsmen"))
				firstTeam[i] = (new Swordsmen(true,board.getZone(0, i + 2)));
			i++;
		}
		i = 0;
		while (i != 6){
			if (second.get(i).equals("Cavalry"))
				secondTeam[i] = (new Cavalry(false,board.getZone(9, i + 2)));
			else if (second.get(i).equals("Guard"))
				secondTeam[i] = (new Guard(false,board.getZone(9, i + 2)));
			else if (second.get(i).equals("Infantry"))	
				secondTeam[i] = (new Infantry(false,board.getZone(9, i + 2)));
			else if (second.get(i).equals("Lancers"))
				secondTeam[i] = (new Lancers(false,board.getZone(9, i + 2)));
			else if (second.get(i).equals("Militia"))
				secondTeam[i] = (new Militia(false,board.getZone(9, i + 2)));
			else if (second.get(i).equals("Paladin"))
				secondTeam[i] = (new Paladin(false,board.getZone(9, i + 2)));
			else if (second.get(i).equals("Peasant"))
				secondTeam[i] = (new Peasant(false,board.getZone(9, i + 2)));
			else if (second.get(i).equals("Raiders"))
				secondTeam[i] = (new Raiders(false,board.getZone(9, i + 2)));
			else if (second.get(i).equals("Spearman"))
				secondTeam[i] = (new Spearman(false,board.getZone(9, i + 2)));
			else if (second.get(i).equals("Swordsmen"))
				secondTeam[i] = (new Swordsmen(false,board.getZone(9, i + 2)));
			i++;
		}
	}
	
	public void firstTeamRound(int fromX, int fromY, int toX, int toY){
		firstPlayer = true;
		if (GameUtility.getUnitByZone(board.getZone(fromX, fromY),firstTeam,secondTeam) == null)
			return;
		if (GameUtility.getUnitByZone(board.getZone(fromX, fromY),firstTeam,secondTeam).isTeam() != firstPlayer)
			return;
		if (GameUtility.sameZone(board.getZone(fromX, fromY), board.getZone(toX, toY)))
			return;
		
		CombatUnit unit = GameUtility.getUnitByZone(board.getZone(fromX, fromY),firstTeam,secondTeam);
		GameZone zone = board.getZone(toX, toY);
		GameUtility.moveUnitAdv(unit, zone,firstTeam,secondTeam);
		if(GameUtility.sameZone(unit.getUnitZone(), zone) || !unit.isAlive())
			firstPlayer = false;
		
	}
	
	public void secondTeamRound(int fromX, int fromY, int toX, int toY){
		if (GameUtility.getUnitByZone(board.getZone(fromX, fromY),firstTeam,secondTeam) == null)
			return;
		if (GameUtility.getUnitByZone(board.getZone(fromX, fromY),firstTeam,secondTeam).isTeam() != firstPlayer)
			return;
		if (GameUtility.sameZone(board.getZone(fromX, fromY), board.getZone(toX, toY)))
			return;
		
		CombatUnit unit = GameUtility.getUnitByZone(board.getZone(fromX, fromY),firstTeam,secondTeam);
		GameZone zone = board.getZone(toX, toY);
		GameUtility.moveUnitAdv(unit, zone,firstTeam,secondTeam);
		if(GameUtility.sameZone(unit.getUnitZone(), zone) || !unit.isAlive())
			firstPlayer = true;
	}
	
	public void letsPlay(int fromX, int fromY, int toX, int toY){
		if (firstPlayer)
			firstTeamRound(fromX, fromY, toX, toY);
		else
			secondTeamRound(fromX, fromY, toX, toY);
	}
	
	public boolean isEnded(){
		if (this.getFirstAlive() == 0 || this.getSecondAlive() == 0)
			return true;
		return false;
	}
	
	public boolean firstWon(){
		if (this.getFirstAlive() == 0)
			return false;
		return true;
	}
	
	public void moveAI(){
		CombatUnit target = null;
		CombatUnit attacker = null;
		int lowest = 256;

		for (int i = 0; i < 6; i++){
			if (firstTeam[i].isAlive()){
				target = firstTeam[i];
				break;
			}
		}
		
		for (int i = 0; i < 6; i++){
			if (secondTeam[i].isAlive()){
				if(GameUtility.distanceBetween(secondTeam[i].getUnitZone(), target.getUnitZone()) < lowest){
					lowest = GameUtility.distanceBetween(secondTeam[i].getUnitZone(), target.getUnitZone());
					attacker = secondTeam[i];
				}
			}
		}
			
		if (target.getUnitZone().getX() > attacker.getUnitZone().getX() &&
				target.getUnitZone().getY() > attacker.getUnitZone().getY())
			GameUtility.moveUnitAdv(attacker, board.getZone(attacker.getUnitZone().getX() + 1,
					attacker.getUnitZone().getY() + 1),firstTeam, secondTeam);
		else if (target.getUnitZone().getX() > attacker.getUnitZone().getX() &&
				target.getUnitZone().getY() < attacker.getUnitZone().getY())
			GameUtility.moveUnitAdv(attacker, board.getZone(attacker.getUnitZone().getX() + 1,
					attacker.getUnitZone().getY() - 1),firstTeam, secondTeam);
		else if (target.getUnitZone().getX() < attacker.getUnitZone().getX() &&
				target.getUnitZone().getY() > attacker.getUnitZone().getY())
			GameUtility.moveUnitAdv(attacker, board.getZone(attacker.getUnitZone().getX() - 1,
					attacker.getUnitZone().getY() + 1),firstTeam, secondTeam);
		else if (target.getUnitZone().getX() < attacker.getUnitZone().getX() &&
				target.getUnitZone().getY() < attacker.getUnitZone().getY())
			GameUtility.moveUnitAdv(attacker, board.getZone(attacker.getUnitZone().getX() - 1,
					attacker.getUnitZone().getY() - 1),firstTeam, secondTeam);
		else if (target.getUnitZone().getX() > attacker.getUnitZone().getX())
			GameUtility.moveUnitAdv(attacker, board.getZone(attacker.getUnitZone().getX() + 1,
					attacker.getUnitZone().getY()),firstTeam, secondTeam);
		else if (target.getUnitZone().getX() < attacker.getUnitZone().getX())
			GameUtility.moveUnitAdv(attacker, board.getZone(attacker.getUnitZone().getX() - 1,
					attacker.getUnitZone().getY()),firstTeam, secondTeam);
		else if (target.getUnitZone().getY() > attacker.getUnitZone().getY())
			GameUtility.moveUnitAdv(attacker, board.getZone(attacker.getUnitZone().getX(),
					attacker.getUnitZone().getY() + 1),firstTeam, secondTeam);
		else if (target.getUnitZone().getY() < attacker.getUnitZone().getY())
			GameUtility.moveUnitAdv(attacker, board.getZone(attacker.getUnitZone().getX(),
					attacker.getUnitZone().getY() +- 1),firstTeam, secondTeam);
		firstPlayer = true;
	}
}
