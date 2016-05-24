package engine;

/**
 * A játék tábláját reprezentáló osztály.
 */
public class Board {
	
	/**
	 * A tábla összes zónája.
	 */
	private GameZone zones[][] = new GameZone[10][10];
	
	/**
	 * A tábla mérete.
	 */
	private int boardSize;
	
	/**
	 * Konstruktor egy tábla létrehozásához.
	 */
	public Board(){
		this.boardSize = 10;
		for (int x = 0; x < 10; x++){
			for (int y = 0; y < 10; y++){
				zones[x][y] = new GameZone(x,y,true);
			}
		}
	}

	/**
	 * A tábla zónáit visszaadó függvény.
	 * 
	 * @return zones a tábla összes zónája
	 */
	public GameZone[][] getZones() {
		return zones;
	}

	/**
	 * A tábla zónáit beállító függvény.
	 * 
	 * @param zones a tábla összes zónája
	 */
	public void setZones(GameZone[][] zones) {
		this.zones = zones;
	}

	/**
	 * A tábla méretét visszaadó függvény.
	 * 
	 * @return boardSize a tábla mérete
	 */
	public int getBoardSize() {
		return boardSize;
	}

	/**
	 * A tábla méretét beállító osztály.
	 * 
	 * @param boardSize a tábla mérete
	 */
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}	
	
	/**
	 * A tábla egy adott zónáját visszaadó függvény.
	 * 
	 * @param x a zóna X koordinátája
	 * @param y a zóna Y koordinátája
	 * @return zones[x][y] a tábla X,Y zónája
	 */
	public GameZone getZone(int x, int y){
		return zones[x][y];
	}
}
