package engine;

public class Board {
	private GameZone zones[][] = new GameZone[10][10];
	private int boardSize;
	
	public Board(){
		this.boardSize = 10;
		for (int x = 0; x < 10; x++){
			for (int y = 0; y < 10; y++){
				zones[x][y] = new GameZone(x,y,true);
			}
		}
	}

	public GameZone[][] getZones() {
		return zones;
	}

	public void setZones(GameZone[][] zones) {
		this.zones = zones;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}	
	
	public GameZone getZone(int x, int y){
		return zones[x][y];
	}
}
