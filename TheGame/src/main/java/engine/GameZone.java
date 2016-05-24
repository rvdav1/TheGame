package engine;

/**
 * A tábla zónáit,négyzeteit reprezentáló osztály.
 */
public class GameZone {
	
	/**
	 * A zóna X koordinátája.
	 */
	private int x;
	
	/**
	 * A zóna Y koordinátája.
	 */
	private int y;
	
	/**
	 * A zóna foglaltsága.
	 */
	private boolean available;
	
	/**
	 * A zónát birtokló csapat.
	 */
	private boolean ownedBy;
	
	/**
	 * Konstruktor egy zóna létrehozásához,koordinátákkal és a foglaltságot visszaadó logikai változóval.
	 *
	 * @param x	a zóna X koordinátája
	 * @param y	a zóna Y koordinátája
	 * @param available	a zónán áll e egy egység
	 */
	public GameZone(int x, int y, boolean available) {
		super();
		this.x = x;
		this.y = y;
		this.available = available;
	}
	
	/**
	 * Konstruktor egy zóna létrehozásához koordinátákkal.
	 *
	 * @param x	a zóna X koordinátája
	 * @param y	a zóna Y koordinátája
	 */
	public GameZone(int x,int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * A zóna X koordinátáját visszaadó függvény.
	 * 
	 * @return x a zóna X koordinátája
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * A zóna X koordinátáját beállító függvény.
	 * 
	 * @param x a zóna X koordinátája
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * A zóna Y koordinátáját visszaadó függvény.
	 * 
	 * @return y a zóna Y koordinátája
	 */
	public int getY() {
		return y;
	}

	/**
	 * A zóna Y koordinátáját beállító függvény.
	 * 
	 * @param y a zóna Y koordinátája
	 */
	public void setY(int y) {
		this.y = y;
	}

	
	/**
	 * A zóna foglaltságát visszaadó függvény.
	 * 
	 * @return available a zóna foglaltsága
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * A zóna foglaltságát beállító függvény.
	 * 
	 * @param available a zóna foglaltsága
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * A zónát birtokló csapatot beállító függvény.
	 * 
	 * @return ownedBy a zónát birtokló csapat
	 */
	public boolean isOwnedBy() {
		return ownedBy;
	}

	/**
	 * A zónát birtokló csapatot visszaadó függvény.
	 * 
	 * @param ownedBy a zónát birtokló csapat
	 */
	public void setOwnedBy(boolean ownedBy) {
		this.ownedBy = ownedBy;
	}
}

